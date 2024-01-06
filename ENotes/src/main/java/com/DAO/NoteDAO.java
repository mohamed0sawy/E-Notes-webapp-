package com.DAO;

import com.User.NoteDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoteDAO {

    public static boolean addNote(NoteDetails note, Connection conn) throws SQLException {
        String query = "insert into note(title, content, userId) values (?,?,?)";
        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, note.getTitle());
            ps.setString(2, note.getContent());
            ps.setInt(3, note.getUser().getId());
            return ps.executeUpdate() > 0;
        }
    }

    public static List<NoteDetails> getAllNotes(int id, Connection conn) throws SQLException {
        List<NoteDetails> list = new ArrayList<>();
        NoteDetails note = new NoteDetails();
        String query = "select * from note where userId=? order by date desc";
        try(PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    note.setId(rs.getInt("id"));
                    note.setTitle(rs.getString("title"));
                    note.setContent(rs.getString("content"));
                    note.setDate(rs.getTimestamp("date").toLocalDateTime());
                    list.add(new NoteDetails(note));
                }
            }
        }
        return list;
    }

    public static NoteDetails getNoteById(int id, Connection conn) throws SQLException {
        NoteDetails note = new NoteDetails();
        String query = "select * from note where id = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    note.setId(rs.getInt("id"));
                    note.setTitle(rs.getString("title"));
                    note.setContent(rs.getString("content"));
                    note.setDate(rs.getTimestamp("date").toLocalDateTime());
                }
            }
        }
        return note;
    }

    public static boolean editNote(NoteDetails note, Connection conn) throws SQLException {
        String query = "update note set title = ?, content = ? where id = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, note.getTitle());
            ps.setString(2, note.getContent());
            ps.setInt(3, note.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public static boolean deleteNote(int id, Connection conn) throws SQLException {
        String query = "delete from note where id = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
