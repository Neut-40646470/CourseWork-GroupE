package com.napier.sem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    // Initialise connection object
    private static Connection connection = null;


    public static Connection getConnection() {
        String JDBC_HOSTNAME = System.getenv("MYSQL_HOSTNAME");
        String JDBC_URL = "jdbc:mysql://" + JDBC_HOSTNAME + ":3306/world";
        String USERNAME = "root";
        String PASSWORD = "123";

        try {
            // Check if connection is null or closed and establish new connection if needed
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}