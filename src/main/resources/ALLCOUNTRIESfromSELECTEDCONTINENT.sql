-- Fetch all COUNTRIES from SELECTED CONTINENT, correctly joining to get the capital's name
SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, COALESCE(cap.Name, 'Unknown') AS Capital
FROM country c
LEFT JOIN city cap ON c.Capital = cap.ID
WHERE c.Continent = 'Asia' AND c.Capital IS NOT NULL
ORDER BY c.Population DESC;