package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CitiesReport {
    private Connection con;

    public CitiesReport(Connection con) {
        this.con = con;
    }


    // Methods for generating reports based on various criteria

    // Generate report for all cities in the world
    public void generateAllCitiesInWorldReport() {
        // Retrieve cities ordered by population
        ArrayList<City> cities = getCitiesByQuery("SELECT * FROM city ORDER BY Population DESC");
        // Generate Markdown report for all cities in the world
        MarkdownGenerator.generateCityReportMarkdown(cities, "All_Cities_In_World_Report.md");
    }

    // Generate report for all cities in a continent
    public void generateAllCitiesInContinentReport(String continent) {
        // Retrieve cities in the specified continent
        ArrayList<City> cities = getCitiesByQuery(
                "SELECT * FROM city WHERE CountryCode IN (SELECT Code FROM country WHERE Continent = ?) ORDER BY Population DESC",
                continent);
        // Generate Markdown report for cities in the continent
        MarkdownGenerator.generateCityReportMarkdown(cities,
                "All_Cities_In_" + continent + "_Report.md");
    }

    // Generate report for all cities in a region
    public void generateAllCitiesInRegionReport(String region) {
        // Retrieve cities in the specified region
        ArrayList<City> cities = getCitiesByQuery(
                "SELECT * FROM city WHERE CountryCode IN (SELECT Code FROM country WHERE Region = ?) ORDER BY Population DESC",
                region);
        // Generate Markdown report for cities in the region
        MarkdownGenerator.generateCityReportMarkdown(cities,
                "All_Cities_In_" + region + "_Report.md");
    }

    // Generate report for all cities in a country
    public void generateAllCitiesInCountryReport(String country) {
        // Retrieve cities in the specified country
        ArrayList<City> cities = getCitiesByQuery(
                "SELECT * FROM city WHERE CountryCode IN (SELECT Code FROM country WHERE Name = ?) ORDER BY Population DESC",
                country);
        // Generate Markdown report for cities in the country
        MarkdownGenerator.generateCityReportMarkdown(cities,
                "All_Cities_In_" + country + "_Report.md");
    }

    // Generate report for all cities in a district
    public void generateAllCitiesInDistrictReport(String district) {
        // Retrieve cities in the specified district
        ArrayList<City> cities = getCitiesByQuery(
                "SELECT * FROM city WHERE District = ? ORDER BY Population DESC",
                district);
        // Generate Markdown report for cities in the district
        MarkdownGenerator.generateCityReportMarkdown(cities,
                "All_Cities_In_" + district + "_Report.md");
    }

    // Methods for generating reports for top N populated cities

    // Generate report for top N populated cities in the world
    public void generateTopNPopulatedCitiesInWorldReport(int N) {
        // Retrieve top N populated cities in the world
        ArrayList<City> cities = getCitiesByQuery("SELECT * FROM city ORDER BY Population DESC LIMIT " + N);
        // Generate Markdown report for top N populated cities in the world
        MarkdownGenerator.generateCityReportMarkdown(cities,
                "Top_" + N + "_Populated_Cities_In_World_Report.md");
    }

    // Generate report for top N populated cities in a continent
    public void generateTopNPopulatedCitiesInContinentReport(String continent, int N) {
        // Retrieve top N populated cities in the specified continent
        ArrayList<City> cities = getCitiesByQuery(
                "SELECT * FROM city WHERE CountryCode IN (SELECT Code FROM country WHERE Continent = ?) ORDER BY Population DESC LIMIT " + N,
                continent);
        // Generate Markdown report for top N populated cities in the continent
        MarkdownGenerator.generateCityReportMarkdown(cities,
                "Top_" + N + "_Populated_Cities_In_" + continent + "_Report.md");
    }

    // Generate report for top N populated cities in a region
    public void generateTopNPopulatedCitiesInRegionReport(String region, int N) {
        // Retrieve top N populated cities in the specified region
        ArrayList<City> cities = getCitiesByQuery(
                "SELECT ci.* FROM city ci " +
                        "JOIN country co ON ci.CountryCode = co.Code " +
                        "WHERE co.Region = ? " +
                        "ORDER BY ci.Population DESC LIMIT " + N,
                region);
        // Generate Markdown report for top N populated cities in the region
        MarkdownGenerator.generateCityReportMarkdown(cities,
                "Top_" + N + "_Populated_Cities_In_" + region + "_Report.md");
    }

    // Generate report for top N populated cities in a country
    public void generateTopNPopulatedCitiesInCountryReport(String country, int N) {
        // Retrieve top N populated cities in the specified country
        ArrayList<City> cities = getCitiesByQuery(
                "SELECT * FROM city WHERE CountryCode = ? ORDER BY Population DESC LIMIT " + N,
                country);
        // Generate Markdown report for top N populated cities in the country
        MarkdownGenerator.generateCityReportMarkdown(cities,
                "Top_" + N + "_Populated_Cities_In_" + country + "_Report.md");
    }

    // Generate report for top N populated cities in a district
    public void generateTopNPopulatedCitiesInDistrictReport(String district, int N) {
        // Retrieve top N populated cities in the specified district
        ArrayList<City> cities = getCitiesByQuery(
                "SELECT * FROM city WHERE District = ? ORDER BY Population DESC LIMIT " + N,
                district);
        // Generate Markdown report for top N populated cities in the district
        MarkdownGenerator.generateCityReportMarkdown(cities,
                "Top_" + N + "_Populated_Cities_In_" + district + "_Report.md");
    }

    // Methods for generating reports for capital cities

    // Generate report for all capital cities in the world
    public void generateAllCapitalCitiesInWorldReport() {
        // Retrieve all capital cities in the world
        ArrayList<City> capitalCities = getCapitalCitiesFromCountryTable();
        // Generate Markdown report for all capital cities in the world
        MarkdownGenerator.generateCityReportMarkdown(capitalCities,
                "All_Capital_Cities_In_World_Report.md");
    }

    // Generate report for all capital cities in a continent
    public void generateAllCapitalCitiesInContinentReport(String continent) {
        // Retrieve all capital cities in the specified continent
        ArrayList<City> capitalCities = getCapitalCitiesFromCountryTableByContinent(continent);
        // Generate Markdown report for all capital cities in the continent
        MarkdownGenerator.generateCityReportMarkdown(capitalCities,
                "All_Capital_Cities_In_" + continent + "_Report.md");
    }

    // Generate report for all capital cities in a region
    public void generateAllCapitalCitiesInRegionReport(String region) {
        // Retrieve all capital cities in the specified region
        ArrayList<City> capitalCities = getCapitalCitiesFromCountryTableByRegion(region);
        // Generate Markdown report for all capital cities in the region
        MarkdownGenerator.generateCityReportMarkdown(capitalCities,
                "All_Capital_Cities_In_" + region + "_Report.md");
    }

    // Generate report for top N populated capital cities in the world
    public void generateTopNPopulatedCapitalCitiesInWorldReport(int N) {
        // Retrieve top N populated capital cities in the world
        ArrayList<City> capitalCities = getCapitalCitiesFromCountryTable();
        // Generate Markdown report for top N populated capital cities in the world
        MarkdownGenerator.generateCityReportMarkdown(capitalCities,
                "Top_" + N + "_Populated_Capital_Cities_In_World_Report.md");
    }

    // Generate report for top N populated capital cities in a continent
    public void generateTopNPopulatedCapitalCitiesInContinentReport(String continent, int N) {
        // Retrieve top N populated capital cities in the specified continent
        ArrayList<City> capitalCities = getCapitalCitiesFromCountryTable();
        // Generate Markdown report for top N populated capital cities in the continent
        MarkdownGenerator.generateCityReportMarkdown(capitalCities,
                "Top_" + N + "_Populated_Capital_Cities_In_" + continent + "_Report.md");
    }

    // Generate report for top N populated capital cities in a region
    public void generateTopNPopulatedCapitalCitiesInRegionReport(String region, int N) {
        // Retrieve top N populated capital cities in the specified region
        ArrayList<City> capitalCities = getCapitalCitiesFromCountryTable();
        // Generate Markdown report for top N populated capital cities in the region
        MarkdownGenerator.generateCityReportMarkdown(capitalCities,
                "Top_" + N + "_Populated_Capital_Cities_In_" + region + "_Report.md");
    }

    // Private method to execute a SQL query and retrieve cities based on provided criteria
    private ArrayList<City> getCitiesByQuery(String query, String... parameters) {
        ArrayList<City> cities = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            // Set query parameters
            for (int i = 0; i < parameters.length; i++) {
                stmt.setString(i + 1, parameters[i]);
            }
            ResultSet rs = stmt.executeQuery();
            // Process query results
            while (rs.next()) {
                String name = rs.getString("Name");
                String countryCode = rs.getString("CountryCode");
                String district = rs.getString("District");
                int population = rs.getInt("Population");
                cities.add(new City(name, countryCode, district, population));
            }
        } catch (SQLException e) {
            // Print stack trace in case of database error
            e.printStackTrace();
        }
        return cities;
    }

    // Methods to retrieve capital cities from the country table based on different criteria

    // Retrieve capital cities by continent
    public ArrayList<City> getCapitalCitiesFromCountryTableByContinent(String continent) {
        ArrayList<City> capitalCities = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(
                "SELECT ci.Name AS Name, ci.Name AS Country, ci.Population AS Population " +
                        "FROM world.city ci " +
                        "JOIN world.country co ON ci.CountryCode = co.Code " +
                        "WHERE co.Continent = ? AND ci.ID = co.Capital " +
                        "ORDER BY ci.Population DESC; ")){
            stmt.setString(1, continent);
            ResultSet rs = stmt.executeQuery();
            // Process query results
            while (rs.next()) {
                String name = rs.getString("Name");
                String countryCode = rs.getString("Country");
                int population = rs.getInt("Population");
                capitalCities.add(new City(name, countryCode, population));
            }
        } catch (SQLException e) {
            // Print stack trace in case of database error
            e.printStackTrace();
        }
        return capitalCities;
    }

    // Retrieve capital cities by region
    public ArrayList<City> getCapitalCitiesFromCountryTableByRegion(String region) {
        ArrayList<City> capitalCities = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(
                "SELECT ci.Name AS Name, ci.Name AS Country, ci.Population AS Population " +
                        "FROM world.city ci " +
                        "JOIN world.country co ON ci.CountryCode = co.Code " +
                        "WHERE co.Region = ? AND ci.ID = co.Capital " +
                        "ORDER BY ci.Population DESC; ")) {
            stmt.setString(1, region);
            ResultSet rs = stmt.executeQuery();
            // Process query results
            while (rs.next()) {
                String name = rs.getString("Name");
                String countryCode = rs.getString("Country");
                int population = rs.getInt("Population");
                capitalCities.add(new City(name, countryCode, population));
            }
        } catch (SQLException e) {
            // Print stack trace in case of database error
            e.printStackTrace();
        }
        return capitalCities;
    }

    // Retrieve all capital cities
    public ArrayList<City> getCapitalCitiesFromCountryTable() {
        ArrayList<City> capitalCities = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(
                "SELECT ci.Name AS Name, ci.Name AS Country, ci.Population AS Population " +
                        "FROM world.city ci " +
                        "JOIN world.country co ON ci.CountryCode = co.Code " +
                        "WHERE ci.ID = co.Capital " +
                        "ORDER BY ci.Population DESC; ")) {
            ResultSet rs = stmt.executeQuery();
            // Process query results
            while (rs.next()) {
                String name = rs.getString("Name");
                String countryCode = rs.getString("Country");
                int population = rs.getInt("Population");
                capitalCities.add(new City(name, countryCode, population));
            }
        } catch (SQLException e) {
            // Print stack trace in case of database error
            e.printStackTrace();
        }
        return capitalCities;
    }
}