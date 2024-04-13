-- Fetch all COUNTRIES from THE WORLD

SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, cap.Name AS Capital
FROM country c
JOIN city cap ON c.Capital = cap.ID
WHERE c.Capital IS NOT NULL
ORDER BY c.Population DESC;