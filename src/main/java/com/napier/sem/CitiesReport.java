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



public class CitiesReport {
    private Connection con;

    public CitiesReport(Connection con) {
        this.con = con;
    }

    public void generateCityReportMarkdown(ArrayList<Cities> cities, String filename) {
        if (cities == null || cities.isEmpty()) {
            System.out.println("No cities to generate report.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("# City Report\n\n");
        sb.append("| Name | Country | District | Population |\n");
        sb.append("| ---- | ------- | -------- | ---------- |\n");
        for (Cities city : cities) {
            sb.append("| " + city.getName() + " | " + city.getCountry() + " | " +
                    city.getDistrict() + " | " + city.getPopulation() + " |\n");
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
            System.out.println("\n\nCity report generated: " + filename);
        } catch (IOException e) {
            System.out.println("Error generating city report: " + e.getMessage());
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
            System.out.println(String.format("%-30s %-30s %-20s %-15s", "Name", "Country", "District", "Population"));
            System.out.println("----------------------------------------------------------------------------");
            // Print cities from the ResultSet
            while (rs.next()) {
                String name = rs.getString("Name");
                String country = rs.getString("Country");
                String district = rs.getString("District");
                int population = rs.getInt("Population");
                System.out.println(String.format("%-30s %-30s %-20s %-15d", name, country, district, population));
            }
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
    }

    public void printCitiesFromContinent(String continent, String queryFile) {
        try {
            String query = readQueryFromFile(queryFile).replace("", continent);
            executeQuery(query, "City Report By Continent");
        } catch (IOException e) {
            System.out.println("Error reading SQL file: " + e.getMessage());
        }
    }

    public void printCitiesFromDistrict(String district, String queryFile) {
        try {
            String query = readQueryFromFile(queryFile).replace("", district);
            executeQuery(query, "City Report By District");
        } catch (IOException e) {
            System.out.println("Error reading SQL file: " + e.getMessage());
        }
    }

    public void printCitiesFromRegion(String region, String queryFile) {
        try {
            String query = readQueryFromFile(queryFile).replace("", region);
            executeQuery(query, "City Report By Region");
        } catch (IOException e) {
            System.out.println("Error reading SQL file: " + e.getMessage());
        }
    }

    public void printCitiesFromWorld(String world, String queryFile) {
        try {
            String query = readQueryFromFile(queryFile).replace("", world);
            executeQuery(query, "City Report By World");
        } catch (IOException e) {
            System.out.println("Error reading SQL file: " + e.getMessage());
        }
    }


}
