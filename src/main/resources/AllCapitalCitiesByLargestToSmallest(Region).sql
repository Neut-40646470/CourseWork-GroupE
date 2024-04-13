SELECT c.Name AS Name, co.Name AS Country, c.Population AS Population
FROM world.city c
JOIN world.country co ON c.CountryCode = co.Code
WHERE co.Region = 'Nordic Countries' AND c.ID = co.Capital
ORDER BY c.Population DESC;
