package com.Servlet;

import com.DB.DBconnect;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DBListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            // Initialize the database connection when the application starts
            Connection connection = DBconnect.getMyConn();
            sce.getServletContext().setAttribute("conn", connection);
            System.out.println("Database connection initialized.");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Close the database connection when the application is shut down
        Connection connection = (Connection) sce.getServletContext().getAttribute("conn");
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
