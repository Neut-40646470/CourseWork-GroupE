package com.napier.sem;

import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {
        App app = new App();

        if (args.length < 1) {
            app.connect("localhost:33060", 0);
        } else {
            app.connect("db:3306", 30000);
        }

        ResultSet allCitiesByWorld = app.executeQueryFromFile("src/main/resources/ALLCITIESfromWORLD.sql"); // Print Report for allCitiesByWorld taking the file path
        ResultSet TopNCitiesByWorld = app.executeQueryFromFile("src/main/resources/TopNpopulatedCITIESfromWORLD.sql");
        ResultSet allCitiesByContinent = app.executeQueryFromFile("src/main/resources/ALLCITIESfromSELECTEDCONTINENT.sql");
        ResultSet TopNCitiesByContinent = app.executeQueryFromFile("src/main/resources/TopNpopulatedCITIESfromCONTINENT.sql");
        ResultSet allCitiesByCountry = app.executeQueryFromFile("src/main/resources/ALLCITIESfromSELECTEDCOUNTRY.sql");
        ResultSet TopNCitiesByCountry = app.executeQueryFromFile("src/main/resources/TopNpopulatedCITIESfromCOUNTRY.sql");
        ResultSet allCitiesByDistrict = app.executeQueryFromFile("src/main/resources/ALLCITIESfromSELECTEDDISTRICT.sql");
        ResultSet TopNCitiesByDistrict = app.executeQueryFromFile("src/main/resources/TopNpopulatedCITIESfromDISTRICT.sql");
        ResultSet allCitiesByRegion = app.executeQueryFromFile("src/main/resources/ALLCITIESfromSELECTEDREGION.sql");
        ResultSet TopNCitiesByRegion = app.executeQueryFromFile("src/main/resources/TopNpopulatedCITIESfromREGION.sql");

        if (allCitiesByWorld != null) {
            app.generateCityReportFromResultSet(allCitiesByWorld, "City_Report_World.md");
            app.printCitiesFromWorld("", "src/main/resources/ALLCITIESfromWORLD.sql");
        }
        if(TopNCitiesByWorld != null){
            app.generateCityReportFromResultSet(TopNCitiesByWorld, "Top_N_City_Report_World.md");
            app.printTopNCitiesFromWorld("", "src/main/resources/TopNpopulatedCITIESfromWORLD.sql");
        }
        if (allCitiesByContinent != null) {
            app.generateCityReportFromResultSet(allCitiesByContinent, "City_Report_Continent.md");
            app.printCitiesFromContinent("", "src/main/resources/ALLCITIESfromSELECTEDCONTINENT.sql");
        }
        if (TopNCitiesByContinent != null) {
            app.generateCityReportFromResultSet(TopNCitiesByContinent, "Top_N_City_Report_Continent.md");
            app.printTopNCitiesFromContinent("", "src/main/resources/TopNpopulatedCITIESfromCONTINENT.sql");
        }
        if (allCitiesByCountry != null) {
            app.generateCityReportFromResultSet(allCitiesByCountry, "City_Report_Country.md");
            app.printCitiesFromCountry("", "src/main/resources/ALLCITIESfromSELECTEDCOUNTRY.sql");
        }
        if (TopNCitiesByCountry != null) {
            app.generateCityReportFromResultSet(TopNCitiesByCountry, "Top_N_City_Report_Country.md");
            app.printTopNCitiesFromCountry("", "src/main/resources/TopNpopulatedCITIESfromCOUNTRY.sql");
        }
        if (allCitiesByDistrict != null) {
            app.generateCityReportFromResultSet(allCitiesByDistrict, "City_Report_District.md");
            app.printCitiesFromDistrict("", "src/main/resources/ALLCITIESfromSELECTEDDISTRICT.sql");
        }
        if (TopNCitiesByDistrict != null) {
            app.generateCityReportFromResultSet(TopNCitiesByDistrict, "Top_N_City_Report_District.md");
            app.printTopNCitiesFromDistrict("","src/main/resources/TopNpopulatedCITIESfromDISTRICT.sql");
        }
        if (allCitiesByRegion != null) {
            app.generateCityReportFromResultSet(allCitiesByRegion, "City_Report_Region.md");
            app.printCitiesFromRegion("", "src/main/resources/ALLCITIESfromSELECTEDREGION.sql");
        }
        if (TopNCitiesByRegion != null) {
            app.generateCityReportFromResultSet(TopNCitiesByRegion, "Top_N_City_Report_Region.md");
            app.printTopNCitiesFromRegion("","src/main/resources/TopNpopulatedCITIESfromREGION.sql");
        }

        ResultSet allCountriesByWorld = app.executeQueryFromFile("src/main/resources/ALLCOUNTRIESfromWORLD.sql");
        ResultSet TopNCountriesByWorld = app.executeQueryFromFile("src/main/resources/TopNpopulatedCOUNTRIESfromWORLD.sql");
        ResultSet allCountriesByContinent = app.executeQueryFromFile("src/main/resources/ALLCOUNTRIESfromSELECTEDCONTINENT.sql");
        ResultSet TopNCountriesByContinent = app.executeQueryFromFile("src/main/resources/TopNpopulatedCOUNTRIESfromCONTINENT.sql");
        ResultSet allCountriesByRegion = app.executeQueryFromFile("src/main/resources/ALLCOUNTRIESfromSELECTEDREGION.sql");
        ResultSet TopNCountriesByRegion = app.executeQueryFromFile("src/main/resources/TopNpopulatedCOUNTRIESfromREGION.sql");

        app.generateCountryReportFromResultSet(allCountriesByContinent, "Country_Report_Continent.md");
        app.printCountryFromContinent("src/main/resources/ALLCOUNTRIESfromSELECTEDCONTINENT.sql");

        app.generateCountryReportFromResultSet(TopNCountriesByContinent, "Top_N_Country_Report_Continent.md");
        app.printTopNCountriesFromContinent("src/main/resources/TopNpopulatedCOUNTRIESfromCONTINENT.sql");

        app.generateCountryReportFromResultSet(allCountriesByRegion, "Country_Report_Region.md");
        app.printCountryFromRegion("src/main/resources/ALLCOUNTRIESfromSELECTEDREGION.sql");

        app.generateCountryReportFromResultSet(TopNCountriesByRegion, "Top_N_Country_Report_Region.md");
        app.printTopNCountriesFromRegion("src/main/resources/TopNpopulatedCOUNTRIESfromREGION.sql");

        app.generateCountryReportFromResultSet(allCountriesByWorld, "Country_Report_World.md");
        app.printCountryFromWorld("src/main/resources/ALLCOUNTRIESfromWORLD.sql");

        app.generateCountryReportFromResultSet(TopNCountriesByWorld, "Top_N_Country_Report_World.md");
        app.printTopNCountriesFromWorld("src/main/resources/TopNpopulatedCOUNTRIESfromWORLD.sql");



        //Capital Cities bellow
        ResultSet allCapitalCitiesFromContinent = app.executeQueryFromFile("src/main/resources/AllCapitalCitiesByLargestToSmallest(Continent).sql");

        app.generateCapitalCityReportFromResultSet(allCapitalCitiesFromContinent, "All_Capital_Cities_Report_Continent.md");
        app.printCapitalCitiesFromContinent("","src/main/resources/AllCapitalCitiesByLargestToSmallest(Continent).sql");

        // Disconnect from the database
        app.disconnect();
    }
}



//  List of File Paths to copy into the report printing pointer ^^
//              Delete the one you used before Commit and Push


//src/main/resources/AllCapitalCitiesByLargestToSmallest(Continent).sql
//src/main/resources/AllCapitalCitiesByLargestToSmallest(Region).sql
//src/main/resources/AllCapitalCitiesByLargestToSmallest(World).sql

//src/main/resources/ALLCOUNTRIESfromSELECTEDCONTINENT.sql
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

//   ** DONE LIST **
//src/main/resources/ALLCITIESfromSELECTEDCONTINENT.sql
//src/main/resources/ALLCITIESfromSELECTEDDISTRICT.sql
//src/main/resources/ALLCITIESfromSELECTEDREGION.sql
//src/main/resources/ALLCITIESfromWORLD.sql