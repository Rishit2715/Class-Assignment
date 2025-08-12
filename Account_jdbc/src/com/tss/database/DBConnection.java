package com.tss.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    private DBConnection() {
        // Private constructor to prevent instantiation
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tss_student_db", 
                    "root", 
                    "Rishit91@#15"
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
