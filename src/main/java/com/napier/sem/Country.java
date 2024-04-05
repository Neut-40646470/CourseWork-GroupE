package com.napier.sem;
import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;
public class Country {

    private String Code;
    private String Name;
    private String Continent;
    private String Region;
    private int Population;
    private Integer Capital; // Integer type to handle null values for capital

    public Country(String code, String name, String continent, String region, int population, Integer capital) {
        this.Code = code;
        this.Name = name;
        this.Continent = continent;
        this.Region = region;
        this.Population = population;
        this.Capital = capital;
    }

    // Getter method for capital
    public Integer getCapital() {
        return Capital;
    }
    public void setCapital(Integer capital) {
        this.Capital = capital;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        this.Code = code;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getContinent() {
        return this.Continent;
    }

    public void setContinent(String continent) {
        this.Continent = continent;
    }

    public String getRegion() {
        return this.Region;
    }

    public void setRegion(String region) {
        this.Region = region;
    }

    public int getPopulation() {
        return this.Population;
    }

    public void setPopulation(int population) {
        this.Population = population;
    }
    // Other getter methods for code, name, continent, region, population
    // You can generate these automatically in your IDE or write them manually

    // Override toString method for debugging purposes or custom output
    @Override
    public String toString() {
        return "Country{" +
                "code='" + Code + '\'' +
                ", name='" + Name + '\'' +
                ", continent='" + Continent + '\'' +
                ", region='" + Region + '\'' +
                ", population=" + Population +
                ", capital=" + Capital +
                '}';
    }
}


//    public int getCapital() {
//        return this.Capital;
//    }

//    public void setCapital(int capital) {
//        this.Capital = capital;
//    }




