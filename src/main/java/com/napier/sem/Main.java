package com.napier.sem;
import java.io.IOException;
import java.sql.ResultSet;

import java.util.ArrayList;

public class Main {
        public static void main(String[] args) {
            App a = new App();

            if (args.length < 1) {
                a.connect("localhost:33060", 0);
            } else {
                a.connect("db:3306", 30000);
            }

            ResultSet resultSetContinent = a.executeQueryFromFile("src/main/resources/ALLCITIESfromSELECTEDCONTINENT.sql");
            ResultSet resultSetDistrict = a.executeQueryFromFile("src/main/resources/ALLCITIESfromSELECTEDDISTRICT.sql");
            ResultSet resultSetRegion = a.executeQueryFromFile("src/main/resources/ALLCITIESfromSELECTEDREGION.sql");

            a.generateCityReportFromResultSet(resultSetContinent, "City_Report_Continent.md");

            if (resultSetDistrict != null) {
                a.generateCityReportFromResultSet(resultSetDistrict, "City_Report_District.md");
            }
            if (resultSetRegion != null) {
                a.generateCityReportFromResultSet(resultSetRegion, "City_Report_Region.md");
            }

//            a.disconnect();

//            ArrayList<Cities> cities = a.getAllCities();
//            if (cities != null) {
//                for (Cities city : cities) {
//                    System.out.println(city.ID + ", " + city.Name + ", " + city.CountryCode + ", " + city.District + ", " + city.Population);
//                }
//            } else {
//                System.out.println("No cities found.");
//            }

            // Execute queries and generate reports
            a.printCitiesFromContinent("", "src/main/resources/ALLCITIESfromSELECTEDCONTINENT.sql");
            a.printCitiesFromDistrict("", "src/main/resources/ALLCITIESfromSELECTEDDISTRICT.sql");
            a.printCitiesFromRegion("", "src/main/resources/ALLCITIESfromSELECTEDREGION.sql");


//            ArrayList<Country> country = a.getAllCountries("SELECT * FROM country");
//            if(country != null) {
//                for (Country countries : country) {
//                    System.out.println(countries.getCode() + ", " + countries.getName() + ", " + countries.getContinent() + ", " + countries.getRegion() + ", " + countries.getPopulation());
//                }
//            } else {
//            System.out.println("No Countries found.");
//    }
//
//            a.printCountriesFromRegion("", "src/main/resources/ALLCOUNTRIESfromSELECTEDREGION.sql");
            // Disconnect from the database
            a.disconnect();
        }
    }
    // Generate country reports
//    ArrayList<Country> countries = app.getAllCountriesFromFile("src/main/resources/ALLCOUNTRIESfromSELECTEDREGION.sql");
//        if (countries != null) {
//        app.generateCountryReportMarkdown(countries, "country_report.md");
//         Print countries from region
//        String region = "";
//        app.printCountriesFromRegion(region, "src/main/resources/ALLCOUNTRIESfromSELECTEDREGION.sql");
//        app.generateCountryReportMarkdown(app.printCountriesFromRegion(region), "country_by_Region.md");
//         Print countries from continent
//        String continent = "";
//        app.printCountriesFromContinent(continent, "src/main/resources/ALLCOUNTRIESfromSELECTEDCONTINENT.sql");
//        app.generateCountryReportMarkdown(app.printCountriesFromContinent(continent), "country_by_continent.md");
//    } else {
//        System.out.println("No countries found.");
//    }
//
//     Disconnect from the database
//        app.disconnect();
//}
//}




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

