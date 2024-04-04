-- Fetch all CITIES from SELECTED CONTINENT

SELECT c.Name AS Name,co.Name AS Country, c.District AS District, c.Population AS Population
FROM world.city c
JOIN world.country co ON c.CountryCode = co.Code
WHERE co.Continent = 'Europe'
ORDER BY c.Population DESC;
