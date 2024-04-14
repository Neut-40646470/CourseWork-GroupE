package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class AppIntegrationTest {
        static App app;
        @BeforeAll
        static void init()
        {
            app = new App();
            app.connect("localhost:33060", 30000);

        }
    @Test
    void testGetAllCities() {
        ArrayList<Cities> cities = app.getAllCities();
        assertNotNull(cities);
        assertFalse(cities.isEmpty());  // Make sure cities are retrieved
        // Example assertion on a specific city if you have predictable data
        assertEquals("Kabul", cities.get(0).getName());
    }

}

