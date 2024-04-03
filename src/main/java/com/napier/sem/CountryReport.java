package com.napier.sem;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CountryReport {
    private Connection con;

    public CountryReport(Connection con) {
        this.con = con;
    }
    public void generateCountryReportMarkdown(ArrayList<Country> countries, String filename) {
        if (countries == null || countries.isEmpty()) {
            System.out.println("No countries to generate report.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("# Country Report\n\n");
        sb.append("| Code | Name | Continent | Region | Population | Capital |\n");
        sb.append("| ---- | ---- | --------- | ------ | ---------- | ------- |\n");
        for (Country country : countries) {
            sb.append("| " + country.getCode() + " | " + country.getName() + " | " +
                    country.getContinent() + " | " + country.getRegion() + " | " +
                    country.getPopulation() + " | " + country.getCapital() + " |\n");
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
            System.out.println("Country report generated: " + filename);
        } catch (IOException e) {
            System.out.println("Error generating country report: " + e.getMessage());
        }
    }


    private String readQueryFromFile(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        StringBuilder queryBuilder = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            queryBuilder.append(line).append("\n");
        }
        br.close();
        return queryBuilder.toString();
    }

    private void executeQuery(String query, String reportTitle) {
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            // Print country report header
            System.out.println(reportTitle + "\n");
            System.out.println(String.format("%-5s %-52s %-12s %-26s %-12s %-7s", "Code", "Name", "Continent", "Region", "Population", "Capital"));
            System.out.println("--------------------------------------------------------------------------------------------------");
            // Print countries from the ResultSet
            while (rs.next()) {
                String code = rs.getString("Code");
                String name = rs.getString("Name");
                String continent = rs.getString("Continent");
                String region = rs.getString("Region");
                int population = rs.getInt("Population");
                int capital = rs.getInt("Capital");
                System.out.println(String.format("%-5s %-52s %-12s %-26s %-12d %-7d", code, name, continent, region, population, capital));
            }
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
    }

    public void printCountriesFromContinent(String continent, String queryFile) {
        try {
            String query = readQueryFromFile(queryFile).replace("", continent);
            executeQuery(query, "Country Report");
        } catch (IOException e) {
            System.out.println("Error reading SQL file: " + e.getMessage());
        }
    }

    public void printCountriesFromRegion(String region, String queryFile) {
        try {
            String query = readQueryFromFile(queryFile).replace("", region);
            executeQuery(query, "Country Report");
        } catch (IOException e) {
            System.out.println("Error reading SQL file: " + e.getMessage());
        }
    }
}
