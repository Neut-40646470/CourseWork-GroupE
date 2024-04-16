package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PopulationReport {

    private Connection con;

    public PopulationReport(Connection con) {
        this.con = con;
    }

    public void generatePopulationStatisticsByContinent(String continent) {
        try {
            String query = "SELECT Continent, " +
                    "SUM(c.Population) AS TotalPopulation, " +
                    "SUM(CASE WHEN c.ID = co.Capital THEN c.Population ELSE 0 END) AS PopulationInCities, " +
                    "SUM(CASE WHEN c.ID != co.Capital THEN c.Population ELSE 0 END) AS PopulationNotInCities " +
                    "FROM country co " +
                    "JOIN city c ON co.Capital = c.ID " +
                    "WHERE Continent = ? " +
                    "GROUP BY Continent";

            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, continent);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int totalPopulation = rs.getInt("TotalPopulation");
                int populationInCities = rs.getInt("PopulationInCities");
                int populationNotInCities = rs.getInt("PopulationNotInCities");

                MarkdownGenerator.generatePopulationStatisticsMarkdown(continent, totalPopulation, populationInCities, populationNotInCities);
            }

            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generatePopulationStatisticsByRegion(String region) {
        try {
            String query = "SELECT Region, " +
                    "SUM(c.Population) AS TotalPopulation, " +
                    "SUM(CASE WHEN c.ID = co.Capital THEN c.Population ELSE 0 END) AS PopulationInCities, " +
                    "SUM(CASE WHEN c.ID != co.Capital THEN c.Population ELSE 0 END) AS PopulationNotInCities " +
                    "FROM country co " +
                    "JOIN city c ON co.Capital = c.ID " +
                    "WHERE Region = ? " +
                    "GROUP BY Region";

            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, region);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int totalPopulation = rs.getInt("TotalPopulation");
                int populationInCities = rs.getInt("PopulationInCities");
                int populationNotInCities = rs.getInt("PopulationNotInCities");

                MarkdownGenerator.generatePopulationStatisticsMarkdown(region, totalPopulation, populationInCities, populationNotInCities);
            }

            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generatePopulationStatisticsByCountry(String country) {
        try {
            String query = "SELECT Name AS Country, " +
                    "Population AS TotalPopulation, " +
                    "Population AS PopulationInCities, " +
                    "0 AS PopulationNotInCities " +
                    "FROM country " +
                    "WHERE Name = ?";

            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, country);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int totalPopulation = rs.getInt("TotalPopulation");
                int populationInCities = rs.getInt("PopulationInCities"); // This will always be equal to TotalPopulation
                int populationNotInCities = rs.getInt("PopulationNotInCities");

                MarkdownGenerator.generatePopulationStatisticsMarkdown(country, totalPopulation, populationInCities, populationNotInCities);
            }

            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void generateLanguageSpeakersReport() {
        try {
            // Query to get the number of speakers for each language
            String query = "SELECT Language, SUM(Percentage) AS TotalSpeakers " +
                    "FROM countrylanguage " +
                    "WHERE Language IN (?, ?, ?, ?, ?) " +
                    "GROUP BY Language";

            // Execute the query
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, "Chinese");
            stmt.setString(2, "English");
            stmt.setString(3, "Hindi");
            stmt.setString(4, "Spanish");
            stmt.setString(5, "Arabic");
            ResultSet rs = stmt.executeQuery();

            // Map to store language and total speakers
            Map<String, Integer> languageSpeakers = new HashMap<>();

            // Process the result set
            while (rs.next()) {
                String language = rs.getString("Language");
                int totalSpeakers = rs.getInt("TotalSpeakers");
                languageSpeakers.put(language, totalSpeakers);
            }

            // Generate markdown report for language speakers
            MarkdownGenerator.generateLanguageSpeakersReport(languageSpeakers);

            // Close the statement and result set
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}