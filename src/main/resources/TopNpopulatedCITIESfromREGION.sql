-- FETCH Top (N) Populated CITIES from SELECTED REGION

SELECT city.Name, country.Name AS Country, city.District, city.Population
FROM city
JOIN country
ON city.CountryCode = country.Code
WHERE country.Region = "Southern Europe"
ORDER BY Population DESC
LIMIT N;