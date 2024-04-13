package com.napier.sem;
import java.sql.*;
public class Cities {
    public int ID;
    public String Name;
    public String CountryCode;
    public String District;
    public int Population;

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
