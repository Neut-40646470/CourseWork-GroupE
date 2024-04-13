-- Fetch Population of a continent

SELECT Continent, sum(Population) AS Population_of_Continent
FROM country
GROUP BY Continent
ORDER BY Continent ASC;
