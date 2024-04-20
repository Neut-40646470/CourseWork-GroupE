package com.napier.sem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class MarkdownGenerator {
    // Directory for saving reports
    private static final String REPORTS_DIRECTORY = "/tmp/reports/";

    public static void generateCountryReportMarkdown(ArrayList<Country> countries, String filename) {
        // Check if countries is empty
        if (countries == null || countries.isEmpty()) {
            System.out.println("No countries to generate report.");
            return;
        }

        // Build report
        StringBuilder sb = new StringBuilder();
        sb.append("# Country Report\n\n");
        sb.append("| Code | Name | Continent | Region | Population | Capital |\n");
        sb.append("| ---- | ---- | --------- | ------ | ---------- | ------- |\n");
        for (Country country : countries) {
            sb.append("| ").append(country.getCode()).append(" | ").append(country.getName()).append(" | ")
                    .append(country.getContinent()).append(" | ").append(country.getRegion()).append(" | ")
                    .append(country.getPopulation()).append(" | ").append(country.getCapital() != null ? country.getCapital() : "N/A")
                    .append(" |\n");
        }

        saveReportToFile(sb.toString(), filename);
    }


    public static void appendTopNPopulatedCountriesReport(StringBuilder sb, ArrayList<Country> countries, int n) {
        // Check if n has been set and if countries is empty
        if (n <= 0 || countries == null || countries.isEmpty()) {
            return;
        }

        // Sort countries list in descending order
        Collections.sort(countries, Comparator.comparingInt(Country::getPopulation).reversed());

        // Build report
        sb.append("\n\n## Additional Reports\n\n");
        sb.append("### Top ").append(n).append(" Populated Countries in the World\n\n");
        sb.append("| Rank | Name | Population |\n");
        sb.append("| ---- | ---- | ---------- |\n");
        for (int i = 0; i < n && i < countries.size(); i++) {
            Country country = countries.get(i);
            sb.append("| ").append(i + 1).append(" | ").append(country.getName()).append(" | ").append(country.getPopulation()).append(" |\n");
        }
    }

    public static void generateCityReportMarkdown(ArrayList<City> cities, String filename) {
        if (cities == null || cities.isEmpty()) {
            System.out.println("No cities to generate report.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("# City Report\n\n");
        sb.append("| Name | Country | District | Population |\n");
        sb.append("| ---- | ------- | -------- | ---------- |\n");

        for (City city : cities) {
            sb.append("| ")
                    .append(city.getName()).append(" | ")
                    .append(city.getCountry()).append(" | ")
                    .append(city.getDistrict()).append(" | ")
                    .append(city.getPopulation()).append(" |\n");
        }

        saveReportToFile(sb.toString(), filename);
    }

    public static void generateCapitalCityReportMarkdown(ArrayList<CapitalCity> capitalCities, String filename) {
        if (capitalCities == null || capitalCities.isEmpty()) {
            System.out.println("No capital cities to generate report.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("# Capital City Report\n\n");
        sb.append("| Name | Country | Population |\n");
        sb.append("| ---- | ------- | ---------- |\n");

        for (CapitalCity city : capitalCities) {
            sb.append("| ")
                    .append(city.getName()).append(" | ")
                    .append(city.getCountry()).append(" | ")
                    .append(city.getPopulation()).append(" |\n");
        }

        saveReportToFile(sb.toString(), filename);
    }

    public static void generateTopNCitiesReportMarkdown(ArrayList<City> cities, String filename, int n) {
        // Check if n has been set and if cities is empty
        if (cities == null || cities.isEmpty() || n <= 0) {
            System.out.println("Invalid input for generating top cities report.");
            return;
        }

        // Sort cities list in descending order
        Collections.sort(cities, Comparator.comparingInt(City::getPopulation).reversed());

        // Build report
        StringBuilder sb = new StringBuilder();
        sb.append("# Top ").append(n).append(" Cities Report\n\n");
        sb.append("| Rank | Name | Country | District | Population |\n");
        sb.append("| ---- | ---- | ------- | -------- | ---------- |\n");
        for (int i = 0; i < n && i < cities.size(); i++) {
            City city = cities.get(i);
            sb.append("| ").append(i + 1).append(" | ").append(city.getName()).append(" | ")
                    .append(city.getCountry()).append(" | ").append(city.getDistrict()).append(" | ")
                    .append(city.getPopulation()).append(" |\n");
        }

        saveReportToFile(sb.toString(), filename);
    }

    public static void generatePopulationStatisticsMarkdown(String location, int totalPopulation, int populationInCities, int populationNotInCities) {
        StringBuilder sb = new StringBuilder();
        sb.append("# Population Statistics for ").append(location).append("\n\n");
        sb.append("Total Population: ").append(totalPopulation).append("\n");
        sb.append("Population Living in Cities: ").append(populationInCities).append("\n");
        sb.append("Population Not Living in Cities: ").append(populationNotInCities).append("\n");

        saveReportToFile(sb.toString(), "Population_Statistics_" + location + ".md");
    }

    public static void generateLanguageSpeakersReport(Map<String, Integer> languageSpeakers) {
        if (languageSpeakers == null || languageSpeakers.isEmpty()) {
            System.out.println("No language speakers data available.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("# Language Speakers Report\n\n");
        sb.append("| Language | Number of Speakers |\n");
        sb.append("| -------- | ------------------ |\n");
        for (Map.Entry<String, Integer> entry : languageSpeakers.entrySet()) {
            sb.append("| ").append(entry.getKey()).append(" | ").append(entry.getValue()).append(" |\n");
        }

        saveReportToFile(sb.toString(), "Language_Speakers_Report.md");
    }

    private static void saveReportToFile(String content, String filename) {
        try {
            File directory = new File(REPORTS_DIRECTORY);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

            File reportFile = new File(directory, filename);
            BufferedWriter writer = new BufferedWriter(new FileWriter(reportFile));
            writer.write(content);
            writer.close();
            System.out.println("Report generated: " + reportFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error generating report: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
