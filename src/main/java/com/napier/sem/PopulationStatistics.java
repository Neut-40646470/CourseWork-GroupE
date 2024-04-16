package com.napier.sem;

public class PopulationStatistics {
    private String location;
    private int totalPopulation;
    private int populationInCities;
    private int populationNotInCities;
//set up for population statistics
    public PopulationStatistics(String location, int totalPopulation, int populationInCities, int populationNotInCities) {
        this.location = location;
        this.totalPopulation = totalPopulation;
        this.populationInCities = populationInCities;
        this.populationNotInCities = populationNotInCities;
    }
//Getter for location
    public String getLocation() {
        return location;
    }
//Getter for population
    public int getTotalPopulation() {
        return totalPopulation;
    }
//getter ffor population in cities
    public int getPopulationInCities() {
        return populationInCities;
    }
//getter for population not in cities
    public int getPopulationNotInCities() {
        return populationNotInCities;
    }
}