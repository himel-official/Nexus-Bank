package com.nexusbank.dao;

import com.nexusbank.model.User;
import com.nexusbank.util.DBConnection;
import com.nexusbank.util.PasswordUtil;
import java.sql.*;

public class UserDAO {

    // Register a new user
    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (username, password_hash, full_name, email) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPasswordHash());
            pstmt.setString(3, user.getFullName());
            pstmt.setString(4, user.getEmail());

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                // Get generated user ID and create account
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int userId = rs.getInt(1);
                    createDefaultAccount(userId);
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Login user
    public User loginUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password_hash");

                if (PasswordUtil.checkPassword(password, storedHash)) {
                    User user = new User(
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        rs.getString("full_name"),
                        rs.getString("email")
                    );
                    user.setId(rs.getInt("id"));
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Create default account after registration
    private void createDefaultAccount(int userId) {
        String accountNumber = "NB" + System.currentTimeMillis();
        String sql = "INSERT INTO accounts (user_id, account_number, balance) VALUES (?, ?, 5000.00)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, accountNumber);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get user by username
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User(
                    rs.getString("username"),
                    rs.getString("password_hash"),
                    rs.getString("full_name"),
                    rs.getString("email")
                );
                user.setId(rs.getInt("id"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}