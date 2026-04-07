<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nexus Bank - Register</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f4f7f9; }
        .container { max-width: 450px; margin: 80px auto; padding: 30px; background: white; border-radius: 10px; box-shadow: 0 0 15px rgba(0,0,0,0.1); }
        h1 { text-align: center; color: #2c3e50; }
        input { width: 100%; padding: 12px; margin: 8px 0; border: 1px solid #ddd; border-radius: 5px; box-sizing: border-box; }
        button { width: 100%; padding: 12px; background: #27ae60; color: white; border: none; border-radius: 5px; font-size: 16px; cursor: pointer; }
        button:hover { background: #219653; }
        .error { color: red; text-align: center; }
        .success { color: green; text-align: center; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Nexus Bank</h1>
        <h2>Create New Account</h2>
        
        <% if(request.getAttribute("error") != null) { %>
            <p class="error"><%= request.getAttribute("error") %></p>
        <% } %>
        <% if(request.getAttribute("success") != null) { %>
            <p class="success"><%= request.getAttribute("success") %></p>
        <% } %>
        
        <form action="register" method="post">
            <input type="text" name="username" placeholder="himel" required>
            <input type="text" name="fullName" placeholder="Himel Mahmud" required>
            <input type="email" name="email" placeholder="Himel@gmail.com" required>
            <input type="password" name="password" placeholder="Password (min 6 characters)" required>
            <button type="submit">Register</button>
        </form>
        
        <div style="text-align:center; margin-top:15px;">
            Already have an account? <a href="login.jsp">Login here</a>
        </div>
    </div>
</body>
</html>