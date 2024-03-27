package com.napier.sem;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        // Create new Application
        App a = new App();
        // Connect to database
//        a.connect();

        if (args.length < 1) {
            a.connect("localhost:33060", 0);
        } else {
            a.connect("db:3306", 30000);
        }

        ArrayList<Cities> cities = a.getAllCities();
        // Print out all cities
        if (cities != null) {
            for (Cities city : cities) {

                System.out.println(city.ID + ", " + city.Name + ", " + city.CountryCode + ", " + city.District + ", " + city.Population);
            }
        } else {
            System.out.println("No cities found.");
        }
    }
}
