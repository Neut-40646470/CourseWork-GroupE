package com.napier.sem;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class MarkdownGenerationTest {

    @Test
    public void testMarkdownGeneration() {
        // Test case to ensure mock markdown data is generated correctly
        List<String> mockMarkdownData = TestManager.generateMockMarkdownData();

        assertTrue(!mockMarkdownData.isEmpty(), "Mock Markdown data is empty");
        assertTrue(mockMarkdownData.contains("# Sample Markdown File"), "Header not found");
        assertTrue(mockMarkdownData.contains("## Subheading"), "Subheading not found");
        assertTrue(mockMarkdownData.contains("This is a sample Markdown content."), "Content not found");
    }

    @Test
    public void testCountryReportGeneration() {
        // Test case to generate a country report markdown file
        ArrayList<Country> mockCountryData = TestManager.generateMockCountryDataByPopulationForContinent();
        MarkdownGenerator.generateCountryReportMarkdown(mockCountryData, "test_country_report.md");
    }

    @Test
    public void testTopNPopulatedCountriesReport() {
        // Test case to generate top N populated countries report
        ArrayList<Country> mockCountryData = TestManager.generateMockCountryDataByPopulationForContinent();
        StringBuilder sb = new StringBuilder();
        MarkdownGenerator.appendTopNPopulatedCountriesReport(sb, mockCountryData, 5);
    }

    @Test
    public void testCityReportGeneration() {
        // Test case to generate a city report Markdown file
        ArrayList<City> mockCityData = TestManager.generateMockCityData();
        MarkdownGenerator.generateCityReportMarkdown(mockCityData, "test_city_report.md");
    }

    @Test
    public void testTopNCitiesReportGeneration() {
        // Test case to generate a top N cities report Markdown file
        ArrayList<City> mockCityData = TestManager.generateMockCityData();
        MarkdownGenerator.generateTopNCitiesReportMarkdown(mockCityData, "test_top_cities_report.md", 5);
    }

    @Test
    public void testPopulationStatisticsReportGeneration() {
        // Test case to generate a population statistics report markdown
        MarkdownGenerator.generatePopulationStatisticsMarkdown("SampleLocation", 100000, 50000, 50000);
    }

    @Test
    public void testLanguageSpeakersReportGeneration() {
        // Test case to generate a language speakers report markdown
        Map<String, Integer> mockLanguageSpeakers = new HashMap<>();
        mockLanguageSpeakers.put("English", 100000000);
        mockLanguageSpeakers.put("Spanish", 50000000);
        MarkdownGenerator.generateLanguageSpeakersReport(mockLanguageSpeakers);
    }
//
//    @Test
//    public void testGenerateCountryReportMarkdown() {
//        // Create sample countries data
//        ArrayList<Country> countries = new ArrayList<>();
//        countries.add(new Country("TST", "TestCountry", "TestContinent", "TestRegion", 1000000, "TestCapital"));
//
//        // Call the method under test
//        MarkdownGenerator.generateCountryReportMarkdown(countries, "test_country_report.md");
//
//        // Verify that the file is generated with the expected content
//        assertFileContains("test_country_report.md", "# Country Report\n\n");
//        assertFileContains("test_country_report.md", "| Code | Name | Continent | Region | Population | Capital |\n");
//        assertFileContains("test_country_report.md", "| ---- | ---- | --------- | ------ | ---------- | ------- |\n");
//        assertFileContains("test_country_report.md", "| TST | TestCountry | TestContinent | TestRegion | 1000000 | TestCapital |\n");
//    }
//
//    @Test
//    public void testGenerateCityReportMarkdown() {
//        // Create sample cities data
//        ArrayList<City> cities = new ArrayList<>();
//        cities.add(new City("TestCity", "TST", "TestDistrict", 1000));
//
//        // Call the method under test
//        MarkdownGenerator.generateCityReportMarkdown(cities, "test_city_report.md");
//
//        // Verify that the file is generated with the expected content
//        assertFileContains("test_city_report.md", "# City Report\n\n");
//        assertFileContains("test_city_report.md", "| Name | Country | District | Population |\n");
//        assertFileContains("test_city_report.md", "| ---- | ------- | -------- | ---------- |\n");
//        assertFileContains("test_city_report.md", "| TestCity | TST | TestDistrict | 1000 |\n");
//    }

    // Helper method to assert that a file contains expected content
    private void assertFileContains(String filename, String expectedContent) {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(filename)))) {
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append("\n");
            }
            assertTrue(content.toString().contains(expectedContent), "File content does not match expected");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
