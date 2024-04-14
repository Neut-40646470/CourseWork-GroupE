-- FETCH The population of a region.

SELECT Region, SUM(Population) AS Population_of_Region
FROM country
GROUP BY Region
ORDER BY Region ASC;