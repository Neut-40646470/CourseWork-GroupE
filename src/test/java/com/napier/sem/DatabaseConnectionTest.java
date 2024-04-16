package com.napier.sem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseConnectionTest {

    @Test
    public void testPopulationStatistics() {
        // Simulate getting mock population data from TestManager
        ArrayList<PopulationStatistics> mockPopulationData = TestManager.generateMockPopulationDataForCountry();

        // Perform assertions based on the mock data
        assertEquals(1, mockPopulationData.size());
        PopulationStatistics population = mockPopulationData.get(0);
        assertEquals("Aruba", population.getLocation());
        assertEquals(103000, population.getTotalPopulation());
        assertEquals(29034, population.getPopulationInCities());
        assertEquals(73966, population.getPopulationNotInCities());
    }

    @Test
    public void testCountryDataByPopulationForContinent() {
        // Simulate getting mock country data from TestManager
        ArrayList<Country> mockCountryData = TestManager.generateMockCountryDataByPopulationForContinent();

        // Perform assertions based on the mock data
        assertEquals(1, mockCountryData.size());
        Country country = mockCountryData.get(0);
        assertEquals("BEL", country.getCode());
        assertEquals("Belgium", country.getName());
        assertEquals("Europe", country.getContinent());
        assertEquals("Western Europe", country.getRegion());
        assertEquals(10239000, country.getPopulation());
        assertEquals("Brussels", country.getCapital());
    }

//    @Test
//    public void testLanguageSpeakersReport() {
//        // Test case to verify that generateLanguageSpeakersReport method is called
//        // Create a mock population report
//        PopulationReport populationReport = Mockito.mock(PopulationReport.class);
//
//        // Mock behavior of generateLanguageSpeakersReport method
//        Mockito.doNothing().when(populationReport).generateLanguageSpeakersReport();
//
//        // Call the method under test
//        populationReport.generateLanguageSpeakersReport();
//
//        // Verify that the method was called once
//        Mockito.verify(populationReport, Mockito.times(1)).generateLanguageSpeakersReport();
//    }
}
