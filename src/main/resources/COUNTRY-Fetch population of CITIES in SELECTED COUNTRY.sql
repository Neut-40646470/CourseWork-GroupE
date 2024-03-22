SELECT c.Name AS Name, co.Name AS Country, c.District, c.Population
FROM world.city c
JOIN world.country co ON c.CountryCode = co.Code
WHERE co.Name = 'Country Name Here'
ORDER BY c.Population DESC;