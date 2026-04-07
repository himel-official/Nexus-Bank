package com.nexusbank.servlet;

import com.nexusbank.dao.AccountDAO;
import com.nexusbank.model.Account;
import com.nexusbank.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("currentUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("currentUser");
        
        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.getAccountByUserId(user.getId());

        // Put data in request for JSP
        request.setAttribute("user", user);
        request.setAttribute("account", account);

        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}