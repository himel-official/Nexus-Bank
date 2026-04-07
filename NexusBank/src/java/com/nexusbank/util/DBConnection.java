package com.nexusbank.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    // Change these according to your MySQL setup
    private static final String URL = "jdbc:mysql://localhost:3306/nexusbank?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";           // Your MySQL username
    private static final String PASSWORD = "him3l";  // ← CHANGE THIS!

    public static Connection getConnection() throws Exception {
        // Load MySQL Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}