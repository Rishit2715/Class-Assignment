package com.tss.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.dao.AccountDAO;
import com.tss.dao.BeneficiaryDAO;
import com.tss.dao.TransactionDAO;
import com.tss.model.Account;
import com.tss.model.Beneficiary;
import com.tss.model.Transaction;

@WebServlet("/customer/transfer")
public class TransferServlet extends HttpServlet {
    private AccountDAO accountDAO = new AccountDAO();
    private BeneficiaryDAO beneficiaryDAO = new BeneficiaryDAO();
    private TransactionDAO transactionDAO = new TransactionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("🟢 TransferServlet: doGet() STARTED");

        HttpSession session = request.getSession();
        Object userIdObj = session.getAttribute("userId");

        // Debug session
        System.out.println("🟢 Session ID: " + session.getId());
        System.out.println("🟢 userId from session: " + userIdObj);

        if (userIdObj == null) {
            System.out.println("❌ No userId in session, redirecting to login");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int custId = (Integer) userIdObj;
        List<Account> approvedAccounts = null;
        List<Beneficiary> beneficiaries = null;
        List<Transaction> transactions = null;
        String error = null;

        try {
            // Step 1: Load approved accounts
            System.out.println("🔍 Loading approved accounts for custId = " + custId);
            approvedAccounts = accountDAO.getApprovedAccounts(custId);
            System.out.println("✅ Loaded " + (approvedAccounts != null ? approvedAccounts.size() : 0) + " approved accounts");

            if (approvedAccounts == null || approvedAccounts.isEmpty()) {
                error = "No approved account found. Cannot transfer funds.";
                System.out.println("⚠️ " + error);
            } else {
                // Load beneficiaries for the customer (not per account)
                System.out.println("🔍 Loading beneficiaries for customer ID = " + custId);
                beneficiaries = beneficiaryDAO.getBeneficiariesByCustomerId(custId);
                System.out.println("✅ Loaded " + (beneficiaries != null ? beneficiaries.size() : 0) + " beneficiaries");

                // Load transaction history
                System.out.println("🔍 Loading transactions for custId = " + custId);
                transactions = transactionDAO.getTransactionsByCustomer(custId);
                System.out.println("✅ Loaded " + (transactions != null ? transactions.size() : 0) + " transactions");
            }

        } catch (Exception e) {
            System.err.println("❌ Error in TransferServlet doGet: " + e.getMessage());
            e.printStackTrace();
            error = "Failed to load data: " + e.getMessage();
        }

        // Set attributes
        request.setAttribute("approvedAccounts", approvedAccounts);
        request.setAttribute("beneficiaries", beneficiaries);
        request.setAttribute("transactions", transactions);
        if (error != null) {
            request.setAttribute("error", error);
        }

        // Forward to JSP
        try {
            System.out.println("➡️ Forwarding to transfer.jsp");
            request.getRequestDispatcher("/customer/transfer.jsp").forward(request, response);
        } catch (ServletException e) {
            System.err.println("❌ Forward failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("🟢 TransferServlet: doPost() STARTED");

        HttpSession session = request.getSession();
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int custId = (Integer) userIdObj;
        String remarks = request.getParameter("remarks");
        double amount;
        
        try {
            amount = Double.parseDouble(request.getParameter("amount"));
            if (amount <= 0) {
                throw new NumberFormatException("Amount must be greater than 0");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid amount: " + e.getMessage());
            doGet(request, response);
            return;
        }

        Connection conn = null;
        String senderAccNo = null;
        String receiverAccNo = null;

        try {
            conn = accountDAO.getConnection();
            if (conn == null) {
                throw new SQLException("Database connection failed");
            }
            
            conn.setAutoCommit(false);

            // Get sender account
            senderAccNo = request.getParameter("fromAccount");
            if (senderAccNo == null || senderAccNo.trim().isEmpty()) {
                throw new IllegalArgumentException("Please select an account to transfer from.");
            }

            // Validate sender account
            if (!accountDAO.isAccountOfCustomer(senderAccNo, custId)) {
                throw new IllegalArgumentException("Selected account is not valid or not approved.");
            }

            // Get transfer mode
            String transferMode = request.getParameter("transferMode");
            System.out.println("🟢 Transfer mode: " + transferMode);

            if ("direct".equals(transferMode)) {
                receiverAccNo = request.getParameter("toAccount");
                if (receiverAccNo == null || receiverAccNo.trim().isEmpty()) {
                    throw new IllegalArgumentException("Please enter receiver account number.");
                }
                // Validate receiver account format
                if (!receiverAccNo.matches("\\d{11,16}")) {
                    throw new IllegalArgumentException("Receiver account number must be 11-16 digits.");
                }
            } else if ("beneficiary".equals(transferMode)) {
                receiverAccNo = request.getParameter("toBeneficiary");
                if (receiverAccNo == null || receiverAccNo.trim().isEmpty()) {
                    throw new IllegalArgumentException("Please select a beneficiary.");
                }
            } else {
                throw new IllegalArgumentException("Invalid transfer method selected.");
            }

            System.out.println("🟢 Sender: " + senderAccNo + ", Receiver: " + receiverAccNo);

            // Prevent self-transfer
            if (senderAccNo.equals(receiverAccNo)) {
                throw new IllegalArgumentException("You cannot transfer money to your own account.");
            }

            // Check sender balance
            double senderBalance = accountDAO.getBalance(senderAccNo, conn);
            System.out.println("🟢 Sender balance: " + senderBalance);
            
            if (senderBalance < amount) {
                throw new IllegalArgumentException("Insufficient balance. Available: ₹" + String.format("%.2f", senderBalance));
            }

            // Validate receiver account exists and is approved
            if (!beneficiaryDAO.isApprovedAccount(receiverAccNo)) {
                throw new IllegalArgumentException("Receiver account not found or not approved.");
            }

            double receiverBalance = accountDAO.getBalance(receiverAccNo, conn);
            System.out.println("🟢 Receiver balance: " + receiverBalance);

            // Debit sender
            accountDAO.updateBalance(senderAccNo, senderBalance - amount, conn);
            System.out.println("🟢 Debited sender: " + (senderBalance - amount));

            // Credit receiver
            accountDAO.updateBalance(receiverAccNo, receiverBalance + amount, conn);
            System.out.println("🟢 Credited receiver: " + (receiverBalance + amount));

            // Log transactions
            transactionDAO.insertTransaction(
                senderAccNo, "transfer_out", amount, senderBalance - amount,
                "Transfer to " + maskAccount(receiverAccNo) + ". " + (remarks != null ? remarks : ""), receiverAccNo, conn
            );
            transactionDAO.insertTransaction(
                receiverAccNo, "transfer_in", amount, receiverBalance + amount,
                "Received from " + maskAccount(senderAccNo) + ". " + (remarks != null ? remarks : ""), senderAccNo, conn
            );

            conn.commit();
            System.out.println("🟢 Transfer committed successfully");

            request.setAttribute("success", "✅ Transfer successful! ₹" + String.format("%.2f", amount) + " sent to account " + maskAccount(receiverAccNo) + ".");

        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("🟢 Transaction rolled back");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            System.err.println("❌ Transfer failed: " + e.getMessage());
            request.setAttribute("error", "❌ Transfer failed: " + e.getMessage());
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

        // Reload with updated data
        doGet(request, response);
    }

    private String maskAccount(String accNo) {
        if (accNo == null || accNo.length() < 4) return "****";
        return "****" + accNo.substring(accNo.length() - 4);
    }
}