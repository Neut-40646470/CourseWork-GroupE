-- FETCH Top (N) Populated COUNTRIES from THE SELECTED REGION

SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name AS Capital
FROM world.country
JOIN world.city
ON country.capital = city.id
WHERE country.region = "Caribbean"
ORDER BY country.Population DESC
LIMIT 10;