-- Fetch all COUNTRIES from SELECTED REGION

SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, city.Name AS Capital
FROM country c
JOIN city ON c.Capital = city.ID
WHERE c.Region = 'Middle East' AND c.Capital IS NOT NULL
<<<<<<< HEAD
ORDER BY c.Population DESC;
=======
ORDER BY c.Population DESC;
>>>>>>> feature/workingSQLexample
