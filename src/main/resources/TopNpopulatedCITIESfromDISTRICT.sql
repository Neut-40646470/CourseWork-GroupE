-- FETCH Top (N) Populated CITIES from SELECTED DISTRICT

SELECT city.Name, country.Name AS Country, city.District, city.Population AS Population
FROM world.city
JOIN world.country ON city.CountryCode = country.Code
WHERE city.District = "California"
ORDER BY city.Population DESC
LIMIT 10;