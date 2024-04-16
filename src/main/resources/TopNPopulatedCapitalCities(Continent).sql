SELECT city.Name AS Name, country.Name AS Country, city.Population AS Population
FROM world.city
JOIN world.country ON city.CountryCode = country.Code
WHERE country.Continent = 'INSERT CONTINENT HERE' AND city.ID = country.Capital
ORDER BY city.Population DESC
<<<<<<< HEAD
LIMIT N;
=======
LIMIT 10;
>>>>>>> feature/workingSQLexample
