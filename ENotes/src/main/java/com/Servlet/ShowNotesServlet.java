package com.Servlet;

import com.DAO.NoteDAO;
import com.User.NoteDetails;
import com.User.UserDetails;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ShowNotesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDetails user = (UserDetails) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("showNotes.jsp");
            return;
        }
        int id = user.getId();
        try {
        Connection conn = (Connection) getServletContext().getAttribute("conn");
            List<NoteDetails> list = NoteDAO.getAllNotes(id, conn);
            req.setAttribute("list", list);
            RequestDispatcher dispatcher = req.getRequestDispatcher("showNotes.jsp");
            dispatcher.forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
