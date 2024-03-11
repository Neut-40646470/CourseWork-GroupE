package com.napier.sem;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        Cities city = a.getCities(15);
        a.displayCity(city);

        // Disconnect from database
        a.disconnect();
    }

}
//
//
//    public void printCities(ArrayList<Cities> cities)
//    {
//        // Check employees is not null
//        if (cities == null)
//        {
//            System.out.println("No cities");
//            return;
//        }
//        // Print header
//        System.out.println(String.format("%-10s %-15s %-20s %-8s", "ID", "Name", "CountryCode", "Population"));
//        // Loop over all employees in the list
//        for (Cities emp : cities)
//        {
//            String city_string =
//                    String.format("%-10s %-15s %-20s %-8s",
//                            city.ID, city.Name, city.CountryCode, city.Population);
//            System.out.println(city_string);
//        }
//    }
//
//}










