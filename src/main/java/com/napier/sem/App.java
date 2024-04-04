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
            String strSelect = "SELECT city.ID, city.Name AS Name, city.CountryCode, "
                    + "country.Name AS Country, city.District, city.Population "
                    + "FROM world.city "
                    + "JOIN world.country ON city.CountryCode = country.Code";

            ResultSet rset = stmt.executeQuery(strSelect);
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

    public void generateCityReportMarkdown(ArrayList<Cities> cities, String filename) {
        CitiesReport citiesReport = new CitiesReport(con);
        citiesReport.generateCityReportMarkdown(cities, filename);
    }

    public void printCitiesFromContinent(String continent, String queryFile) {
        CitiesReport citiesReport = new CitiesReport(con);
        citiesReport.printCitiesFromContinent(continent, queryFile);
    }

    public void printCitiesFromDistrict(String district, String queryFile) {
        CitiesReport citiesReport = new CitiesReport(con);
        citiesReport.printCitiesFromDistrict(district, queryFile);
    }

    public void printCitiesFromRegion(String region, String queryFile) {
        CitiesReport citiesReport = new CitiesReport(con);
        citiesReport.printCitiesFromRegion(region, queryFile);
    }

}
