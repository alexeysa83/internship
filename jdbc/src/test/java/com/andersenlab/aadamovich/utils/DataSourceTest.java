package com.andersenlab.aadamovich.utils;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataSourceTest {

    private DataSource mysql = DataSource.getInstance();

    @Test
    void getInstance() {
        assertNotNull(mysql);
    }

    @Test
    void getConnection() {
        final Connection connection = mysql.getConnection();
        assertNotNull(connection);
    }
}
