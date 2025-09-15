package com.DAO;

import java.security.MessageDigest;
import java.sql.*;
import java.util.*;

import com.entity.User;

public class UserDAOImpl implements UserDAO {
	private Connection conn;

	public UserDAOImpl(Connection conn) {
		this.conn = conn;
	}
    // Register new user
    public boolean userRegister(User us) {
        boolean success = false;
        try {
            String query = "INSERT INTO user (name, password, phnNo, email) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, us.getName());
            String hashedPassword = hashPasswordSHA256(us.getPassword());
            ps.setString(2, hashedPassword);
            ps.setString(3, us.getPhnNo());
            ps.setString(4, us.getEmail());
            int rowsAffected = ps.executeUpdate();
            success = rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    // Check if user is already registered
    public boolean isUserRegistered(String email) {
        boolean userExists = false;
        String query = "SELECT * FROM user WHERE email = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            userExists = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userExists;
    }

    // Login
    public User login(String email, String password) {
        User us = null;
        try {
            String sql = "SELECT * FROM user WHERE email=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                if (validatePasswordSHA256(password, hashedPassword)) {
                    us = new User();
                    us.setUserId(rs.getInt("userId"));
                    us.setName(rs.getString("name"));
                    us.setPhnNo(rs.getString("phnNo"));
                    us.setEmail(rs.getString("email"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return us;
    }

    private String hashPasswordSHA256(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean validatePasswordSHA256(String plainPassword, String hashedPassword) {
        String hashedInput = hashPasswordSHA256(plainPassword);
        return hashedInput.equals(hashedPassword);
    }

    // Check password by userId
    @Override
    public boolean checkPassword(int userId, String plainPassword) {
        try {
            String sql = "SELECT password FROM user WHERE userId = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String hashedPasswordFromDB = rs.getString("password");
                return validatePasswordSHA256(plainPassword, hashedPasswordFromDB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get user by userId
    public User getUserById(int userId) {
        User user = null;
        try {
            String query = "SELECT * FROM user WHERE userId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhnNo(rs.getString("phnNo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Update user profile
    @Override
    public boolean updateProfile(User us) {
        boolean f = false;
        try {
            String query = "UPDATE user SET name = ?, phnNo = ?, email = ? WHERE userId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, us.getName());
            ps.setString(2, us.getPhnNo());
            ps.setString(3, us.getEmail());
            ps.setInt(4, us.getUserId());
            int i = ps.executeUpdate();
            f = i == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    // Get user details by orderId (fixed to int)
    @Override
    public User getUserDetailsByOrderId(int orderId) {
        User user = null;
        try {
            String query = "SELECT u.name, u.phnNo, u.email "
                    + "FROM user u "
                    + "JOIN `order` o ON u.userId = o.userId "
                    + "WHERE o.orderId = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
                user.setPhnNo(rs.getString("phnNo"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Get all users
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            String query = "SELECT * FROM user";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                user.setPhnNo(rs.getString("phnNo"));
                user.setEmail(rs.getString("email"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}