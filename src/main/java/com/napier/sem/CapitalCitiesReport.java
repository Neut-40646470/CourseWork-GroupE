package com.napier.sem;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CapitalCitiesReport {

    private Connection con;

    public CapitalCitiesReport(Connection con) {
        this.con = con;
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

    public void printCapitalCitiesFromDistrict(String district, String queryFile) {
        try {
            String query = readQueryFromFile(queryFile).replace("", district);
            executeQuery(query, "Capital City Report By District");
        } catch (IOException e) {
            System.out.println("Error reading SQL file: " + e.getMessage());
        }
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

