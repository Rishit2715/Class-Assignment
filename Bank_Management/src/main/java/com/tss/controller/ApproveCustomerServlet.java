package com.tss.controller;

import com.tss.dao.AdminDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/approveCustomer")
public class ApproveCustomerServlet extends HttpServlet {
    private AdminDAO adminDAO = new AdminDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Check if user is admin
        HttpSession session = request.getSession();
        if (!"admin".equals(session.getAttribute("role"))) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // 2. Get customer ID from form
        String customerIdStr = request.getParameter("customerId");
        if (customerIdStr == null || customerIdStr.trim().isEmpty()) {
            request.setAttribute("error", "Invalid customer ID");
            request.getRequestDispatcher("/admin/manage_customers").forward(request, response);
            return;
        }

        int customerId;
        try {
            customerId = Integer.parseInt(customerIdStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid customer ID format");
            request.getRequestDispatcher("/admin/manage_customers").forward(request, response);
            return;
        }

        // 3. Approve customer: activate + approve accounts
        try {
            adminDAO.activateCustomer(customerId);
            adminDAO.approveAllAccountsOfCustomer(customerId);

            request.setAttribute("message", "✅ Customer approved and activated successfully!");
            System.out.println("✅ Admin approved customer ID: " + customerId);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "❌ Approval failed: " + e.getMessage());
        }

        // 4. Forward back to manage customers
        request.getRequestDispatcher("/admin/manage_customers").forward(request, response);
    }

    // Optional: Handle GET requests (block direct access)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/admin/manage_customers");
    }
}