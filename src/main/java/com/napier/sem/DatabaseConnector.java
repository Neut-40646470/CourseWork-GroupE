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

        int retries = 30; // Number of retries for connecting to the database
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                Thread.sleep(1000);
                // Attempt a connection to the database
                connection = DriverManager.getConnection(
                        "jdbc:mysql://mysql:3306/world?useSSL=false&allowPublicKeyRetrieval=true",
                        "root", "123");
                System.out.println("Successfully connected");
                break; // Exit loop if connection is successful
            } catch (SQLException | InterruptedException e) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(e.getMessage());
                // Properly handle thread interruption
                if (e instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return connection; // Return the established connection or null if failed
    }
}
