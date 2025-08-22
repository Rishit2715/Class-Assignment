// File: src/com/tss/controller/ApproveAccountServlet.java
package com.tss.controller;

import com.tss.dao.AdminDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/approveAccount")
public class ApproveAccountServlet extends HttpServlet {
    private AdminDAO adminDAO = new AdminDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check if admin
        HttpSession session = request.getSession();
        if (!"admin".equals(session.getAttribute("role"))) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String accNo = request.getParameter("accNo");
        if (accNo == null || accNo.trim().isEmpty()) {
            request.setAttribute("error", "Invalid account number");
            request.getRequestDispatcher("/admin/manage_customers").forward(request, response);
            return;
        }

        try {
            adminDAO.approveAccount(accNo);
            System.out.println("✅ Admin approved account: " + accNo);
            request.setAttribute("message", "✅ Account approved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "❌ Approval failed: " + e.getMessage());
        }

        // Redirect back to manage customers
        request.getRequestDispatcher("/admin/manage_customers").forward(request, response);
    }

    // Optional: Handle GET requests (e.g., block direct access)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/admin/manage_customers");
    }
}