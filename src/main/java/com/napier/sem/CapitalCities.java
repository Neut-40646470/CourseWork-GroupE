package com.napier.sem;
import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;

public class CapitalCities {
    public int ID;
    public String Name;
    public String CountryCode;
    public int Population;

    // No-argument constructor
    public CapitalCities() {
        // Initialize variables to default values
        this.ID = 0;
        this.Name = "";
        this.CountryCode = "";
        this.Population = 0;
    }

    // Getter methods
    public String getName() {
        return this.Name;
    }

    public String getCountry() {
        return this.CountryCode; // Assuming CountryCode represents the country
    }

    public int getPopulation() {
        return this.Population;
    }
}
