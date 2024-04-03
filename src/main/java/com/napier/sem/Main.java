package com.napier.sem;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        App app = new App();

        if (args.length < 1) {
            app.connect("localhost:33060", 0);
        } else {
            app.connect("db:3306", 30000);
        }

        // Generate city reports
        ArrayList<Cities> cities = app.getAllCities();
        if (cities != null) {
            app.generateCityReportMarkdown(cities, "city_report.md");
            app.printCitiesFromContinent("", "src/main/resources/ALLCITIESfromSELECTEDCONTINENT.sql");
            app.generateCityReportMarkdown(app.getAllCitiesFromContinent(""), "city_by_continent.md");
            app.printCitiesFromDistrict("", "src/main/resources/ALLCITIESfromSELECTEDDISTRICT.sql");
            app.generateCityReportMarkdown(app.getAllCitiesFromDistrict(""), "city_by_district.md");
            app.printCitiesFromRegion("", "src/main/resources/ALLCITIESfromSELECTEDREGION.sql");
            app.generateCityReportMarkdown(app.getAllCitiesFromRegion(""), "city_by_region.md");
        } else {
            System.out.println("No cities found.");
        }

        // Generate country reports
        ArrayList<Country> countries = app.getAllCountries();
        if (countries != null) {
            app.generateCountryReportMarkdown(countries, "country_report.md");
            app.printCountriesFromRegion("", "src/main/resources/ALLCOUNTRIESfromSELECTEDREGION.sql");
            app.generateCountryReportMarkdown(app.getAllCountriesFromRegion(""), "country_by_Region.md");
            app.printCountriesFromContinent("", "src/main/resources/ALLCOUNTRIESfromSELECTEDCONTINENT.sql");
            app.generateCountryReportMarkdown(app.getAllCountriesFromContinent(""), "country_by_continent.md");
        } else {
            System.out.println("No countries found.");
        }

        // Disconnect from the database
        app.disconnect();
    }
}




//  List of File Paths to copy into the report printing pointer ^^
//              Delete the one you used before Commit and Push



//src/main/resources/AllCapitalCitiesByLargestToSmallest(Continent).sql
//src/main/resources/AllCapitalCitiesByLargestToSmallest(Region).sql
//src/main/resources/AllCapitalCitiesByLargestToSmallest(World).sql
//src/main/resources/ALLCITIESfromSELECTEDCONTINENT.sql
//src/main/resources/ALLCITIESfromSELECTEDDISTRICT.sql
//src/main/resources/ALLCITIESfromSELECTEDREGION.sql
//src/main/resources/ALLCITIESfromWORLD.sql

//src/main/resources/ALLCOUNTRIESfromSELECTEDREGION.sql
//src/main/resources/ALLCOUNTRIESfromWORLD.sql
//src/main/resources/CONTINENT-FetchpopulationofALLCITIESfromSELECTEDCONTINENT.sql
//src/main/resources/COUNTRY-FetchpopulationofCITIESinSELECTEDCOUNTRY.sql
//src/main/resources/PopulationOfCity.sql
//src/main/resources/PopulationOfContinent.sql
//src/main/resources/PopulationOfCountry.sql
//src/main/resources/PopulationOfDistrict.sql
//src/main/resources/PopulationOfRegion.sql
//src/main/resources/PopulationOfWorld.sql
//src/main/resources/TopNPopulatedCapitalCities(Continent).sql
//src/main/resources/TopNPopulatedCapitalCities(Region).sql
//src/main/resources/TopNPopulatedCapitalCities(World).sql
//src/main/resources/TopNpopulatedCITIESfromCONTINENT.sql
//src/main/resources/TopNpopulatedCITIESfromCOUNTRY.sql
//src/main/resources/TopNpopulatedCITIESfromDISTRICT.sql
//src/main/resources/TopNpopulatedCITIESfromREGION.sql
//src/main/resources/TopNpopulatedCITIESfromWORLD.sql
//src/main/resources/TopNpopulatedCOUNTRIESfromCONTINENT.sql
//src/main/resources/TopNpopulatedCOUNTRIESfromREGION.sql
//src/main/resources/TopNpopulatedCOUNTRIESfromWORLD.sql
//src/main/resources/WORLD-FetchpopulationofCAPITIALCITIES.sql
//src/main/resources/WORLD-FetchpopulationofCITIES.sql