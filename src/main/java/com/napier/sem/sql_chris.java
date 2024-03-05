package com.napier.sem;

public class sql_chris {
    //Output for WORLD - Fetch population of CITIES
    "SELECT Name, Population"
    "FROM world.city;"


  //WORLD - Fetch population of CAPITAL CITIES
 //(Outputs country joined with its capital city followed by population in largest to smallest/DESC order)
    "SELECT co.NAME AS Country,""
    "p.NAME AS CapitalCity,""
    "p.Population AS Population""
    "FROM world.city p""
    "JOIN world.country co ON p.ID = co.Capital""
    "ORDER BY p.Population DESC;""


     //COUNTRY - Fetch population of CITIES in SELECTED COUNTRY
    //"(Below will pull the population of cities in a selected country which is placed in 'COUNTRY NAME HERE', have added ORDER BY in the case of Top (N) populated cities task)
   "SELECT c.Name AS Cities, c.Population AS Population"
    "FROM world.city c"
    "JOIN world.country co ON c.CountryCode = co.Code"
    "WHERE co.Name = 'COUNTRY NAME HERE'"
    "ORDER BY c.Population DESC"

    //CONTINENT - Fetch population of ALL CITIES from SELECTED CONTINENT
    //(Below will pull the population of all cities in a selected continent which is placed in 'CONTINENT HERE', have added ORDER BY in the case of Top (N) populated cities task.

    "SELECT c.Name AS Cities,"
    "c.Population AS Population"
    "FROM world.city c"
    "JOIN world.country co ON c.CountryCode = co.Code"
    "WHERE co.Continent = 'CONTINENT HERE'"
    "ORDER BY c.Population DESC;"



}
