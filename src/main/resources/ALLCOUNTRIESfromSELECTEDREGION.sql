-- Fetch all COUNTRIES from SELECTED REGION

SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, city.Name AS Capital
FROM country c
JOIN city ON c.Capital = city.ID
WHERE c.Region = 'Middle East' -- Adjust the region name as per your database
ORDER BY c.Population DESC;
