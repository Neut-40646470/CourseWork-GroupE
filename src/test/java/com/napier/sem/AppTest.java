package com.napier.sem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AppTest
{
    @Test
    void testSingleton(){
        SingletonConnection connection1 = SingletonConnection.getInstance();
        SingletonConnection connection2 = SingletonConnection.getInstance();
        assertTrue(connection1 == connection2);
    }
    @Test
    void testGetAllCities() throws SQLException {
        Connection con = mock(Connection.class);
        ResultSet result = mock(ResultSet.class);
        Statement stmt = mock(Statement.class);
        when(con.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenReturn(result);
        when(result.next()).thenReturn(true).thenReturn(false);
        when(result.getInt("ID")).thenReturn(1);
        when(result.getString("Name")).thenReturn("CityName");
        when(result.getString("CountryCode")).thenReturn("CC");
        when(result.getString("District")).thenReturn("District");
        when(result.getInt("Population")).thenReturn(1000);

        App app = new App();
        ArrayList<Cities> cities = app.getAllCities();

        assertEquals(1, cities.size());
        Cities city = cities.get(0);
        assertEquals(1, city.getID());
        assertEquals("CityName", city.getName());
        assertEquals("CC", city.getCountry());
        assertEquals("District", city.getDistrict());
        assertEquals(1000, city.getPopulation());
    }
}