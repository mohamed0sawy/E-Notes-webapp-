package com.Servlet;

import com.DAO.NoteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DeleteNotesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("note_id"));
        try {
            Connection conn = (Connection) getServletContext().getAttribute("conn");
            conn.setAutoCommit(false);
            boolean flag = NoteDAO.deleteNote(id, conn);
            HttpSession session = req.getSession();
            if (flag) {
                conn.commit();
                session.setAttribute("deleted", "Note deleted successfully");
                System.out.println("note deleted successfully");
            } else {
                conn.rollback();
                System.out.println("failed to delete note");
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("showNotes");
            dispatcher.forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
