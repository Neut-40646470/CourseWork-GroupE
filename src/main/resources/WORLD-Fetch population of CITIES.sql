SELECT city.Name AS Name, country.Name AS Country, city.District, city.Population,
FROM world.city
JOIN world.country ON city.CountryCode = country.Code;