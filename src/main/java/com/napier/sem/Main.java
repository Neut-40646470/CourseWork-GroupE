package com.napier.sem;
import java.io.IOException;
import java.sql.ResultSet;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        App app = new App();

        if (args.length < 1) {
            app.connect("localhost:33060", 0);
        } else {
            app.connect("db:3306", 30000);
        }

        ResultSet resultSetContinent = app.executeQueryFromFile("src/main/resources/ALLCITIESfromSELECTEDCONTINENT.sql");
        ResultSet resultSetDistrict = app.executeQueryFromFile("src/main/resources/ALLCITIESfromSELECTEDDISTRICT.sql");
        ResultSet resultSetRegion = app.executeQueryFromFile("src/main/resources/ALLCITIESfromSELECTEDREGION.sql");

        if (resultSetContinent != null) {
            app.generateCityReportFromResultSet(resultSetContinent, "City_Report_Continent.md");
        }
        if (resultSetDistrict != null) {
            app.generateCityReportFromResultSet(resultSetDistrict, "City_Report_District.md");
        }
        if (resultSetRegion != null) {
            app.generateCityReportFromResultSet(resultSetRegion, "City_Report_Region.md");
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