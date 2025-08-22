// File: src/com/tss/controller/ManageCustomersServlet.java
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
import java.util.List;

@WebServlet("/admin/manage_customers")
public class ManageCustomersServlet extends HttpServlet {
    private AdminDAO adminDAO = new AdminDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (!"admin".equals(session.getAttribute("role"))) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        try {
            List<Customer> customers = adminDAO.getAllCustomers();
            request.setAttribute("customers", customers);
            System.out.println("📊 Admin: Loaded " + customers.size() + " customers");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to load customers.");
        }

        request.getRequestDispatcher("/admin/manage_customers.jsp").forward(request, response);
    }
}