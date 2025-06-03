package com.mobasserazaman;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:postgresql://localhost:5432/contactdb";
    private static final String USER = "user";
    private static final String PASS = "password";


    //This block runs once when the class is first loaded into the JVM — before any objects of the class are created and before any static methods are called.
    static {
        try {
            //This loads the PostgreSQL JDBC driver class dynamically by its full name. It causes the driver's static initializer to run, which typically registers the driver with the DriverManager. This is necessary so your app can connect to PostgreSQL databases via JDBC.
            Class.forName("org.postgresql.Driver"); // Load driver
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL Driver not found.");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
