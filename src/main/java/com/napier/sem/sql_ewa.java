package com.napier.sem;

public class sql_ewa {
    //COUNTRIES - Fetch population for all the COUNTRIES in the WORLD from the database.
    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name AS Capital
    "FROM world.country
    "JOIN world.city
    "ON country.capital = city.id
    "ORDER BY country.Population DESC;"

    //CONTINENT - FETCH population values for all the COUNTRIES in A CONTINENT from the database.
    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name AS Capital
    "FROM world.country
    "JOIN world.city
    "ON country.capital = city.id
    "WHERE country.continent = "South America"
    "ORDER BY country.Population DESC;

    //REGION - Fetch population values for all the COUNTRIES in SELECTED REGION from the database.SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name AS Capital
    "FROM world.country
    "JOIN world.city
    "ON country.capital = city.id
    "WHERE country.region = "Caribbean"
    "ORDER BY country.Population DESC;

    //DISTRICT - Fetch all CITIES from SELECTED DISTRICT
    "SELECT city.Name, country.Name AS Country, city.District, city.Population AS Population
    "FROM world.city
    "JOIN world.country ON city.CountryCode = country.Code
    "WHERE city.District = "California"
    "ORDER BY city.Population DESC;

    //REGION - Fetch population for all CITIES from SELECTED REGION
    "SELECT city.Name, country.Name AS Country, city.District, city.Population
    "FROM city
    "JOIN country
    "ON city.CountryCode = country.Code
    "WHERE country.Region = "Southern Europe"
    "ORDER BY Population DESC;

    //CITIES - Fetch ALL population from WORLD
    "SELECT city.Name, country.Name AS Country, city.District, city.Population AS Population
    "FROM world.city
    "JOIN world.country ON city.CountryCode = country.Code
    "ORDER BY city.Population DESC;

    //CITIES - Fetch ALL population from SELECTED COUNTRY
    "SELECT city.Name, country.Name AS Country, city.District, city.Population AS Population
    "FROM city
    "JOIN country
    "ON city.CountryCode = country.Code
    "WHERE country.Name = "Poland"
    "ORDER BY Population DESC;
}
