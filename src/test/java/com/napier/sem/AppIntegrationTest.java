package com.napier.sem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
public class AppIntegrationTest {
    static App app;
    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:33060", 0);
    }
    @Test
    void testGetAllCities() {
        ArrayList<Cities> cities = app.getAllCities();
        assertNotNull(cities, "Cities list should not be null");
        assertFalse(cities.isEmpty(), "Cities list should not be empty");
        // Assuming 'Kabul' is expected to be in the database
        assertTrue(cities.stream().anyMatch(c -> "Kabul".equals(c.getName())), "Should contain Kabul");
    }

}

