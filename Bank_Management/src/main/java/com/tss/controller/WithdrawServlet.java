// File: src/com/tss/controller/WithdrawServlet.java
package com.tss.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.dao.AccountDAO;
import com.tss.dao.TransactionDAO;

@WebServlet("/customer/withdraw")
public class WithdrawServlet extends HttpServlet {
    private AccountDAO accountDAO = new AccountDAO();
    private TransactionDAO transactionDAO = new TransactionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int custId = (Integer) userIdObj;
        try {
            java.util.List<com.tss.model.Account> accounts = accountDAO.getApprovedAccounts(custId);
            request.setAttribute("accounts", accounts);
        } catch (SQLException e) {
            e.printStackTrace();
            // Store in session for dashboard
            session.setAttribute("flash_error", "Failed to load accounts.");
        }

        // Forward to modal form (called from dashboard)
        request.getRequestDispatcher("/customer/dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String accNo = request.getParameter("accNo");
        double amount;
        try {
            amount = Double.parseDouble(request.getParameter("amount"));
        } catch (NumberFormatException e) {
            session.setAttribute("flash_error", "Invalid amount entered.");
            response.sendRedirect("dashboard");
            return;
        }
        String remarks = request.getParameter("remarks");

        Connection conn = null;

        try {
            conn = accountDAO.getConnection();
            conn.setAutoCommit(false);

            // Validate account
            if (!accountDAO.isAccountOfCustomer(accNo, (Integer) userIdObj)) {
                throw new IllegalArgumentException("Invalid account selected.");
            }

            // Get current balance
            double currentBalance = accountDAO.getBalance(accNo, conn);

            // Prevent overdraft
            if (currentBalance < amount) {
                throw new IllegalArgumentException("Insufficient balance. Available: ₹" + String.format("%.2f", currentBalance));
            }

            // New balance
            double newBalance = currentBalance - amount;

            // Update balance
            accountDAO.updateBalance(accNo, newBalance, conn);

            // Log transaction
            transactionDAO.insertTransaction(
                accNo, 
                "withdraw", 
                amount, 
                newBalance,
                "Cash Withdrawal: " + (remarks != null ? remarks : "No remarks"),
                null, 
                conn
            );

            conn.commit();

            // ✅ Success: Use session flash + redirect
            session.setAttribute("flash_success", "✅ Withdrawal successful! ₹" + String.format("%.2f", amount) + " withdrawn.");

        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            session.setAttribute("flash_error", "❌ Withdrawal failed: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // ✅ Redirect (not forward) to preserve messages
        response.sendRedirect("dashboard");
    }
}