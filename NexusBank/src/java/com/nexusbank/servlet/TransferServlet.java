package com.nexusbank.servlet;

import com.nexusbank.dao.AccountDAO;
import com.nexusbank.model.Account;
import com.nexusbank.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {

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

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("currentUser");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String description = "Transfer to " + request.getParameter("toAccountNumber");

        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.getAccountByUserId(user.getId());

        if (account != null && amount > 0 && account.getBalance() >= amount) {
            if (account.transfer(amount)) {
                boolean success = accountDAO.updateBalance(account.getAccountId(), account.getBalance());
                
                if (success) {
                    accountDAO.recordTransaction(user.getId(), account.getAccountId(), 
                                               "TRANSFER", amount, description);
                    
                    request.setAttribute("success", "Transfer of $" + amount + " successful!");
                }
            }
        } else {
            request.setAttribute("error", "Insufficient balance or invalid amount!");
        }

        request.getRequestDispatcher("transfer.jsp").forward(request, response);
    }
}