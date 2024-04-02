package com.napier.sem;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        App a = new App();

        if (args.length < 1) {
            a.connect("localhost:33060", 0);
        } else {
            a.connect("db:3306", 30000);
        }

        ArrayList<Cities> cities = a.getAllCities();
        if (cities != null) {
            for (Cities city : cities) {
                System.out.println(city.ID + ", " + city.Name + ", " + city.CountryCode + ", " + city.District + ", " + city.Population);
            }
        } else {
            System.out.println("No cities found.");
        }

        // Execute queries and generate reports
        a.printCitiesFromContinent("", "src/main/resources/ALLCITIESfromSELECTEDCONTINENT.sql");
        a.generateCityReportMarkdown(cities, "city_report.md");

        a.printCitiesFromDistrict("", "src/main/resources/ALLCITIESfromSELECTEDDISTRICT.sql");
        a.generateCityReportMarkdown(cities, "district_report.md");

        a.printCitiesFromRegion("", "src/main/resources/ALLCITIESfromSELECTEDREGION.sql");
        a.generateCityReportMarkdown(cities, "region_report.md");

        // Disconnect from the database
        a.disconnect();
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