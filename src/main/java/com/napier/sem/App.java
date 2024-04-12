package com.napier.sem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
    private Connection con = null;

    public void connect(String location, int delay) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                Thread.sleep(delay);
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "123");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException | InterruptedException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            }
        }
    }

    public void disconnect() {
        //try disconnect from DB
        try {
            if (con != null) {
                con.close();
                System.out.println("Disconnected from database");
            }
            //error handling
        } catch (SQLException e) {
            System.out.println("Error closing connection to database");
            System.out.println(e.getMessage());
        }
    }
    public ResultSet executeQueryFromFile(String filePath) {
        try {
            String query = readQueryFromFile(filePath);
            Statement stmt = con.createStatement();
            return stmt.executeQuery(query);
        } catch (IOException | SQLException e) {
            System.out.println("Error executing SQL query from file: " + e.getMessage());
            return null;
        }
    }
    private String readQueryFromFile(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        StringBuilder queryBuilder = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            queryBuilder.append(line).append("\n");
        }
        br.close();
        return queryBuilder.toString();
    }

    public void generateCityReportFromResultSet(ResultSet resultSet, String filename) {
        try {
            ArrayList<Cities> cities = new ArrayList<>();
            while (resultSet.next()) {
                Cities city = new Cities();
//                city.ID = resultSet.getInt("ID");
                city.Name = resultSet.getString("Name");
//                city.CountryCode = resultSet.getString("CountryCode");
                city.CountryCode = resultSet.getString("Country");
                city.District = resultSet.getString("District");
                city.Population = resultSet.getInt("Population");
                cities.add(city);
            }
            if (!cities.isEmpty()) {
                generateCityReportMarkdown(cities, filename);
            } else {
                System.out.println("No cities found for generating report: " + filename);
            }
        } catch (SQLException e) {
            System.out.println("Error generating city report from ResultSet: " + e.getMessage());
        }
    }


    public void generateCityReportMarkdown(ArrayList<Cities> cities, String filename) {
        CitiesReport citiesReport = new CitiesReport(con);
        citiesReport.generateCityReportMarkdown(cities, filename);
    }

    public void printCitiesFromWorld(String world, String queryFile) {
        CitiesReport citiesReport = new CitiesReport(con);
        citiesReport.printCitiesFromWorld(world, queryFile);
    }

    public void printTopNCitiesFromWorld(String world, String queryFile) {
        CitiesReport citiesReport = new CitiesReport(con);
        citiesReport.printTopNCitiesFromWorld(world, queryFile);
    }

    public void printCitiesFromContinent(String continent, String queryFile) {
        CitiesReport citiesReport = new CitiesReport(con);
        citiesReport.printCitiesFromContinent(continent, queryFile);
    }

    public void printTopNCitiesFromContinent(String continent, String queryFile) {
        CitiesReport citiesReport = new CitiesReport(con);
        citiesReport.printTopNCitiesFromContinent(continent, queryFile);
    }

    public void printCitiesFromCountry(String country, String queryFile) {
        CitiesReport citiesReport = new CitiesReport(con);
        citiesReport.printCitiesFromCountry(country, queryFile);
    }

    public void printTopNCitiesFromCountry(String country, String queryFile) {
        CitiesReport citiesReport = new CitiesReport(con);
        citiesReport.printTopNCitiesFromCountry(country, queryFile);
    }

    public void printCitiesFromDistrict(String district, String queryFile) {
        CitiesReport citiesReport = new CitiesReport(con);
        citiesReport.printCitiesFromDistrict(district, queryFile);
    }

    public void printTopNCitiesFromDistrict(String district, String queryFile) {
        CitiesReport citiesReport = new CitiesReport(con);
        citiesReport.printTopNCitiesFromDistrict(district, queryFile);
    }

    public void printCitiesFromRegion(String region, String queryFile) {
        CitiesReport citiesReport = new CitiesReport(con);
        citiesReport.printCitiesFromRegion(region, queryFile);
    }

    public void printTopNCitiesFromRegion(String region, String queryFile) {
        CitiesReport citiesReport = new CitiesReport(con);
        citiesReport.printTopNCitiesFromRegion(region, queryFile);
    }

    //Country reports
    public void generateCountryReportFromResultSet(ResultSet resultSet, String filename) {
        try {
            ArrayList<Country> countries = new ArrayList<>();
            while (resultSet.next()) {
                Country country = new Country();
                country.setCode(resultSet.getString("Code"));
                country.setName(resultSet.getString("Name"));
                country.setContinent(resultSet.getString("Continent"));
                country.setRegion(resultSet.getString("Region"));
                country.setPopulation(resultSet.getInt("Population"));
                country.setCapital(resultSet.getString("Capital")); // Use the alias as specified in your SQL query
                String capitalName = resultSet.getString("Capital"); // Ensure this matches the alias in your SQL query.
                System.out.println("Capital Name: " + capitalName); // Debug print to verify if you're getting the correct data.
                countries.add(country);
            }
            if (!countries.isEmpty()) {
                generateCountryReportMarkdown(countries, filename);
            } else {
                System.out.println("No countries found for generating report: " + filename);
            }
        } catch (SQLException e) {
            System.out.println("Error generating country report from ResultSet: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
        }
    }
    public void generateCountryReportMarkdown(ArrayList<Country> countries, String filename) {
        CountryReport countryReport = new CountryReport(con);
        countryReport.generateCountryReportMarkdown(countries, filename);
    }
    public void printCountryFromWorld(String queryFile) {
        CountryReport countryReport = new CountryReport(con);
        countryReport.printCountriesFromWorld(queryFile);
    }
    public void printTopNCountriesFromWorld(String queryFile) {
        CountryReport countryReport = new CountryReport(con);
        countryReport.printTopNCountriesFromWorld(queryFile);
    }
    public void printCountryFromRegion(String queryFile) {
        CountryReport countryReport = new CountryReport(con);
        countryReport.printCountriesFromRegion(queryFile);
    }
    public void printTopNCountriesFromRegion(String queryFile) {
        CountryReport countryReport = new CountryReport(con);
        countryReport.printTopNCountriesFromRegion(queryFile);
    }
    public void printCountryFromContinent(String queryFile) {
        CountryReport countryReport = new CountryReport(con);
        countryReport.printCountriesFromContinent(queryFile);
    }
    public void printTopNCountriesFromContinent(String queryFile) {
        CountryReport countryReport = new CountryReport(con);
        countryReport.printTopNCountriesFromContinent(queryFile);
    }


    public void generateCapitalCityReportFromResultSet(ResultSet resultSet, String filename) {
        try {
            ArrayList<CapitalCities> capitalcities = new ArrayList<>();
            while (resultSet.next()) {
                CapitalCities capitalcity = new CapitalCities();
//                capitalcity.ID = resultSet.getInt("ID");
                capitalcity.Name = resultSet.getString("Name");
//                city.CountryCode = resultSet.getString("CountryCode");
                capitalcity.CountryCode = resultSet.getString("Country");
//                capitalcity.District = resultSet.getString("District");
                capitalcity.Population = resultSet.getInt("Population");
                capitalcities.add(capitalcity);
            }
            if (!capitalcities.isEmpty()) {
                generateCapitalCityReportMarkdown(capitalcities, filename);
            } else {
                System.out.println("No capital cities found for generating report: " + filename);
            }
        } catch (SQLException e) {
            System.out.println("Error generating Capital city report from ResultSet: " + e.getMessage());
        }
    }

    public void generateCapitalCityReportMarkdown(ArrayList<CapitalCities> capitalcities, String filename) {
        CapitalCitiesReport capitalcitiesreport = new CapitalCitiesReport(con);
        capitalcitiesreport.generateCapitalCityReportMarkdown(capitalcities, filename);
    }

    public void printCapitalCitiesFromWorld(String world, String queryFile) {
        CapitalCitiesReport capitalcitiesreport = new CapitalCitiesReport(con);
        capitalcitiesreport.printCapitalCitiesFromWorld(world, queryFile);
    }

    public void printCapitalCitiesFromContinent(String continent, String queryFile) {
        CapitalCitiesReport capitalcitiesreport = new CapitalCitiesReport(con);
        capitalcitiesreport.printCapitalCitiesFromContinent(continent, queryFile);
    }

//    public void printCapitalCitiesFromRegion(String region, String queryFile) {
//        CapitalCitiesReport capitalcitiesreport = new CapitalCitiesReport(con);
//        capitalcitiesreport.printCapitalCitiesFromRegion(region, queryFile);
//    }

}


//
//    private String getCapitalCityName(Integer capitalId) {
//        try {
//            String query = "SELECT Name FROM City WHERE ID = " + capitalId;
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//            if (rs.next()) {
//                return rs.getString("Name");
//            }
//        } catch (SQLException e) {
//            System.out.println("Error retrieving capital city name: " + e.getMessage());
//        }
//        return null;
//    }



/*

    public ArrayList<Cities> getAllCities() {
        ArrayList<Cities> cities = new ArrayList<>();
        try (Statement stmt = con.createStatement()) {
            String strSelect = "SELECT city.ID, city.Name AS Name, city.CountryCode, "
                    + "country.Name AS Country, city.District, city.Population "
                    + "FROM world.city "
                    + "JOIN world.country ON city.CountryCode = country.Code";

            ResultSet rset = stmt.executeQuery(strSelect);
            while (rset.next()) {
                Cities city = new Cities();
                city.ID = rset.getInt("ID");
                city.Name = rset.getString("Name");
                city.CountryCode = rset.getString("CountryCode");
                city.District = rset.getString("District");
                city.Population = rset.getInt("Population");
                cities.add(city);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get city array: " + e.getMessage());
            cities.clear();
            cities = null;
        }
        return cities.isEmpty() ? null : cities;
    }
*/