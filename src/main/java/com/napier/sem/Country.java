package com.napier.sem;

public class Country {
    private String code;
    private String name;
    private String continent;
    private String region;
    private int population;
    private String capital;

    //Country Variable Set up passing all variable when asked.
    public Country(String code, String name, String continent, String region, int population, String capital) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.population = population;
        this.capital = capital;
    }

    //Public getter for Code that returns code
    public String getCode() {
        return code;
    }
    //Public getter to return name
    public String getName() {
        return name;
    }
    //Public getter to return continent
    public String getContinent() {
        return continent;
    }
    //Public getter to return region
    public String getRegion() {
        return region;
    }
    //Public getter to return population
    public int getPopulation() {
        return population;
    }
    //Public getter to return capital
    public String getCapital() {
        return capital;
    }
}