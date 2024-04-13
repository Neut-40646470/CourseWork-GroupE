SELECT
    "Chinese" AS Language,
    SUM(population) AS TotalPopulation,
    CONCAT(ROUND((SUM(population) / (SELECT SUM(population) FROM world.country)) * 100, 2), '%') AS Percentage
FROM
    world.countrylanguage cl
    JOIN
    world.country co ON cl.CountryCode = co.Code
WHERE
    cl.Language = 'Chinese'

UNION

SELECT
    "English" AS Language,
    SUM(population) AS TotalPopulation,
    CONCAT(ROUND((SUM(population) / (SELECT SUM(population) FROM world.country)) * 100, 2), '%') AS Percentage
FROM
    world.countrylanguage cl
    JOIN
    world.country co ON cl.CountryCode = co.Code
WHERE
    cl.Language = 'English'

UNION

SELECT
    "Hindi" AS Language,
    SUM(population) AS TotalPopulation,
    CONCAT(ROUND((SUM(population) / (SELECT SUM(population) FROM world.country)) * 100, 2), '%') AS Percentage
FROM
    world.countrylanguage cl
    JOIN
    world.country co ON cl.CountryCode = co.Code
WHERE
    cl.Language = 'Hindi'

UNION

SELECT
    "Spanish" AS Language,
    SUM(population) AS TotalPopulation,
    CONCAT(ROUND((SUM(population) / (SELECT SUM(population) FROM world.country)) * 100, 2), '%') AS Percentage
FROM
    world.countrylanguage cl
    JOIN
    world.country co ON cl.CountryCode = co.Code
WHERE
    cl.Language = 'Spanish'

UNION

SELECT
    "Arabic" AS Language,
    SUM(population) AS TotalPopulation,
    CONCAT(ROUND((SUM(population) / (SELECT SUM(population) FROM world.country)) * 100, 2), '%') AS Percentage
FROM
    world.countrylanguage cl
    JOIN
    world.country co ON cl.CountryCode = co.Code
WHERE
    cl.Language = 'Arabic'
ORDER BY
    TotalPopulation DESC;