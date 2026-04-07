<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nexus Bank - Welcome</title>
    <style>
        body { 
            font-family: Arial, sans-serif; 
            background: linear-gradient(135deg, #2c3e50, #3498db); 
            color: white; 
            margin: 0; 
            padding: 0; 
            height: 100vh; 
            display: flex; 
            align-items: center; 
            justify-content: center; 
        }
        .welcome-box {
            text-align: center;
            background: rgba(255,255,255,0.1);
            padding: 60px 40px;
            border-radius: 15px;
            backdrop-filter: blur(10px);
            box-shadow: 0 10px 30px rgba(0,0,0,0.3);
            max-width: 600px;
        }
        h1 { font-size: 48px; margin-bottom: 10px; }
        p { font-size: 20px; margin-bottom: 40px; }
        .btn {
            display: inline-block;
            padding: 15px 40px;
            margin: 10px;
            background: #27ae60;
            color: white;
            text-decoration: none;
            border-radius: 50px;
            font-size: 18px;
            transition: 0.3s;
        }
        .btn:hover {
            background: #219653;
            transform: scale(1.05);
        }
        .btn-login {
            background: #3498db;
        }
        .btn-login:hover { background: #2980b9; }
        
        table {
  border: 2px solid #49638F;
}

td, th {
  border: 0;
}
    </style>
</head>
<body>
    <div class="welcome-box">
        <h1>Welcome to Nexus Bank</h1>
        <p>Your Trusted Digital Banking Partner</p>
        <p>built by group 4</p>
        
        <a href="login.jsp" class="btn btn-login">Login</a>
        <a href="register.jsp" class="btn">Create New Account</a>
        
        <p style="margin-top: 40px; font-size: 14px;">
            A simple & secure banking system
        <table align="center">
            <caption>
            Group Members
            </caption>
            <tr style="text-align: left;">
            <td>1. Himel Mahmud</td>
            <td>ID: 20245103282</td>
            </tr>
            <tr style="text-align: left;">
            <td>2. Rabeya Boshri Rima</td>
            <td>ID: 20245103320</td>
            </tr>
            <tr style="text-align: left;">
            <td>3. Anika Islam Mim</td>
            <td>ID: 20245103313</td>
            </tr>
            <tr>
            <td>4. Md. Ridwanur Hassan Raiyan </td>
            <td>ID: 21225103146</td>
            </tr>
            <tr style="text-align: left;">
            <td>5. S.m. Muhimin</td>
            <td>ID: 21225103029</td>
            </tr>
            <tr style="text-align: left;">
            <td>6. Musfika Naznin</td>
            <td>ID: 20245103382</td>
            </tr>
        </table>
    </div>
</body>
</html>