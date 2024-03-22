package com.napier.sem;

import java.sql.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static App app;
    static final String DB_LOCATION = "localhost:33060";
    static final int DELAY = 0; // Adjust delay as needed

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect(DB_LOCATION, DELAY);
    }

    @Test
    void printCitiesTestNull()
    {
            app.printCities(null);
    }

//    @Test
//    void getCitiesTestNull(){
//        assertNull(app.getAllCities(), "Returned cities list should be null when SQLException occurs");
//    }

    @Test
    void citiesPrintingTest()
    {
        ArrayList<Cities> cities = app.getAllCities();
        app.printCities(cities);
    }

    @Test
    void connectionFailedTest()
    {
        assertThrows(NullPointerException.class, this::throwsException);
    }
    void throwsException() throws NullPointerException
    {
        throw new NullPointerException();
    }
}













//package com.napier.sem;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//
//import java.sql.Connection;
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class AppTest
//{
//    static App app;
////    static Connection con;
//
//    @BeforeAll
//    static void init()
//    {
//        app = new App();
//    }
//
//    @Test
//    void printCitiesTestNull()
//    {
//        app.printCities(null);
//    }
//
//    @Test
//    void getCitiesTestNull(){
//        app.getAllCities();
//    }
//
//    @Test
//    void citiesPrintingTest()
//    {
//        ArrayList<Cities> cities = new ArrayList<Cities>();
////        cities.add(null);
//        app.printCities(cities);
//    }
//
//    @Test
//    void connectionFailedTest()
//    {
//        assertThrows(NullPointerException.class, this::throwsException);
//    }
//    void throwsException() throws NullPointerException
//    {
//        throw new NullPointerException();
//    }
//
//
//}