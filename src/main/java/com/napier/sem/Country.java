package com.napier.sem;
import java.util.List;

public class Country {
    private String Code;
    private String Name;
    private String Continent;
    private String Region;
    private int Population;
    private Integer Capital; // Integer type to handle null values for capital
    private String CapitalCityName; // New attribute for capital city name

    public Country(String code, String name, String continent, String region, int population, Integer capital) {
        this.Code = code;
        this.Name = name;
        this.Continent = continent;
        this.Region = region;
        this.Population = population;
        this.Capital = capital;
        this.CapitalCityName = null; // Initialize capital city name
    }

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

    public String getCapitalCityName() {
        return CapitalCityName;
    }

    public void setCapitalCityName(String capitalCityName) {
        this.CapitalCityName = capitalCityName;
    }

    // Method to set the capital city name based on city ID
    public void setCapitalCityNameFromCitiesList(List<Cities> citiesList) {
        for (Cities city : citiesList) {
            if (city.ID == this.Capital) {
                this.CapitalCityName = city.Name;
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Country{" +
                "Code='" + Code + '\'' +
                ", Name='" + Name + '\'' +
                ", Continent='" + Continent + '\'' +
                ", Region='" + Region + '\'' +
                ", Population=" + Population +
                ", Capital=" + Capital +
                ", CapitalCityName='" + CapitalCityName + '\'' +
                '}';
    }
}


//    public int getCapital() {
//        return this.Capital;
//    }

//    public void setCapital(int capital) {
//        this.Capital = capital;
//    }




