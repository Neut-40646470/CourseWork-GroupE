package com.napier.sem;


public class City {
    private String name;
    private String country;
    private String district;
    private int population;

    //City Variable set up - Used for All Cities only passing 3 variables
    public City(String name, String country, String district, int population) {
        this.name = name;
        this.country = country;
        this.district = district;
        this.population = population;
    }
    //City Variable set up - Used for Capital Cities passing all variables
    public City(String name, String country, int population ){
        this.name = name;
        this.country = country;
        this.population = population;
    }
    //Public getter to return name
    public String getName() {
        return name;
    }
    //Public getter to return country
    public String getCountry() {
        return country;
    }
    //Public getter to return district
    public String getDistrict() {
        return district;
    }
    //Public getter to return population
    public int getPopulation() {
        return population;
    }
}