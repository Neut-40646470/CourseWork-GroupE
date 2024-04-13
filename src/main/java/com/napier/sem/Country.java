package com.napier.sem;

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
