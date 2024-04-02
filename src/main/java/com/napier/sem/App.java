package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
            } catch (SQLException sqle) {
                if(con == null)
                {
                    System.out.println("Test Pass");
                }
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
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

    public void printCitiesFromContinent(String continent) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/ALLCITIESfromSELECTEDCONTINENT.sql"));
            StringBuilder queryBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                queryBuilder.append(line).append("\n");
            }
            br.close();
            String query = queryBuilder.toString().replace("Europe", continent);
            // Print city report header
            System.out.println("City Report\n");
            System.out.println(String.format("%-30s %-30s %-20s %-15s", "Name", "Country", "District", "Population"));
            System.out.println("----------------------------------------------------------------------------");

            // Connect to the database
            connect("localhost:33060", 0); // Adjust connection details as needed

            // Execute the SQL query
            try (Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
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

            // Disconnect from the database
            disconnect();
        } catch (IOException e) {
            System.out.println("Error reading SQL file: " + e.getMessage());
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
            System.out.println("Failed to get city array");
            System.out.println(e.getMessage());
            cities.clear();
            cities = null;
//            return cities; // Return null if SQLException occurs
        }
        return cities.isEmpty() ? null : cities; // Return null if cities list is empty
    }

    public void getCapitalCities()
    {
        return;
    }

    public void printCities(ArrayList<Cities> cities) {
        if (cities == null || cities.isEmpty()) {
            System.out.println("No cities");
            return;
        }

        System.out.println(String.format("%-10s %-20s %-15s %-12s", "City ID", "City Name", "Country", "Population"));
        for (Cities city : cities) {
            String cityString = String.format("%-10s %-20s %-15s %-12s",
                    city.ID, city.Name, city.CountryCode, city.Population);
            System.out.println(cityString);
        }
    }
}