package com.napier.sem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    // Initialise connection object
    private static Connection connection = null;

    // Primary method to get connection
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 1;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                Thread.sleep(150000);
                // Try connecting to the MySQL service named 'mysql' running at port 3306
                connection = DriverManager.getConnection(
                        "jdbc:mysql://mysql:3306/world?allowPublicKeyRetrieval=true&useSSL=false",
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

        return connection;
    }

    // Secondary method for local fallback connection
    public static Connection getConnection1() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 1;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // No delay needed for local fallback
                Thread.sleep(10000);
                // Fallback connection to localhost at port 33060
                connection = DriverManager.getConnection(
                        "jdbc:mysql://db:33060/world??allowPublicKeyRetrieval=true&useSSL=false",
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

        return connection;
    }
}