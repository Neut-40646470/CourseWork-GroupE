-- FETCH Top (N) Populated COUNTRIES from THE SELECTED CONTINENT

SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name AS Capital
FROM world.country
JOIN world.city
ON country.capital = city.id
WHERE country.continent = "South America"
ORDER BY country.Population DESC
LIMIT 10;