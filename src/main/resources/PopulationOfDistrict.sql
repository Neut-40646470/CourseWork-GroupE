-- FETCH The population of a district

SELECT District, SUM(Population) AS Population_of_District
FROM city
GROUP BY District
<<<<<<< HEAD
ORDER BY District ASC;
=======
ORDER BY District ASC;
>>>>>>> feature/workingSQLexample
