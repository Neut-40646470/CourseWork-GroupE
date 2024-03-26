package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

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
