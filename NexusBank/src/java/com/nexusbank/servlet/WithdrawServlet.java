package com.nexusbank.servlet;

import com.nexusbank.dao.AccountDAO;
import com.nexusbank.model.Account;
import com.nexusbank.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/withdraw")
public class WithdrawServlet extends HttpServlet {

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

        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.getAccountByUserId(user.getId());

        if (account != null && amount > 0) {
            if (account.withdraw(amount)) {
                boolean balanceUpdated = accountDAO.updateBalance(account.getAccountId(), account.getBalance());
                
                if (balanceUpdated) {
                    // Record transaction
                    accountDAO.recordTransaction(user.getId(), account.getAccountId(), 
                                               "WITHDRAW", amount, "Withdrawal from account");
                    
                    request.setAttribute("success", "Withdrawal of $" + amount + " successful!");
                } else {
                    request.setAttribute("error", "Failed to update balance!");
                }
            } else {
                request.setAttribute("error", "Insufficient balance!");
            }
        } else {
            request.setAttribute("error", "Invalid amount!");
        }

        request.getRequestDispatcher("withdraw.jsp").forward(request, response);
    }
}