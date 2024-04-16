package com.napier.sem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static Connection connection = null;  // Holds a singleton instance of the connection

    // Synchronized method ensuring thread saftey
    public static synchronized Connection connect() {
        // Check if a connection already exists and return it if so
        if (connection != null) return connection;

        // Attempt to load the JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            return null;
        }

        // Connection URLs
        String[] connectionUrls = {
                "jdbc:mysql://mysql:3306/world?useSSL=false&allowPublicKeyRetrieval=true", // Docker service URL
                "jdbc:mysql://localhost:3307/world?useSSL=false&allowPublicKeyRetrieval=true" // Local fallback URL
        };

        int retries = 30; // Number of retries for connecting to the database
        for (int i = 0; i < retries; ++i) {
            for (String url : connectionUrls) {
                System.out.println("Connecting to database using URL: " + url);
                try {
                    Thread.sleep(1000); // Sleep before trying to connect
                    connection = DriverManager.getConnection(
                            url,
                            "root", "123");
                    System.out.println("Successfully connected using URL: " + url);
                    return connection; // Successful connection, return it
                } catch (SQLException | InterruptedException e) {
                    System.out.println("Failed to connect using URL: " + url + ", attempt " + i);
                    System.out.println(e.getMessage());
                    // Properly handle thread interruption
                    if (e instanceof InterruptedException) {
                        Thread.currentThread().interrupt();
                        return null; // Exit if thread is interrupted
                    }
                }
            }
        }
        return connection; // Return the connection or null if all retries failed
    }
}
