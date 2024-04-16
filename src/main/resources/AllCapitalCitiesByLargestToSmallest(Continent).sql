-- All Capital Cities by continent
SELECT c.Name AS Name, co.Name AS Country, c.Population AS Population
FROM world.city c
JOIN world.country co ON c.CountryCode = co.Code
WHERE co.Continent = 'Europe' AND c.ID = co.Capital
ORDER BY c.Population DESC;