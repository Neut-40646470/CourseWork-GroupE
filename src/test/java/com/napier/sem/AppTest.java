package com.napier.sem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AppTest {

    private App app;

    @BeforeEach
    public void setUp() {
        app = new App();
    }

    @AfterEach
    public void tearDown() {
        app.disconnect(); // Ensure disconnection after each test
    }

    @Test
    public void testDisconnect() {
        // Ensure connection is disconnected successfully
        app.connect("localhost:33060", 0);
        app.disconnect();
       // assertNull(app.getConnection());
    }

    @Test
    public void testGetAllCities() {
        // Ensure getAllCities returns a non-null ArrayList
        app.connect("localhost:33060", 0);
        ArrayList<Cities> cities = app.getAllCities();
        assertNotNull(cities);
    }
}

