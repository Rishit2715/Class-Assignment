// File: com.tss.controller.TransactionsServlet.java
package com.tss.controller;

import com.tss.dao.TransactionDAO;
import com.tss.model.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/transactions")
public class TransactionsServlet extends HttpServlet {
    private TransactionDAO transactionDAO = new TransactionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Object roleObj = session.getAttribute("role");
        if (!"admin".equals(roleObj)) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        try {
            List<Transaction> transactions = transactionDAO.getAllTransactions();
            request.setAttribute("transactions", transactions);
            System.out.println("📊 Admin: Loaded " + transactions.size() + " transactions");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to load transactions.");
        }

        request.getRequestDispatcher("/admin/transactions.jsp").forward(request, response);
    }
}