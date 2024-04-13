package com.napier.sem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class App {
    public static void main(String[] args) {
        // Create new Application
        App app = new App();
        System.out.println("World Population App");
        if (args.length < 1) {
            app.connect("localhost:33060", 0);
        } else {
            app.connect("db:3306", 10000);
        }
        // Connect to database

        app.processReports();

        app.connect("localhost:33060",0);
        ArrayList<Cities> cities = app.getAllCities();
        app.printCities(cities);
        // Disconnect from database
        app.disconnect();
    }

    //Connect to database
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
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    public void disconnect() {
        //try disconnect from DB
        try {
            if (con != null) {
                con.close();
                System.out.println("Disconnected from database");
            }
            //error handling
        } catch (SQLException e) {
            System.out.println("Error closing connection to database");
            System.out.println(e.getMessage());
        }
    }

    public void processReports() {
        // Instantiate report processors
        CapitalCitiesReport capitalCitiesReport = new CapitalCitiesReport(con);
        CitiesReport citiesReport = new CitiesReport(con); // Assuming you have similar constructor
        CountryReport countryReport = new CountryReport(con); // Assuming you have similar constructor

        // Generate and print reports
        capitalCitiesReport.processCapitalCityReport();
        citiesReport.processCityReport(); // Placeholder for actual method call
        countryReport.processCountryReport(); // Placeholder for actual method call
    }
    public ArrayList<Cities> getAllCities() {
        if (con == null) {
            System.out.println("Database connection is not established.");
            return null;
        }

        // Check if connection is closed
        try {
            if (con.isClosed()) {
                System.out.println("Connection is closed before query execution.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Failed to check if connection is closed: " + e.getMessage());
            return null;
        }

        ArrayList<Cities> cities = new ArrayList<>();
        String strSelect = "SELECT city.ID, city.Name AS Name, city.CountryCode, "
                + "country.Name AS Country, city.District, city.Population "
                + "FROM world.city "
                + "JOIN world.country ON city.CountryCode = country.Code";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(strSelect)) {
            System.out.println("All Cities for Unit Test\n");
            System.out.println(String.format("%-10s %-20s %-15s %-12s", "City ID", "City Name", "Country", "Population"));
            while (rs.next()) {
                Cities city = new Cities();
                city.ID = rs.getInt("ID");
                city.Name = rs.getString("Name");
                city.CountryCode = rs.getString("CountryCode");
                city.District = rs.getString("District");
                city.Population = rs.getInt("Population");
                cities.add(city);

                String cityString = String.format("%-10d %-20s %-15s %-12d",
                        city.ID, city.Name, city.CountryCode, city.Population);
                System.out.println(cityString);
            }
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return cities;
    }

    public void printCities(ArrayList<Cities> cities) {
        if (cities == null || cities.isEmpty()) {
            System.out.println("No cities");
            return;
        }
        System.out.println(String.format("%-10s %-20s %-15s %-12s", "City ID", "City Name", "Country", "Population"));
        for (Cities city : cities) {
            String cityString = String.format("%-10d %-20s %-15s %-12d",
                    city.ID, city.Name, city.CountryCode, city.Population);
            System.out.println(cityString);
        }
    }


}