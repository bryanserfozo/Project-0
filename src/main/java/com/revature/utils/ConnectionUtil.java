package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:postgresql://35.192.135.148:5432/postgres";
        String username = System.getenv("DB-User");
        String password = System.getenv("DB-Password");
        return DriverManager.getConnection(url,username, password);
    }


}
