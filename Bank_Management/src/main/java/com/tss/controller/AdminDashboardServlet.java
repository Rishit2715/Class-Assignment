package com.tss.controller;

import com.tss.dao.AdminDAO;
import com.tss.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {
    private AdminDAO adminDAO = new AdminDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("🟢 AdminDashboardServlet: Loading dashboard data...");

        HttpSession session = request.getSession();
        Object userObj = session.getAttribute("user");
        Object roleObj = session.getAttribute("role");

        if (userObj == null || !"admin".equals(roleObj)) {
            System.out.println("🔴 Admin not logged in. Redirecting to login.");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        try {
            // Load real stats from database
            int totalCustomers = adminDAO.getTotalCustomers();
            int pendingAccounts = adminDAO.getPendingAccountsCount();
            int approvedAccounts = adminDAO.getApprovedAccountsCount();
            double totalDeposits = adminDAO.getTotalDeposits();
            int activeFDs = adminDAO.getActiveFDsCount();

            // Set attributes for JSP
            request.setAttribute("totalCustomers", totalCustomers);
            request.setAttribute("pendingAccounts", pendingAccounts);
            request.setAttribute("approvedAccounts", approvedAccounts);
            request.setAttribute("totalDeposits", totalDeposits);
            request.setAttribute("activeFDs", activeFDs);

            // ✅ Print to console after setting
            System.out.println("📊 Admin Dashboard Data:");
            System.out.println("   Total Customers: " + totalCustomers);
            System.out.println("   Pending Accounts: " + pendingAccounts);
            System.out.println("   Approved Accounts: " + approvedAccounts);
            System.out.println("   Total Deposits: ₹" + String.format("%.2f", totalDeposits));
            System.out.println("   Active FDs: " + activeFDs);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("❌ Failed to load dashboard data: " + e.getMessage());
            request.setAttribute("error", "Failed to load dashboard data.");
        }

        // Forward to JSP
        try {
            System.out.println("➡️ Forwarding to /admin/dashboard.jsp");
            request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
        } catch (ServletException e) {
            System.err.println("❌ Forward failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}