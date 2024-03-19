package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);

    }

    @Test
    void testGetEmployee()
    {
        Cities cit = app.getCities(1);
        assertEquals(cit.ID, 1);
        assertEquals(cit.Name, "Kabul");
        assertEquals(cit.CountryCode, "AFG");
        assertEquals(cit.District,"Kabol" );
        assertEquals(cit.Population, 1780000);
    }
}