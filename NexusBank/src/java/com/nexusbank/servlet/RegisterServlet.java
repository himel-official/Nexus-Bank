package com.nexusbank.servlet;

import com.nexusbank.dao.UserDAO;
import com.nexusbank.model.User;
import com.nexusbank.util.PasswordUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");

        // Basic validation
        if (username == null || password == null || fullName == null || email == null ||
            username.trim().isEmpty() || password.length() < 6) {
            
            request.setAttribute("error", "All fields are required and password must be at least 6 characters");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        UserDAO userDAO = new UserDAO();
        String hashedPassword = PasswordUtil.hashPassword(password);

        User newUser = new User(username, hashedPassword, fullName, email);

        if (userDAO.registerUser(newUser)) {
            request.setAttribute("success", "Registration successful! Please login.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Username already exists or registration failed");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}