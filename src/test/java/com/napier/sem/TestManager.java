package com.napier.sem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mockito.Mockito;

public class TestManager {

    // Method to generate a mock database connection
    public static Connection generateMockDatabaseConnection() throws SQLException {
        Connection connection = Mockito.mock(Connection.class);
        return connection;
    }

    // Method to generate mock population data for a country
    public static ArrayList<PopulationStatistics> generateMockPopulationDataForCountry() {
        ArrayList<PopulationStatistics> mockPopulationData = new ArrayList<>();
        // Create a mock population statistics object and add it to the list
        PopulationStatistics population1 = new PopulationStatistics("Aruba", 103000, 29034, 73966);
        mockPopulationData.add(population1);
        return mockPopulationData;
    }

    // Method to generate mock country data by population for a continent
    public static ArrayList<Country> generateMockCountryDataByPopulationForContinent() {
        ArrayList<Country> mockCountryData = new ArrayList<>();
        // Create a mock country object and add it to the list
        Country country1 = new Country("BEL", "Belgium", "Europe", "Western Europe", 10239000, "Brussels");
        mockCountryData.add(country1);
        return mockCountryData;
    }

    // Method to generate mock markdown data
    public static List<String> generateMockMarkdownData() {
        List<String> mockMarkdownData = new ArrayList<>();
        // Add mock markdown content to the list
        mockMarkdownData.add("# Sample Markdown File");
        mockMarkdownData.add("## Subheading");
        mockMarkdownData.add("This is a sample Markdown content.");
        return mockMarkdownData;
    }

    // Method to generate mock city data
    public static ArrayList<City> generateMockCityData() {
        ArrayList<City> mockCityData = new ArrayList<>();
        // Create mock city objects and add them to the list
        City city1 = new City("Tokyo", "Japan", "Tokyo", 38140000);
        City city2 = new City("Shanghai", "China", "Shanghai", 27380000);
        City city3 = new City("Delhi", "India", "Delhi", 27450000);
        City city4 = new City("Beijing", "China", "Beijing", 21710000);
        City city5 = new City("Mumbai", "India", "Maharashtra", 23355000);
        mockCityData.add(city1);
        mockCityData.add(city2);
        mockCityData.add(city3);
        mockCityData.add(city4);
        mockCityData.add(city5);
        return mockCityData;
    }

}