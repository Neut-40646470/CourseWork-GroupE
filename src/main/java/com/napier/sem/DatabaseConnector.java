package com.napier.sem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static Connection connection = null;

    public static synchronized Connection connect() {
        if (connection != null) return connection;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            return null;
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                Thread.sleep(1000);
                connection = DriverManager.getConnection(
                        "jdbc:mysql://mysql:3306/world?useSSL=false&allowPublicKeyRetrieval=true",
                        "root", "123");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException | InterruptedException e) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(e.getMessage());
                if (e instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return connection;
    }
}
