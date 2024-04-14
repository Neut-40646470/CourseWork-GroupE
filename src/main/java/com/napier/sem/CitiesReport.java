package com.napier.sem;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CitiesReport {
    private Connection con;

    public CitiesReport(Connection con) {
        this.con = con;
    }

    public void processCityReport(){
        App app = new App();


            ResultSet allCitiesByWorld = executeQueryFromFile(App.sqlFileBasePath + "ALLCITIESfromWORLD.sql"); // Print Report for allCitiesByWorld taking the file path
            if (allCitiesByWorld != null) {
                generateCityReportFromResultSet(allCitiesByWorld, "City_Report_World.md");
                printCitiesFromWorld("", App.sqlFileBasePath + "ALLCITIESfromWORLD.sql");
            }
            ResultSet TopNCitiesByWorld = executeQueryFromFile(App.sqlFileBasePath + "TopNpopulatedCITIESfromWORLD.sql");
            if (TopNCitiesByWorld != null) {
                generateCityReportFromResultSet(TopNCitiesByWorld, "Top_N_City_Report_World.md");
                printTopNCitiesFromWorld("", App.sqlFileBasePath + "TopNpopulatedCITIESfromWORLD.sql");
            }
            ResultSet allCitiesByContinent = executeQueryFromFile(App.sqlFileBasePath + "ALLCITIESfromSELECTEDCONTINENT.sql");
            if (allCitiesByContinent != null) {
                generateCityReportFromResultSet(allCitiesByContinent, "City_Report_Continent.md");
                printCitiesFromContinent("", App.sqlFileBasePath + "ALLCITIESfromSELECTEDCONTINENT.sql");
            }
            ResultSet TopNCitiesByContinent = executeQueryFromFile(App.sqlFileBasePath + "TopNpopulatedCITIESfromCONTINENT.sql");
            if (TopNCitiesByContinent != null) {
                generateCityReportFromResultSet(TopNCitiesByContinent, "Top_N_City_Report_Continent.md");
                printTopNCitiesFromContinent("", App.sqlFileBasePath + "TopNpopulatedCITIESfromCONTINENT.sql");
            }
            ResultSet allCitiesByCountry = executeQueryFromFile(App.sqlFileBasePath + "ALLCITIESfromSELECTEDCOUNTRY.sql");
            if (allCitiesByCountry != null) {
                generateCityReportFromResultSet(allCitiesByCountry, "City_Report_Country.md");
                printCitiesFromCountry("", App.sqlFileBasePath + "ALLCITIESfromSELECTEDCOUNTRY.sql");
            }
            ResultSet TopNCitiesByCountry = executeQueryFromFile(App.sqlFileBasePath + "TopNpopulatedCITIESfromCOUNTRY.sql");
            if (TopNCitiesByCountry != null) {
                generateCityReportFromResultSet(TopNCitiesByCountry, "Top_N_City_Report_Country.md");
                printTopNCitiesFromCountry("", App.sqlFileBasePath + "TopNpopulatedCITIESfromCOUNTRY.sql");
            }
            ResultSet allCitiesByDistrict = executeQueryFromFile(App.sqlFileBasePath + "ALLCITIESfromSELECTEDDISTRICT.sql");
            if (allCitiesByDistrict != null) {
                generateCityReportFromResultSet(allCitiesByDistrict, "City_Report_District.md");
                printCitiesFromDistrict("", App.sqlFileBasePath + "ALLCITIESfromSELECTEDDISTRICT.sql");
            }
            ResultSet TopNCitiesByDistrict = executeQueryFromFile(App.sqlFileBasePath + "TopNpopulatedCITIESfromDISTRICT.sql");
            if (TopNCitiesByDistrict != null) {
                generateCityReportFromResultSet(TopNCitiesByDistrict, "Top_N_City_Report_District.md");
                printTopNCitiesFromDistrict("", App.sqlFileBasePath + "TopNpopulatedCITIESfromDISTRICT.sql");
            }
            ResultSet allCitiesByRegion = executeQueryFromFile(App.sqlFileBasePath + "ALLCITIESfromSELECTEDREGION.sql");
            if (allCitiesByRegion != null) {
                generateCityReportFromResultSet(allCitiesByRegion, "City_Report_Region.md");
                printCitiesFromRegion("", App.sqlFileBasePath + "ALLCITIESfromSELECTEDREGION.sql");
            }
            ResultSet TopNCitiesByRegion = executeQueryFromFile(App.sqlFileBasePath + "TopNpopulatedCITIESfromREGION.sql");
            if (TopNCitiesByRegion != null) {
                generateCityReportFromResultSet(TopNCitiesByRegion, "Top_N_City_Report_Region.md");
                printTopNCitiesFromRegion("", App.sqlFileBasePath + "TopNpopulatedCITIESfromREGION.sql");
            }

    }
    public void generateCityReportFromResultSet(ResultSet resultSet, String filename) {
        if(resultSet == null)
        {
            System.out.println("Cities -- NO Data to precess, SQL file Might not exist offailed ****");
            return;
        }
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

    public static void generateCityReportMarkdown(ArrayList<Cities> cities, String filename) {
        if (cities == null || cities.isEmpty()) {
            System.out.println("No cities to generate report.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("# City Report\n\n");
        sb.append("| Name | Country | District | Population |\n");
        sb.append("| ---- | ------- | -------- | ---------- |\n");
        for (Cities city : cities) {
            sb.append("| " + city.getName() + " | " + city.getCountry() + " | " +
                    city.getDistrict() + " | " + city.getPopulation() + " |\n");
        }

        saveReportToFile(sb.toString(), filename);
    }

    public static void saveReportToFile(String content, String filename) {
        try {
            File directory = new File("./reports");
            if (!directory.exists()) {
                directory.mkdir();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(directory, filename)));
            writer.write(content);
            writer.close();
            System.out.println("\n\nCity report generated: " + filename);
        } catch (IOException e) {
            System.out.println("Error generating city report: " + e.getMessage());
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

    private void executeQuery(String query, String reportTitle) {
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            // Print city report header
            System.out.println(reportTitle + "\n");
            System.out.println(String.format("%-30s %-30s %-20s %-15s", "Name", "Country", "District", "Population"));
            System.out.println("----------------------------------------------------------------------------");
            // Print cities from the ResultSet
            while (rs.next()) {
                String name = rs.getString("Name");
                String country = rs.getString("Country");
                String district = rs.getString("District");
                int population = rs.getInt("Population");
                System.out.println(String.format("%-30s %-30s %-20s %-15d", name, country, district, population));
            }
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
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

    public void printCitiesFromContinent(String continent, String queryFile) {
        try {
            String query = readQueryFromFile(queryFile).replace("", continent);
            executeQuery(query, "City Report By Continent");
        } catch (IOException e) {
            System.out.println("Error reading SQL file: " + e.getMessage());
        }
    }
    public void printTopNCitiesFromContinent(String continent, String queryFile) {
        try {
            String query = readQueryFromFile(queryFile).replace("", continent);
            executeQuery(query, "Top N City Report By continent");
        } catch (IOException e) {
            System.out.println("Error reading SQL file: " + e.getMessage());
        }
    }

    public void printCitiesFromCountry(String country, String queryFile) {
        try {
            String query = readQueryFromFile(queryFile).replace("", country);
            executeQuery(query, "City Report By Country");
        } catch (IOException e) {
            System.out.println("Error reading SQL file: " + e.getMessage());
        }
    }

    public void printTopNCitiesFromCountry(String country, String queryFile) {
        try {
            String query = readQueryFromFile(queryFile).replace("", country);
            executeQuery(query, "Top N City Report By Country");
        } catch (IOException e) {
            System.out.println("Error reading SQL file: " + e.getMessage());
        }
    }
    public void printCitiesFromDistrict(String district, String queryFile) {
        try {
            String query = readQueryFromFile(queryFile).replace("", district);
            executeQuery(query, "City Report By District");
        } catch (IOException e) {
            System.out.println("Error reading SQL file: " + e.getMessage());
        }
    }

    public void printTopNCitiesFromDistrict(String district, String queryFile) {
        try {
            String query = readQueryFromFile(queryFile).replace("", district);
            executeQuery(query, "Top N City Report By District");
        } catch (IOException e) {
            System.out.println("Error reading SQL file: " + e.getMessage());
        }
    }

    public void printCitiesFromRegion(String region, String queryFile) {
        try {
            String query = readQueryFromFile(queryFile).replace("", region);
            executeQuery(query, "City Report By Region");
        } catch (IOException e) {
            System.out.println("Error reading SQL file: " + e.getMessage());
        }
    }

    public void printTopNCitiesFromRegion(String region, String queryFile) {
        try {
            String query = readQueryFromFile(queryFile).replace("", region);
            executeQuery(query, "Top N City Report By Region");
        } catch (IOException e) {
            System.out.println("Error reading SQL file: " + e.getMessage());
        }
    }

    public void printCitiesFromWorld(String world, String queryFile) {
        try {
            String query = readQueryFromFile(queryFile).replace("", world);
            executeQuery(query, "City Report By World");
        } catch (IOException e) {
            System.out.println("Error reading SQL file: " + e.getMessage());
        }
    }

    public void printTopNCitiesFromWorld(String world, String queryFile) {
        try {
            String query = readQueryFromFile(queryFile).replace("", world);
            executeQuery(query, "Top N City Report By World");
        } catch (IOException e) {
            System.out.println("Error reading SQL file: " + e.getMessage());
        }
    }
}
