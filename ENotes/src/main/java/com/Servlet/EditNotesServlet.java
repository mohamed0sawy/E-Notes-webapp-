package com.Servlet;

import com.DAO.NoteDAO;
import com.User.NoteDetails;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class EditNotesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) getServletContext().getAttribute("conn");
        int noteId = Integer.parseInt(req.getParameter("note_id"));
        req.setAttribute("noteId", noteId);
        try {
            NoteDetails note = NoteDAO.getNoteById(noteId, conn);
            req.setAttribute("note", note);
            RequestDispatcher dispatcher = req.getRequestDispatcher("editNote.jsp");
            dispatcher.forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("noteTitle");
        String content = req.getParameter("noteContent");
        NoteDetails note = new NoteDetails();
        note.setTitle(title);
        note.setContent(content);
        note.setId(Integer.parseInt(req.getParameter("noteId")));
        try {
            Connection conn = (Connection) getServletContext().getAttribute("conn");
            conn.setAutoCommit(false);
            boolean flag = NoteDAO.editNote(note, conn);
            HttpSession session = req.getSession();
            if (flag) {
                conn.commit();
                session.setAttribute("edited", "Note edited successfully");
                System.out.println("note edited successfully");
                resp.sendRedirect("editNote.jsp");
            } else {
                conn.rollback();
                System.out.println("failed to edit note");
                resp.sendRedirect("editNote.jsp");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
