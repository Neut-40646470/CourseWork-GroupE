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
            System.out.println("\n\nCountry report generated: " + filename);
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
            System.out.println(String.format("%-5s %-35s %-18s %-26s %-12s %-7s", "Code", "Name", "Continent", "Region", "Population", "Capital"));
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            // Print countries from the ResultSet
            while (rs.next()) {
                String code = rs.getString("Code");
                String name = rs.getString("Name");
                String continent = rs.getString("Continent");
                String region = rs.getString("Region");
                int population = rs.getInt("Population");
                // Handle Capital as Integer, use getInt instead of getString
                String capital = rs.getString("Capital");
                System.out.println(String.format("%-5s %-35s %-18s %-26s %-12d %-7s", code, name, continent, region, population, capital));
            }
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
    }

        public void printCountriesFromWorld(String queryFile) {
            try {
                String query = readQueryFromFile(queryFile);
                executeQuery(query, "Country Report By World");
            } catch (IOException e) {
                System.out.println("Error reading SQL file: " + e.getMessage());
            }
        }

        public void printTopNCountriesFromWorld(String queryFile) {
            try {
                String query = readQueryFromFile(queryFile);
                executeQuery(query, "Top N Country Report By World");
            } catch (IOException e) {
                System.out.println("Error reading SQL file: " + e.getMessage());
            }
        }

    public void printCountriesFromContinent(String queryFile) {
        try {
            String query = readQueryFromFile(queryFile);
            executeQuery(query, "Country Report By Continent");
        } catch (IOException e) {
            System.out.println("Error reading SQL file: " + e.getMessage());
        }
    }

    public void printCountriesFromRegion( String queryFile) {
        try {
            String query = readQueryFromFile(queryFile);
            executeQuery(query, "Country Report By Region");
        } catch (IOException e) {
            System.out.println("Error reading SQL file: " + e.getMessage());
        }
    }
}
//public void generateCountryReportFromResultSet(ResultSet resultSet, String filename) {
//    try {
//        ArrayList<Country> countries = new ArrayList<>();
//        while (resultSet.next()) {
//            Country country = new Country();
//            country.setCode(resultSet.getString("Code"));
//            country.setName(resultSet.getString("Name"));
//            country.setContinent(resultSet.getString("Continent"));
//            country.setRegion(resultSet.getString("Region"));
//            country.setPopulation(resultSet.getInt("Population"));
//
//             Handle Capital as Integer
//            try {
//                country.setCapital(resultSet.getInt("Capital"));
//            } catch (NumberFormatException e) {
//                country.setCapital(0); // Set a default value or handle as needed
//                System.out.println("Error parsing Capital value: " + e.getMessage());
//            }
//
//            countries.add(country);
//        }
//        if (!countries.isEmpty()) {
//            generateCountryReportMarkdown(countries, filename);
//        } else {
//            System.out.println("No countries found for generating report: " + filename);
//        }
//    } catch (SQLException e) {
//        System.out.println("Error generating country report from ResultSet: " + e.getMessage());
//    }
//}


//    public void printCountriesFromWorld(String world, String queryFile) {
//        try {
//            String query = readQueryFromFile(queryFile).replace("", world);
//            executeQuery(query, "Country Report By World");
//        } catch (IOException e) {
//            System.out.println("Error reading SQL file: " + e.getMessage());
//        }
//    }
//}
