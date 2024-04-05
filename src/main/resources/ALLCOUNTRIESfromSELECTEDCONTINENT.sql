-- Fetch all COUNTRIES from SELECTED CONTINENT
SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, COALESCE(city.Name, 'Unknown') AS Capital
FROM country c
LEFT JOIN city ON c.Capital = city.ID
WHERE c.Continent = 'Asia'
ORDER BY c.Population DESC;
