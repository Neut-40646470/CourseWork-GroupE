SELECT
    co.Continent AS Name,
    SUM(co.Population) AS TotalPopulation,
    SUM(c.Population) AS PopulationInCities,
    SUM(co.Population) - SUM(c.Population) AS PopulationNotInCities,
    CONCAT(ROUND((SUM(c.Population) / SUM(co.Population)) * 100, 2), '%') AS PercentageInCities,
    CONCAT(ROUND(((SUM(co.Population) - SUM(c.Population)) / SUM(co.Population)) * 100, 2), '%') AS PercentageNotInCities
FROM
    world.country co
        LEFT JOIN
    (
        SELECT
            CountryCode,
            SUM(Population) AS Population
        FROM
            world.city
        GROUP BY
            CountryCode
    ) c ON co.Code = c.CountryCode
GROUP BY
    co.Continent
ORDER BY
<<<<<<< HEAD
    co.Continent;
=======
    co.Continent;
>>>>>>> feature/workingSQLexample