<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nexus Bank - Withdraw</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f4f7f9; }
        .container { max-width: 500px; margin: 50px auto; padding: 30px; background: white; border-radius: 10px; box-shadow: 0 0 15px rgba(0,0,0,0.1); }
        h1 { text-align: center; color: #2c3e50; }
        input { width: 100%; padding: 12px; margin: 10px 0; border: 1px solid #ddd; border-radius: 5px; }
        button { width: 100%; padding: 12px; background: #e74c3c; color: white; border: none; border-radius: 5px; font-size: 16px; cursor: pointer; }
        .back { margin-top: 15px; text-align: center; }
        .success { color: green; text-align: center; }
        .error { color: red; text-align: center; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Withdraw Money</h1>
        
        <% if(request.getAttribute("success") != null) { %>
            <p class="success"><%= request.getAttribute("success") %></p>
        <% } %>
        <% if(request.getAttribute("error") != null) { %>
            <p class="error"><%= request.getAttribute("error") %></p>
        <% } %>

        <form action="withdraw" method="post">
            <input type="number" name="amount" step="0.01" placeholder="Enter amount to withdraw" required>
            <button type="submit">Withdraw Now</button>
        </form>

        <div class="back">
            <a href="dashboard">← Back to Dashboard</a>
        </div>
    </div>
</body>
</html>