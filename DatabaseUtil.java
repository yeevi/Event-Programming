package com.university;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Provides the connection between the application and MySQL. */
public final class DatabaseUtil {
    private static final String URL = System.getenv().getOrDefault(
            "MYSQL_URL", "jdbc:mysql://localhost:3306/university_db");
    private static final String USER = System.getenv().getOrDefault("MYSQL_USER", "root");
    private static final String PASSWORD = System.getenv().getOrDefault("MYSQL_PASSWORD", "");

    private DatabaseUtil() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
