package com.andersenlab.aadamovich.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataSource {

    private static volatile DataSource instance;

    public static DataSource getInstance() {
        DataSource localInstance = instance;
        if (localInstance == null) {
            synchronized (DataSource.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DataSource();
                }
            }
        }
        return localInstance;
    }

    public Connection getConnection() {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("url");
        String user = resource.getString("userDB");
        String pass = resource.getString("passwordDB");

        try {
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get connection to DB", e);
        }
    }
}
