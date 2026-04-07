<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.nexusbank.model.User" %>
<%@ page import="com.nexusbank.model.Transaction" %>
<%@ page import="com.nexusbank.dao.AccountDAO" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nexus Bank - Transaction History</title>
    <style>
        body { 
            font-family: Arial, sans-serif; 
            background: #f4f7f9; 
            margin: 0; 
        }
        .header { 
            background: #2c3e50; 
            color: white; 
            padding: 15px 30px; 
            display: flex; 
            justify-content: space-between; 
            align-items: center; 
        }
        .container { 
            max-width: 1000px; 
            margin: 30px auto; 
            padding: 20px; 
        }
        .card { 
            background: white; 
            border-radius: 10px; 
            padding: 25px; 
            box-shadow: 0 0 15px rgba(0,0,0,0.1); 
        }
        table { 
            width: 100%; 
            border-collapse: collapse; 
            margin-top: 20px; 
        }
        th, td { 
            padding: 12px; 
            text-align: left; 
            border-bottom: 1px solid #ddd; 
        }
        th { 
            background: #3498db; 
            color: white; 
        }
        tr:hover { 
            background: #f1f1f1; 
        }
        .deposit { color: #27ae60; font-weight: bold; }
        .withdraw { color: #e74c3c; font-weight: bold; }
        .transfer { color: #3498db; font-weight: bold; }
        .back-btn {
            display: inline-block;
            padding: 10px 20px;
            background: #2c3e50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        .back-btn:hover { background: #1a252f; }
    </style>
</head>
<body>
    <div class="header">
        <h1>Nexus Bank</h1>
        <div>
            Transaction History | 
            <a href="dashboard" style="color:white; margin-left:15px;">Back to Dashboard</a> |
            <a href="logout" style="color:white; margin-left:15px;">Logout</a>
        </div>
    </div>

    <div class="container">
        <div class="card">
            <h2>Transaction History</h2>
            
            <%
                User user = (User) session.getAttribute("currentUser");
                if (user != null) {
                    AccountDAO accountDAO = new AccountDAO();
                    List<Transaction> transactions = accountDAO.getTransactionHistory(user.getId());
            %>
            
            <% if (transactions.isEmpty()) { %>
                <p style="text-align:center; color:gray; padding:30px;">
                    No transactions yet. Make your first deposit or withdrawal!
                </p>
            <% } else { %>
            
            <table>
                <thead>
                    <tr>
                        <th>Date & Time</th>
                        <th>Type</th>
                        <th>Description</th>
                        <th>Amount</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Transaction t : transactions) { %>
                    <tr>
                        <td><%= t.getTransactionDate() %></td>
                        <td>
                            <span class="<%= t.getTransactionType().toLowerCase() %>">
                                <%= t.getTransactionType() %>
                            </span>
                        </td>
                        <td><%= t.getDescription() != null ? t.getDescription() : "-" %></td>
                        <td>
                            <%= t.getTransactionType().equals("DEPOSIT") ? "+" : "-" %>
                            ৳<%= String.format("%.2f", t.getAmount()) %>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            
            <% } %>
            
            <% } else { %>
                <p style="color:red;">Please login to view transaction history.</p>
            <% } %>
        </div>
    </div>
</body>
</html>