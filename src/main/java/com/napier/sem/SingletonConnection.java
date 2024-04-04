package com.napier.sem;
import java.sql.*;

public class SingletonConnection {
    private static SingletonConnection instance = null;
    private Connection connection = null;

    private SingletonConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = connectDB();
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }
    }

    public static SingletonConnection getInstance() {
        if(instance == null){
            instance = new SingletonConnection();
        }
        return instance;
    }

    private Connection connectDB() {
        String url = "jdbc:mysql://db:3306/world?useSSL=false";
        String user = "root";
        String password = "123";

        for (int i = 0; i < 10; ++i) {
            try {
                Thread.sleep(30000);
                return DriverManager.getConnection(url, user, password);
            } catch (SQLException | InterruptedException e) {
                System.out.println("Failed to connect to database attempt " + i);
            }
        }
        return null;
    }

    public Connection getConnection(){
        return this.connection;
    }

    public void disconnect(){
        if(this.connection != null){
            try {
                this.connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection to database");
            }
        }
    }
}
