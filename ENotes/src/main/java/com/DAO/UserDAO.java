package com.DAO;


import com.User.UserDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public static boolean addUser(UserDetails user, Connection conn) throws SQLException {
        UserDetails userFound = UserDAO.findUserByEmail(user, conn);
        if (userFound != null)
            return false;
        String query = "insert into user(name, email, password) values (?, ?, ?)";
        try(PreparedStatement ps = conn.prepareStatement(query);){
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            return ps.executeUpdate() > 0;
        }
    }

    public static UserDetails findUser(UserDetails user, Connection conn) throws SQLException {
        String query = "select * from user where email = ? and password = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    return user;
                }
            }
            return null;
        }
    }

    public static UserDetails findUserByEmail(UserDetails user, Connection conn) throws SQLException {
        String query = "select * from user where email = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, user.getEmail());
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return user;
                }
            }
            return null;
        }
    }
}
