package com.Servlet;

import com.DAO.NoteDAO;
import com.User.NoteDetails;
import com.User.UserDetails;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AddNoteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String title = req.getParameter("noteTitle");
        String content = req.getParameter("noteContent");
        NoteDetails note = new NoteDetails();
        note.setTitle(title);
        note.setContent(content);
        note.setUser((UserDetails) session.getAttribute("user"));
        try {
            Connection conn = (Connection) getServletContext().getAttribute("conn");
            conn.setAutoCommit(false);
            boolean flag = NoteDAO.addNote(note, conn);
            if (flag) {
                conn.commit();
                session.setAttribute("note", note);
                session.setAttribute("added", "Note added successfully");
                System.out.println("note added successfully");
                resp.sendRedirect("addNotes.jsp");
            } else {
                conn.rollback();
                System.out.println("failed to add note");
                resp.sendRedirect("addNotes.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
