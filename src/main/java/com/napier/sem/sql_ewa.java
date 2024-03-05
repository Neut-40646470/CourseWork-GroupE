package com.napier.sem;

public class sql_ewa {
    //COUNTRIES - Fetch population for all the COUNTRIES in the WORLD from the database.
    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name AS Capital
    "FROM world.country,
    "JOIN world.city,
    "ON country.capital = city.id,
    "ORDER BY country.Population DESC;"
}
