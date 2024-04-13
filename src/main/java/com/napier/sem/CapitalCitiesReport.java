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

public class CapitalCitiesReport {
    private Connection con;

    public CapitalCitiesReport(Connection con) {
        this.con = con;
    }
    public void processCapitalCityReport() {
        App app = new App();
        try {
            //Capital City Report and Generation of Markdown file
            ResultSet allCapitalCitiesFromWorld = executeQueryFromFile("/app/resources/AllCapitalCitiesByLargestToSmallest(World).sql");
            ResultSet allCapitalCitiesFromContinent = executeQueryFromFile("/app/resources/AllCapitalCitiesByLargestToSmallest(Continent).sql");
            ResultSet allCapitalCitiesFromRegion = executeQueryFromFile("/app/resources/AllCapitalCitiesByLargestToSmallest(Region).sql");

            if (allCapitalCitiesFromWorld != null) {
            generateCapitalCityReportFromResultSet(allCapitalCitiesFromWorld, "All_Capital_Cities_Report_World.md");
            printCapitalCitiesFromWorld("","/app/resources/AllCapitalCitiesByLargestToSmallest(World).sql");
            }
            if (allCapitalCitiesFromContinent != null) {
                generateCapitalCityReportFromResultSet(allCapitalCitiesFromContinent, "All_Capital_Cities_Report_Continent.md");
                printCapitalCitiesFromContinent("", "/app/resources/AllCapitalCitiesByLargestToSmallest(Continent).sql");
            }
            if (allCapitalCitiesFromRegion != null) {
                generateCapitalCityReportFromResultSet(allCapitalCitiesFromRegion, "All_Capital_Cities_Report_Region.md");
                printCapitalCitiesFromRegion("", "/app/resources/AllCapitalCitiesByLargestToSmallest(Region).sql");
            }

        }finally {
            app.disconnect();
        }
    }
    public void generateCapitalCityReportFromResultSet(ResultSet resultSet, String filename) {
        if(resultSet == null)
        {
            System.out.println("Capital Cities -- NO Data to precess, SQL file Might not exist offailed ****");
            return;
        }
        try {
            ArrayList<CapitalCities> capitalcities = new ArrayList<>();
            while (resultSet.next()) {
                CapitalCities capitalcity = new CapitalCities();
                capitalcity.Name = resultSet.getString("Name");
                capitalcity.CountryCode = resultSet.getString("Country");
                capitalcity.Population = resultSet.getInt("Population");
                capitalcities.add(capitalcity);
            }
            if (!capitalcities.isEmpty()) {
                generateCapitalCityReportMarkdown(capitalcities, filename);
            } else {
                System.out.println("No capital cities found for generating report: " + filename);
            }
        } catch (SQLException e) {
            System.out.println("Error generating Capital city report from ResultSet: " + e.getMessage());
        }
    }

    public void generateCapitalCityReportMarkdown(ArrayList<CapitalCities> capitalcities, String filename) {
        if (capitalcities == null || capitalcities.isEmpty()) {
            System.out.println("No capital cities to generate report.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("# Capital City Report\n\n");
        sb.append("| Name | Country | Population |\n");
        sb.append("| ---- | ------- | ---------- |\n");
        for (CapitalCities capitalcity : capitalcities) {
            sb.append("| " + capitalcity.getName() + " | " + capitalcity.getCountry() + " | " +
                    capitalcity.getPopulation() + " |\n");
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
            System.out.println("\n\nCapital City report generated: " + filename);
        } catch (IOException e) {
            System.out.println("Error generating capital city report: " + e.getMessage());
        }
    }

    private void executeQuery(String query, String reportTitle) {
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            // Print city report header
            System.out.println(reportTitle + "\n");
            System.out.println(String.format("%-30s %-30s %-15s", "Name", "Country", "Population"));
            System.out.println("-------------------------------------------------------------");
            // Print cities from the ResultSet
            while (rs.next()) {
                String name = rs.getString("Name");
                String country = rs.getString("Country");
                int population = rs.getInt("Population");
                System.out.println(String.format("%-30s %-30s %-15d", name, country, population));
            }
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
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
            System.out.println("Attempted to read from: "+ file.getAbsolutePath());
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

    public void printCapitalCitiesFromWorld(String world, String queryFile) {
        try {
            String query = readQueryFromFile(queryFile).replace("", world);
            executeQuery(query, "Capital City Report By World");
        } catch (IOException e) {
            System.out.println("Error reading SQL file: " + e.getMessage());
        }
    }

    public void printCapitalCitiesFromContinent(String continent, String queryFile) {
        try {
            String query = readQueryFromFile(queryFile).replace("", continent);
            executeQuery(query, "Capital City Report By Continent");
        } catch (IOException e) {
            System.out.println("Error reading SQL file: " + e.getMessage());
        }
    }

    public void printCapitalCitiesFromRegion(String region, String queryFile) {
        try {
            String query = readQueryFromFile(queryFile).replace("", region);
            executeQuery(query, "Capital City Report By Region");
        } catch (IOException e) {
            System.out.println("Error reading SQL file: " + e.getMessage());
        }
    }

}
