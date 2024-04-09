//package com.napier.sem;
//
//public class sql_chris {
//Output for WORLD - Fetch population of CITIES
//Amended code below to show all required columns

//"SELECT city.Name AS Name, country.Name AS Country, city.District, city.Population"
//    "FROM world.city""
//    "JOIN world.country ON city.CountryCode = country.Code;""
//
//
//  WORLD - Fetch population of CAPITAL CITIES (Amended name of column to fit report requirement)
// (Outputs country joined with its capital city followed by population in largest to smallest/DESC order)
//  "SELECT co.NAME AS Country,"
//    "p.NAME AS Name,"
//    "p.Population AS Population"
//    "FROM world.city p"
//    "JOIN world.country co ON p.ID = co.Capital"
//    "ORDER BY p.Population DESC;"
//
//
//     COUNTRY - Fetch population of CITIES in SELECTED COUNTRY
//    "(Below will pull the population of cities in a selected country which is placed in 'COUNTRY NAME HERE', have added ORDER BY in the case of Top (N) populated cities task)
//     amended code to show required columns for a city report, (country is the choice within the code and city is the output)
//
//    "SELECT c.Name AS Name, co.Name AS Country, c.District, c.Population"
//    "FROM world.city c""
//    "JOIN world.country co ON c.CountryCode = co.Code"
//    "WHERE co.Name = 'Country Name Here'""
//    "ORDER BY c.Population DESC;"
//
//    CONTINENT - Fetch population of ALL CITIES from SELECTED CONTINENT
//    (Below will pull the population of all cities in a selected continent which is placed in 'CONTINENT HERE', have added ORDER BY in the case of Top (N) populated cities task.
//    amended code to show required columns for a city report, (continent is the choice within the code and city is the output)
//
//    "SELECT c.Name AS Name,co.Name AS Country, c.District AS District, c.Population AS Population
//    "FROM world.city c
//    "JOIN world.country co ON c.CountryCode = co.Code
//   "WHERE co.Continent = 'Continent Here'
//    "ORDER BY c.Population DESC;


//}
