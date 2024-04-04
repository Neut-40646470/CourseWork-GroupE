package com.napier.sem;
import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;

public class Cities {
    public int ID;
    public String Name;
    public String CountryCode;
    public String District;
    public int Population;

    // Constructor with arguments
    public Cities(int ID, String Name, String CountryCode, String District, int Population) {
        this.ID = ID;
        this.Name = Name;
        this.CountryCode = CountryCode;
        this.District = District;
        this.Population = Population;
    }

    // No-argument constructor
    public Cities() {
        // Initialize variables to default values
        this.ID = 0;
        this.Name = "";
        this.CountryCode = "";
        this.District = "";
        this.Population = 0;
    }

    // Getter methods
    public String getName() {
        return this.Name;
    }

    public String getCountry() {
        return this.CountryCode; // Assuming CountryCode represents the country
    }

    public String getDistrict() {
        return this.District;
    }

    public int getPopulation() {
        return this.Population;
    }
}