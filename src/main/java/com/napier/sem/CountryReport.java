package com.napier.sem;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CountryReport {
    private Connection con;
    public CountryReport(Connection con) {
            this.con = con;
        }
    public void processCountryReport() {
        App app = new App();
        try {
            //Country Report Printing and Creating Markdown
            ResultSet allCountriesByWorld = executeQueryFromFile(App.sqlFileBasePath + "ALLCOUNTRIESfromWORLD.sql");
            ResultSet TopNCountriesByWorld = executeQueryFromFile(App.sqlFileBasePath + "TopNpopulatedCOUNTRIESfromWORLD.sql");
            ResultSet allCountriesByContinent = executeQueryFromFile(App.sqlFileBasePath + "ALLCOUNTRIESfromSELECTEDCONTINENT.sql");
            ResultSet TopNCountriesByContinent = executeQueryFromFile(App.sqlFileBasePath + "TopNpopulatedCOUNTRIESfromCONTINENT.sql");
            ResultSet allCountriesByRegion = executeQueryFromFile(App.sqlFileBasePath + "ALLCOUNTRIESfromSELECTEDREGION.sql");
            ResultSet TopNCountriesByRegion = executeQueryFromFile(App.sqlFileBasePath + "TopNpopulatedCOUNTRIESfromREGION.sql");
            if(allCountriesByWorld != null){
                displayAndGenerateReport(allCountriesByWorld, "Country_Report_World.md", "World Countries Report");
            }
            if(TopNCountriesByWorld != null){
                displayAndGenerateReport(TopNCountriesByWorld, "Top_N_Country_Report_World.md", "Top 10 Countries In The World");
            }
            if(allCountriesByContinent != null){
                displayAndGenerateReport(allCountriesByContinent, "Country_Report_Continent.md", "Countries by Continent");
            }
            if(TopNCountriesByContinent != null){
                displayAndGenerateReport(TopNCountriesByContinent, "Top_N_Country_Report_Continent.md","Top 10 Countries by Continent");
            }
            if(allCountriesByRegion != null){
                displayAndGenerateReport(allCountriesByRegion, "Country_Report_Region.md","Countries By Region");
            }
            if(TopNCountriesByRegion != null){
                displayAndGenerateReport(TopNCountriesByRegion, "Top_N_Country_Report_Region.md","Top 10 Countries By Region");
            }

        } catch (SQLException e){
            System.out.println("Error processing Country Report: "+ e.getMessage());
        }finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
    private void displayAndGenerateReport(ResultSet resultSet, String filename, String reportTitle) throws SQLException {
        ArrayList<Country> countries = new ArrayList<>();
        System.out.println(reportTitle + "\n");
        System.out.println(String.format("%-5s %-35s %-18s %-26s %-12s %-7s", "Code", "Name", "Continent", "Region", "Population", "Capital"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------");

        while (resultSet.next()) {
            String code = resultSet.getString("Code");
            String name = resultSet.getString("Name");
            String continent = resultSet.getString("Continent");
            String region = resultSet.getString("Region");
            int population = resultSet.getInt("Population");
            String capital = resultSet.getString("Capital");
            System.out.println(String.format("%-5s %-35s %-18s %-26s %-12d %-7s", code, name, continent, region, population, capital));

            // Add to list for markdown generation
            countries.add(new Country(code, name, continent, region, population, capital));
        }

        if (!countries.isEmpty()) {
            generateCountryReportMarkdown(countries, filename);
        } else {
            System.out.println("No countries found for generating report.");
        }
    }

    public void generateCountryReportMarkdown(ArrayList<Country> countries, String filename) {
        StringBuilder sb = new StringBuilder();
        sb.append("# Country Report\n\n");
        sb.append("| Code | Name | Continent | Region | Population | Capital |\n");
        sb.append("| ---- | ---- | --------- | ------ | ---------- | ------- |\n");
        for (Country country : countries) {
            sb.append("| ")
                    .append(country.getCode()).append(" | ")
                    .append(country.getName()).append(" | ")
                    .append(country.getContinent()).append(" | ")
                    .append(country.getRegion()).append(" | ")
                    .append(country.getPopulation()).append(" | ")
                    .append(country.getCapital() != null ? country.getCapital() : "N/A")
                    .append(" |\n");
        }

        saveReportToFile(sb.toString(), filename);
    }

    private void saveReportToFile(String content, String filename) {
        try {
            File directory = new File("./reports");
            if (!directory.exists()) {
                directory.mkdir();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(directory, filename)));
            writer.write(content);
            writer.close();
            System.out.println("Report generated: " + filename);
        } catch (IOException e) {
            System.out.println("Error generating country report: " + e.getMessage());
        }
    }

    public ResultSet executeQueryFromFile(String filePath) {
        try {
            String query = readQueryFromFile(filePath);
            Statement stmt = con.createStatement();
            return stmt.executeQuery(query);
        } catch (IOException | SQLException e) {
            System.out.println("Error executing SQL query from file: " + e.getMessage());
            return null;
        }
    }

    private String readQueryFromFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File does not exist: " + file.getAbsolutePath());
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder queryBuilder = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            queryBuilder.append(line).append("\n");
        }
        br.close();
        return queryBuilder.toString();
    }
}