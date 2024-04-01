-- FETCH The population of a district

SELECT District, SUM(Population) AS Population_of_District
FROM city
GROUP BY District
ORDER BY District ASC;