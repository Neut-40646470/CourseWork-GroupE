package com.napier.sem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
    private Connection con = null;
    public void connect(String location, int delay) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }
        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                Thread.sleep(delay);
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "123");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException | InterruptedException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            }
        }
    }
    public void disconnect() {
        try {
            if (con != null) {
                con.close();
                System.out.println("Disconnected from database");
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection to database");
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<Cities> getAllCities() {
        ArrayList<Cities> cities = new ArrayList<>();
        try (Statement stmt = con.createStatement()) {
            String query = "SELECT * FROM city";
            ResultSet rset = stmt.executeQuery(query);
            while (rset.next()) {
                Cities city = new Cities();
                city.ID = rset.getInt("ID");
                city.Name = rset.getString("Name");
                city.CountryCode = rset.getString("CountryCode");
                city.District = rset.getString("District");
                city.Population = rset.getInt("Population");
                cities.add(city);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get city array: " + e.getMessage());
            cities.clear();
            cities = null;
        }
        return cities.isEmpty() ? null : cities;
    }
    public ArrayList<Country> getAllCountries(String query) {
        ArrayList<Country> countries = new ArrayList<>();
        try (Statement stmt = con.createStatement()) {
            String strSelect = query;
            ResultSet rset = stmt.executeQuery(strSelect);
            while (rset.next()) {
                Country country = new Country(
                        rset.getString("Code"),
                        rset.getString("Name"),
                        rset.getString("Continent"),
                        rset.getString("Region"),
                        rset.getInt("Population"),
                        rset.getInt("Capital")
                );
                countries.add(country);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get country array: " + e.getMessage());
            countries.clear();
            countries = null;
        }
        return countries.isEmpty() ? null : countries;
    }
    public void generateCityReportMarkdown(ArrayList<Cities> cities, String filename) {
        CitiesReport citiesReport = new CitiesReport(con);
        citiesReport.generateCityReportMarkdown(cities, filename);
    }
    public void printCitiesFromContinent(String continent, String queryFile) {
        CitiesReport citiesReport = new CitiesReport(con);
        citiesReport.printCitiesFromContinent(continent, queryFile);
        ArrayList<Cities> cities = getAllCities();
        generateCityReportMarkdown(cities, "testing.md");
    }
    public void printCitiesFromDistrict(String district, String queryFile) {
        CitiesReport citiesReport = new CitiesReport(con);
        citiesReport.printCitiesFromDistrict(district, queryFile);
        ArrayList<Cities> cities = getAllCities();
        generateCityReportMarkdown(cities, "test_district.md");
    }
    public void printCitiesFromRegion(String region, String queryFile) {
        CitiesReport citiesReport = new CitiesReport(con);
        citiesReport.printCitiesFromRegion(region, queryFile);
    }
    //COUNTRY THINGS GO HERE
    public void generateCountryReportMarkdown(ArrayList<Country> countries, String filename) {
        CountryReport countryReport = new CountryReport(con);
        countryReport.generateCountryReportMarkdown(countries, filename);
    }
    public void printCountriesFromContinent(String continent, String queryFile) {
        CountryReport countryReport = new CountryReport(con);
        countryReport.printCountriesFromContinent(continent, queryFile);
    }
    public void printCountriesFromRegion(String region, String queryFile) {
        CountryReport countryReport = new CountryReport(con);
        countryReport.printCountriesFromRegion(region, queryFile);
    }
}

