package com.napier.sem;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Get a database connection
        try (Connection connection = DatabaseConnector.getConnection()) {
            System.out.println("|           Main Class Has Been Executed           |");

            // Create CitiesReport and CountryReport instances
            CitiesReport citiesReport = new CitiesReport(connection);
            CountryReport countryReport = new CountryReport(connection);
            PopulationReport populationReport = new PopulationReport(connection);

            // Generate country report for the world, continent, and region
            countryReport.generateWorldCountryReport();
            countryReport.generateContinentCountryReport("Europe");
            countryReport.generateRegionCountryReport("Central Africa");

            // Generate additional reports for top N populated countries
            countryReport.generateTopNPopulatedCountriesReport(5);
            countryReport.generateTopNPopulatedCountriesInEurope(5);
            countryReport.generateTopNPopulatedCountriesInSouthernEurope(5);

            // Generate city reports
            citiesReport.generateAllCitiesInWorldReport();
            citiesReport.generateAllCitiesInContinentReport("Europe");
            citiesReport.generateAllCitiesInRegionReport("Central Africa");
            citiesReport.generateAllCitiesInCountryReport("France");
            citiesReport.generateAllCitiesInDistrictReport("Lombardia");
            citiesReport.generateTopNPopulatedCitiesInWorldReport(10);
            citiesReport.generateTopNPopulatedCitiesInContinentReport("Europe", 5); 
            citiesReport.generateTopNPopulatedCitiesInRegionReport("Southern Europe", 3);
            citiesReport.generateTopNPopulatedCitiesInCountryReport("ITA", 7); 
            citiesReport.generateTopNPopulatedCitiesInDistrictReport("Lombardia", 4); 

            // Generate population statistics reports
            populationReport.generatePopulationStatisticsByContinent("Europe");
            populationReport.generatePopulationStatisticsByRegion("Central Africa");
            populationReport.generatePopulationStatisticsByCountry("Germany");
            citiesReport.generateAllCapitalCitiesInWorldReport();

            // Generate language speakers report
            populationReport.generateLanguageSpeakersReport();

        } catch (SQLException e) {
            System.err.println("|           Main Class Could Not Be Executed           |");
            e.printStackTrace();
        }
    }
}
