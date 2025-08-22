// File: com.tss.controller.DeleteCustomerServlet.java
package com.tss.controller;

import com.tss.dao.AdminDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/deleteCustomer")
public class DeleteCustomerServlet extends HttpServlet {
    private AdminDAO adminDAO = new AdminDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (!"admin".equals(session.getAttribute("role"))) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int customerId = Integer.parseInt(request.getParameter("customerId"));

        try {
            adminDAO.deleteCustomerAndAllData(customerId);
            request.setAttribute("message", "🗑️ Customer and all related data deleted.");
            System.out.println("🗑️ Admin deleted customer ID: " + customerId);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "❌ Delete failed: " + e.getMessage());
        }

        request.getRequestDispatcher("/admin/manage_customers").forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/admin/manage_customers");
    }
}