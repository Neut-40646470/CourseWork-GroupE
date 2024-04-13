package com.napier.sem;

public class Country {
        public String code;
        public String name;
        public String continent;
        public String region;
        public int population;
        public String capital;
    public Country(String code, String name, String continent, String region, int population, String capital) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.population = population;
        this.capital = capital;
    }
        public void setCapital(String capital) {
            this.capital = capital;
        }
        public String getCapital() {
            return this.capital;
        }
        public String getCode() {
            return this.code;
        }
        public String getName() {
            return this.name;
        }
        public String getContinent() {
            return this.continent;
        }
        public String getRegion() {
            return this.region;
        }
        public int getPopulation() {
            return this.population;
        }
        public void setCode(String code) {
            this.code = code;
        }
        public void setName(String name) {
            this.name = name;
        }
        public void setContinent(String continent) {
            this.continent = continent;
        }
        public void setRegion(String region) {
            this.region = region;
        }
        public void setPopulation(int population) {
            this.population = population;
        }
}
