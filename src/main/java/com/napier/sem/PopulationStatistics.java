package com.napier.sem;

public class PopulationStatistics {
        private String location;
        private int totalPopulation;
        private int populationInCities;
        private int populationNotInCities;

        public PopulationStatistics(String location, int totalPopulation, int populationInCities, int populationNotInCities) {
            this.location = location;
            this.totalPopulation = totalPopulation;
            this.populationInCities = populationInCities;
            this.populationNotInCities = populationNotInCities;
        }

        public String getLocation() {
            return location;
        }

        public int getTotalPopulation() {
            return totalPopulation;
        }

        public int getPopulationInCities() {
            return populationInCities;
        }

        public int getPopulationNotInCities() {
            return populationNotInCities;
        }
    }
}
