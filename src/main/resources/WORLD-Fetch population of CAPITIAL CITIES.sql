SELECT co.NAME AS Country,
p.NAME AS Name,
p.Population AS Population
FROM world.city p
JOIN world.country co ON p.ID = co.Capital
ORDER BY p.Population DESC;