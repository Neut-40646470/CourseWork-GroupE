-- FETCH Top (N) Populated CITIES from SELECTED COUNTRY

SELECT city.Name, country.Name AS Country, city.District, city.Population AS Population
FROM city
JOIN country
ON city.CountryCode = country.Code
WHERE country.Name = "Poland"
ORDER BY Population DESC
LIMIT N;