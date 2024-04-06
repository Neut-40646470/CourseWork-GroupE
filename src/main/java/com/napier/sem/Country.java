package com.napier.sem;
import java.util.List;
import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;


public class Country {
    public String Code;
    public String Name;
    public String Continent;
    public String Region;
    public int Population;
    public String Capital; // Integer type to handle null values for capital
    public void setCapital(String capital) {
        this.Capital = capital;
    }
    public String getCapital() {
        return this.Capital;
    }
    public String getCode() {
        return this.Code;
    }
    public String getName() {
        return this.Name;
    }
    public String getContinent() {
        return this.Continent;
    }
    public String getRegion() {
        return this.Region;
    }
    public int getPopulation() {
        return this.Population;
    }
    public void setCode(String code) {
        this.Code = code;
    }
    public void setName(String name) {
        this.Name = name;
    }
    public void setContinent(String continent) {
        this.Continent = continent;
    }
    public void setRegion(String region) {
        this.Region = region;
    }
    public void setPopulation(int population) {
        this.Population = population;
    }
}
//    public String CapitalCityName; // New attribute for capital city name

//    public String getCapitalCityName() {
//        return this.CapitalCityName;
//    }

//    public void setCapitalCityName(String capitalCityName) {
//        this.CapitalCityName = capitalCityName;
//    }
//    public void setCapital(String capital) {
//        this.Capital = capital;
//    }

//    public String getCapitalCityName() {
//        return CapitalCityName;
//    }



////
//    public Country() {
//        this.Code = "";
//        this.Name = "";
//        this.Continent = "";
//        this.Region = "";
//        this.Population = 0;
//        this.Capital = "";
//        this.CapitalCityName = "";
////        this.CapitalCityName = null; // Initialize capital city name
//    }



//    public void setCapitalCityName(String capitalCityName) {
//        this.CapitalCityName = capitalCityName;
//    }

    // Method to set the capital city name based on city ID
//    public void setCapitalCityNameFromCitiesList(List<Cities> citiesList) {
//        for (Cities city : citiesList) {
//            if (city.ID == this.Capital) {
//                this.CapitalCityName = city.Name;
//                break;
//            }
//        }
//    }

//    @Override
//    public String toString() {
//        return "Country{" +
//                "Code='" + Code + '\'' +
//                ", Name='" + Name + '\'' +
//                ", Continent='" + Continent + '\'' +
//                ", Region='" + Region + '\'' +
//                ", Population=" + Population +
//                ", Capital=" + Capital +
//                ", CapitalCityName='" + CapitalCityName + '\'' +
//                '}';
//    }



//    public int getCapital() {
//        return this.Capital;
//    }

//    public void setCapital(int capital) {
//        this.Capital = capital;
//    }




