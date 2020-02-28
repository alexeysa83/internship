package com.andersenlab.aadamovich.utils;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DataSourceTest {

    private DataSource mysql = DataSource.getInstance();

    @Test
    void getInstance() {
        assertNotNull(mysql);
    }

    @Test
    void getConnection() throws SQLException {
        try (final Connection connection = mysql.getConnection()) {
            assertNotNull(connection);
            assertTrue(connection.isValid(1));
            assertFalse(connection.isClosed());
        }
    }
}
