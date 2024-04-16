package com.napier.sem;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Get a database connection
        Connection con = DatabaseConnector.getConnection();
        try{
        // Check if the connection is null and attempt to connect to the secondary source
        if (con == null) {
            System.out.println("Primary database connection failed. Attempting secondary connection...");
            con = DatabaseConnector.getConnection1();
            if (con == null) {
                System.err.println("Failed to establish any database connection.");
                return; // Exit if no connection could be established
            }
        }

            System.out.println("|           Main Class Has Been Executed           |");

            // Create CitiesReport and CountryReport instances
            CitiesReport citiesReport = new CitiesReport(con);
            CountryReport countryReport = new CountryReport(con);
            PopulationReport populationReport = new PopulationReport(con);

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

            // Generate Capital Cities Reports
            citiesReport.generateAllCapitalCitiesInWorldReport();
            //citiesReport.generateAllCapitalCitiesInCountryReport("");
            citiesReport.generateAllCapitalCitiesInContinentReport("");
            citiesReport.generateAllCapitalCitiesInRegionReport("");
            citiesReport.generateTopNPopulatedCapitalCitiesInWorldReport(69);
            citiesReport.generateTopNPopulatedCapitalCitiesInContinentReport("", 3);
            citiesReport.generateTopNPopulatedCapitalCitiesInRegionReport("", 20);

            // Generate population statistics reports
            populationReport.generatePopulationStatisticsByContinent("Europe");
            populationReport.generatePopulationStatisticsByRegion("Central Africa");
            populationReport.generatePopulationStatisticsByCountry("Germany");

            // Generate language speakers report
            populationReport.generateLanguageSpeakersReport();

        }

        finally {
            // Ensure the connection is closed after use
            if (con != null) {
                try {
                    con.close();}
                catch (SQLException e) {
                        System.err.println("|           Main Class Could Not Be Executed           |");
                        e.printStackTrace();
                    }
            }
        }
    }
}