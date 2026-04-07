package com.nexusbank.dao;

import com.nexusbank.model.Account;
import com.nexusbank.model.Transaction;
import com.nexusbank.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    // Get account by user ID
    public Account getAccountByUserId(int userId) {
        String sql = "SELECT * FROM accounts WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Account account = new Account(
                    rs.getInt("user_id"),
                    rs.getString("account_number"),
                    rs.getDouble("balance")
                );
                account.setAccountId(rs.getInt("account_id"));
                return account;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update account balance
    public boolean updateBalance(int accountId, double newBalance) {
        String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, newBalance);
            pstmt.setInt(2, accountId);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ==================== TRANSACTION METHODS ====================

    // Record a new transaction
    public boolean recordTransaction(int userId, int accountId, String type, 
                                     double amount, String description) {
        
        String sql = "INSERT INTO transactions (user_id, account_id, transaction_type, amount, description) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, accountId);
            pstmt.setString(3, type);
            pstmt.setDouble(4, amount);
            pstmt.setString(5, description);
            
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get transaction history for a user (last 20 transactions)
    public List<Transaction> getTransactionHistory(int userId) {
        List<Transaction> transactions = new ArrayList<>();
        
        String sql = "SELECT * FROM transactions WHERE user_id = ? "
                   + "ORDER BY transaction_date DESC LIMIT 20";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Transaction t = new Transaction(
                    rs.getInt("user_id"),
                    rs.getInt("account_id"),
                    rs.getString("transaction_type"),
                    rs.getDouble("amount"),
                    rs.getString("description")
                );
                t.setTransactionId(rs.getInt("transaction_id"));
                t.setTransactionDate(rs.getTimestamp("transaction_date"));
                transactions.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }
}