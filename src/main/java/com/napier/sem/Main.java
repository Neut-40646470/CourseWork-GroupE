package com.napier.sem;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Get all cities
        ArrayList<Cities> cities = a.getAllCities();

        // Print out all cities
        if (cities != null) {
            for (Cities city : cities) {
                System.out.println(city.ID + ", " + city.Name + ", " + city.CountryCode + ", " + city.District + ", " + city.Population);
            }
        } else {
            System.out.println("No cities found.");
        }

        // Disconnect from database
        SingletonConnection.getInstance().disconnect();
    }
}
