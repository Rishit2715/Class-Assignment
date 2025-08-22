// File: src/com/tss/controller/RejectCustomerServlet.java
package com.tss.controller;

import com.tss.dao.AdminDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/rejectCustomer")
public class RejectCustomerServlet extends HttpServlet {
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
            adminDAO.rejectCustomerAccounts(customerId);
            request.setAttribute("message", "❌ Customer request rejected.");
            System.out.println("❌ Admin rejected customer ID: " + customerId);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "❌ Rejection failed: " + e.getMessage());
        }

        request.getRequestDispatcher("/admin/manage_customers").forward(request, response);
    }
}