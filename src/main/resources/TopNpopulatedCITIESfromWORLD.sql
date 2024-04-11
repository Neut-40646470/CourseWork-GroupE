-- FETCH Top (N) Populated CITIES from THE WORLD

SELECT city.Name, country.Name AS Country, city.District, city.Population AS Population
FROM world.city
JOIN world.country ON city.CountryCode = country.Code
ORDER BY city.Population DESC
LIMIT N;