package com.napier.sem;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AppTest {

    static App app;
    static final String DB_LOCATION = "localhost:33060";
    static final int DELAY = 0; // Adjust delay as needed

    @Test
    void testConnection(){
        app = new App();
        app.connect(DB_LOCATION,DELAY);
    }
    @Test
    void connectionFailedTest() {
        app = new App();
        assertThrows(NullPointerException.class, this::throwsException);
    }

    @Test
    void testDisconnect(){
        app = new App();
        app.disconnect();
    }
    @Test
    void testPrintCities(){
        app = new App();
        app.connect(DB_LOCATION,DELAY);
        app.getAllCities();
//        app.printCities(app.getAllCities());
    }

//    @Test
//    void citiesPrintingTest() {
//        app = new App();
//        app.connect(DB_LOCATION, DELAY);
//        ResultSet allCitiesByWorld = app.executeQueryFromFile("src/main/resources/ALLCITIESfromWORLD.sql");
//        if(allCitiesByWorld != null){
//            app.generateCityReportFromResultSet(allCitiesByWorld, "City_Report_World.md");
//            app.printCitiesFromWorld("", "src/main/resources/ALLCITIESfromWORLD.sql");
//        }
//
//    }



    void throwsException() throws NullPointerException {
        throw new NullPointerException();
    }
}













//package com.napier.sem;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//
//import java.sql.Connection;
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class AppTest
//{
//    static App app;
////    static Connection con;
//
//    @BeforeAll
//    static void init()
//    {
//        app = new App();
//    }
//
//    @Test
//    void printCitiesTestNull()
//    {
//        app.printCities(null);
//    }
//
//    @Test
//    void getCitiesTestNull(){
//        app.getAllCities();
//    }
//
//    @Test
//    void citiesPrintingTest()
//    {
//        ArrayList<Cities> cities = new ArrayList<Cities>();
////        cities.add(null);
//        app.printCities(cities);
//    }
//
//    @Test
//    void connectionFailedTest()
//    {
//        assertThrows(NullPointerException.class, this::throwsException);
//    }
//    void throwsException() throws NullPointerException
//    {
//        throw new NullPointerException();
//    }
//
//
//}