package com.DB;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBconnect {

    private static Properties prop = new Properties();
    private static String url, user, pass;

    static {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getMyConn() throws SQLException, IOException {
        prop.load(DBconnect.class.getClassLoader().getResourceAsStream("DBinfo.properties"));
        url = prop.getProperty("url");
        user = prop.getProperty("user");
        pass = prop.getProperty("pass");
        return DriverManager.getConnection(url, user, pass);
    }
}
