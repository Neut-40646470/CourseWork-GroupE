package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App {
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */

public void connect(String location, int delay) {
    try {
        // Load Database driver
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        System.out.println("Could not load SQL driver");
        System.exit(-1);
    }

    int retries = 10;
    for (int i = 0; i < retries; ++i) {
        System.out.println("Connecting to database...");
        try {
            // Wait a bit for db to start
            Thread.sleep(delay);
            // Connect to database
            con = DriverManager.getConnection("jdbc:mysql://" + location
                            + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                    "root", "123");
            System.out.println("Successfully Connected To Database");
            break;
        } catch (SQLException sqle) {
            System.out.println("Failed to connect to database attempt " +                                  Integer.toString(i));
            System.out.println(sqle.getMessage());
        } catch (InterruptedException ie) {
            System.out.println("Thread interrupted? Should not happen.");
        }
    }
}

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
                System.out.println("Successfully disconnected");
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public Cities getCities(int ID)
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.ID, city.Name AS Name, city.CountryCode, "
                            + " country.Name AS Country, city.District, city.Population "
                            + "FROM world.city "
                            + "JOIN world.country ON city.CountryCode = country.Code";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            //ArrayList<Cities> city = new ArrayList<>();
            if (rset.next()) {
                Cities city1 = new Cities();
                city1.ID = rset.getInt("ID");
                city1.Name = rset.getString("Name");
                city1.CountryCode = rset.getString("CountryCode");
                city1.District = rset.getString("District");
                city1.Population = rset.getInt("Population");
                return city1;
                //city.add(city1);
            }
            else
                return null;
            //return city;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("FAILED TO GET CITY ARRAY");
            return null;
        }
    }

    public ArrayList<Cities> getAllCities() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.ID, city.Name AS Name, city.CountryCode, "
                        + " country.Name AS Country, city.District, city.Population "
                        + "FROM world.city "
                        + "JOIN world.country ON city.CountryCode = country.Code";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<Cities> city = new ArrayList<>();
            while (rset.next()) {
                Cities city1 = new Cities();
                city1.ID = rset.getInt("ID");
                city1.Name = rset.getString("Name");
                city1.CountryCode = rset.getString("CountryCode");
                city1.District = rset.getString("District");
                city1.Population = rset.getInt("Population");
                city.add(city1);
            }
            return city;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("FAILED TO GET CITY ARRAY");
            return null;
        }
    }


    public void printCities(ArrayList<Cities> cities) {

        System.out.println(String.format("%-10s %-20s %-15s %-10s %-12s", "City ID", "City Name", "Country", "District", "Population"));
// Loop over all cities in the list
        for (Cities city : cities)
        {
            String cityString =
                    String.format("%-10s %-20s %-15s %-10s %-12s",
                            city.ID, city.Name, city.CountryCode, city.District, city.Population);
            System.out.println(cityString);
        }
    }

}
