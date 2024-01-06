package com.Servlet;

import com.DAO.UserDAO;
import com.User.UserDetails;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

public class RegisterServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("userFullName");
        String email = req.getParameter("userEmail");
        String password = req.getParameter("userPassword");
        UserDetails user = new UserDetails(name, email, password);
        try {
            Connection conn = (Connection) getServletContext().getAttribute("conn");
            conn.setAutoCommit(false);
            boolean flag = UserDAO.addUser(user, conn);
            HttpSession session = req.getSession();
            if (flag) {
                conn.commit();
                session.setAttribute("reg-succ", "You Registered Successfully...");
                System.out.println("user added succefully");
                resp.sendRedirect("register.jsp");
            } else {
                conn.rollback();
                session.setAttribute("reg-fail", "Registeration failed. Email already Exist. Try agian...");
                System.out.println("failed to add the user");
                resp.sendRedirect("register.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
