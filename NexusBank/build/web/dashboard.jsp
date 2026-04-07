<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.nexusbank.model.User" %>
<%@ page import="com.nexusbank.model.Account" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nexus Bank - Dashboard</title>
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
            border-radius: 12px; 
            padding: 30px; 
            box-shadow: 0 0 20px rgba(0,0,0,0.1); 
            margin-bottom: 25px; 
        }
        h1 { margin: 0; font-size: 28px; }
        .balance { 
            font-size: 32px; 
            color: #27ae60; 
            font-weight: bold; 
            margin: 15px 0; 
        }
        .menu { 
            display: flex; 
            flex-wrap: wrap; 
            gap: 15px; 
            margin-top: 25px; 
        }
        .menu a {
            flex: 1;
            min-width: 220px;
            padding: 18px 25px;
            background: #3498db;
            color: white;
            text-decoration: none;
            border-radius: 10px;
            font-size: 17px;
            text-align: center;
            transition: 0.3s;
        }
        .menu a:hover {
            background: #2980b9;
            transform: translateY(-3px);
        }
        .history-btn {
            background: #9b59b6 !important;
        }
        .history-btn:hover {
            background: #8e44ad !important;
        }
        .logout {
            background: #e74c3c !important;
        }
        .info {
            color: #7f8c8d;
            font-size: 15px;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>Nexus Bank</h1>
        <div>
            Welcome, 
            <strong>
                <%= (session.getAttribute("currentUser") != null) 
                    ? ((User)session.getAttribute("currentUser")).getFullName() 
                    : "User" %>
            </strong> 
            | 
            <a href="logout" style="color:white; margin-left:15px;">Logout</a>
        </div>
    </div>

    <div class="container">
        <div class="card">
            <h2>Account Overview</h2>
            
            <%
                User user = (User) session.getAttribute("currentUser");
                Account account = (Account) request.getAttribute("account");
                
                if (account != null) {
            %>
                <p><strong>Account Number:</strong> <%= account.getAccountNumber() %></p>
                <p class="balance">Balance: ৳<%= String.format("%.2f", account.getBalance()) %></p>
            <%
                } else {
            %>
                <p style="color:red;">Account information not available.</p>
            <%
                }
            %>
        </div>

        <div class="menu">
            <a href="deposit.jsp"> Deposit Money</a>
            <a href="withdraw.jsp"> Withdraw Money</a>
            <a href="transfer.jsp"> Transfer Money</a>
            <a href="history.jsp" class="history-btn"> Transaction History</a>
        </div>

        <p class="info" style="margin-top: 30px; text-align:center;">
            Last updated: <%= new java.util.Date() %>
        </p>
    </div>
</body>
</html>