package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static App app;
//    static Connection con;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printCitiesTestNull()
    {
        app.printCities(null);
    }

    @Test
    void getCitiesTestNull(){
        app.getAllCities();
    }

    @Test
    void citiesPrintingTest()
    {
        ArrayList<Cities> cities = new ArrayList<Cities>();
//        cities.add(null);
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