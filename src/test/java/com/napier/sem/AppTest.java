package com.napier.sem;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AppTest {

    @Test
    void testGetAllCities() throws SQLException {
        // Initialize SingletonConnection mock
        SingletonConnection singletonConnection = Mockito.mock(SingletonConnection.class);

        Connection con = mock(Connection.class);
        ResultSet result = mock(ResultSet.class);
        Statement stmt = mock(Statement.class);

        // Define mock behaviors
        Mockito.when(singletonConnection.getConnection()).thenReturn(con);
        Mockito.when(con.createStatement()).thenReturn(stmt);
        Mockito.when(stmt.executeQuery(anyString())).thenReturn(result);
        Mockito.when(result.next()).thenReturn(true).thenReturn(false);
        Mockito.when(result.getInt("ID")).thenReturn(1);
        Mockito.when(result.getString("Name")).thenReturn("Test City");
        Mockito.when(result.getString("CountryCode")).thenReturn("TC");
        Mockito.when(result.getString("District")).thenReturn("Test District");
        Mockito.when(result.getInt("Population")).thenReturn(1000000);

        // Create App instance with SingletonConnection mock
        App app = new App();

        // Call the method under test
        ArrayList<Cities> resultList = app.getAllCities();

        // Assertions
        assertEquals(1, resultList.size());
        String expected = "ID: 1 Name: Test City CountryCode: TC District: Test District Population: 1000000";
        assertEquals(expected, resultList.get(0).toString());
    }
}
