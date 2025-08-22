// File: src/com/tss/controller/ManageAccountsServlet.java
package com.tss.controller;

import com.tss.dao.AdminDAO;
import com.tss.model.CustomerWithAccountStatus;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/manage_accounts")
public class ManageAccountsServlet extends HttpServlet {
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
            List<CustomerWithAccountStatus> accounts = adminDAO.getAllCustomersWithStatus();
            request.setAttribute("accounts", accounts);
            System.out.println("📊 Admin: Loaded " + accounts.size() + " accounts for management");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to load accounts.");
        }

        request.getRequestDispatcher("/admin/manage_accounts.jsp").forward(request, response);
    }
}