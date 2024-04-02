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
