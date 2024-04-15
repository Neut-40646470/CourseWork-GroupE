package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CountryReport {
    private Connection con;

    public CountryReport(Connection con) {
        this.con = con;
    }

    public void generateWorldCountryReport() {
        ArrayList<Country> countries = getAllCountries(); 
        MarkdownGenerator.generateCountryReportMarkdown(countries, "World_Country_Report.md");
    }

    public void generateContinentCountryReport(String continent) {
        ArrayList<Country> countries = getCountriesByContinent(continent); 
        MarkdownGenerator.generateCountryReportMarkdown(countries, "Continent_Country_Report_" + continent + ".md");
    }

    public void generateRegionCountryReport(String region) {
        ArrayList<Country> countries = getCountriesByRegion(region);
        MarkdownGenerator.generateCountryReportMarkdown(countries, "Region_Country_Report_" + region + ".md");
    }

    public void generateTopNPopulatedCountriesReport(int N) {
        ArrayList<Country> countries = getTopPopulationCountriesInWorld(N);
        MarkdownGenerator.generateCountryReportMarkdown(countries, "Top_"+ N +"_Populated_Countries_Report.md");
    }

    public void generateTopNPopulatedCountriesInEurope(int N) {
        ArrayList<Country> countries = getTopPopulationCountriesInContinent("Europe", N);
        MarkdownGenerator.generateCountryReportMarkdown(countries, "Top_"+ N +"_Populated_Countries_In_Europe_Report.md");
    }

    public void generateTopNPopulatedCountriesInSouthernEurope(int N) {
        ArrayList<Country> countries = getTopPopulationCountriesInRegion("Southern Europe", N);
        MarkdownGenerator.generateCountryReportMarkdown(countries, "Top_"+ N +"_Populated_Countries_In_Southern_Europe_Report.md");
    }

    private ArrayList<Country> getAllCountries() {
        return getCountriesByQuery("SELECT * FROM country");
    }

    private ArrayList<Country> getCountriesByContinent(String continent) {
        String query = "SELECT * FROM country WHERE Continent = ?";
        return getCountriesByQuery(query, continent);
    }

    private ArrayList<Country> getCountriesByRegion(String region) {
        String query = "SELECT * FROM country WHERE Region = ?";
        return getCountriesByQuery(query, region);
    }

    private ArrayList<Country> getTopPopulationCountriesInWorld(int N) {
        ArrayList<Country> countries = getAllCountries();
        Collections.sort(countries, Comparator.comparingInt(Country::getPopulation).reversed());
        return new ArrayList<>(countries.subList(0, Math.min(N, countries.size())));
    }

    private ArrayList<Country> getTopPopulationCountriesInContinent(String continent, int N) {
        ArrayList<Country> countries = getCountriesByContinent(continent);
        Collections.sort(countries, Comparator.comparingInt(Country::getPopulation).reversed());
        return new ArrayList<>(countries.subList(0, Math.min(N, countries.size())));
    }

    private ArrayList<Country> getTopPopulationCountriesInRegion(String region, int N) {
        ArrayList<Country> countries = getCountriesByRegion(region);
        Collections.sort(countries, Comparator.comparingInt(Country::getPopulation).reversed());
        return new ArrayList<>(countries.subList(0, Math.min(N, countries.size())));
    }

    private ArrayList<Country> getCountriesByQuery(String query, String... parameters) {
        ArrayList<Country> countries = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            for (int i = 0; i < parameters.length; i++) {
                stmt.setString(i + 1, parameters[i]);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String code = rs.getString("Code");
                String name = rs.getString("Name");
                String continent = rs.getString("Continent");
                String region = rs.getString("Region");
                int population = rs.getInt("Population");
                String capital = rs.getString("Capital");
                countries.add(new Country(code, name, continent, region, population, capital));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving countries: " + e.getMessage());
        }
        return countries;
    }
}
