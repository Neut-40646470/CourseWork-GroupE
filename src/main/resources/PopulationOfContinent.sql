-- Fetch Population of a continent

SELECT Continent, sum(Population) AS Population_of_Continent
FROM country
GROUP BY Continent
<<<<<<< HEAD
ORDER BY Continent ASC;
=======
ORDER BY Continent ASC;
>>>>>>> feature/workingSQLexample
