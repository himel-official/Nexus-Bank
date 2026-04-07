<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nexus Bank - Login</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f4f7f9; }
        .container { max-width: 400px; margin: 100px auto; padding: 30px; background: white; border-radius: 10px; box-shadow: 0 0 15px rgba(0,0,0,0.1); }
        h1 { text-align: center; color: #2c3e50; }
        input { width: 100%; padding: 12px; margin: 10px 0; border: 1px solid #ddd; border-radius: 5px; }
        button { width: 100%; padding: 12px; background: #3498db; color: white; border: none; border-radius: 5px; font-size: 16px; cursor: pointer; }
        button:hover { background: #2980b9; }
        .error { color: red; text-align: center; }
        .link { text-align: center; margin-top: 15px; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Nexus Bank</h1>
        <h2>Login</h2>
        
        <% if(request.getAttribute("error") != null) { %>
            <p class="error"><%= request.getAttribute("error") %></p>
        <% } %>
        
        <form action="login" method="post">
            <input type="text" name="username" placeholder="Himel" required>
            <input type="password" name="password" placeholder="123456" required>
            <button type="submit">Login</button>
        </form>
        
        <div class="link">
            Don't have an account? <a href="register.jsp">Register here</a>
        </div>
    </div>
</body>
</html>