// File: src/com/tss/controller/CustomerDashboardServlet.java
package com.tss.controller;

import com.tss.dao.AccountDAO;
import com.tss.model.Account;
import com.tss.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/customer/dashboard")
public class CustomerDashboardServlet extends HttpServlet {
    private AccountDAO accountDAO = new AccountDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Object userObj = session.getAttribute("user");
        Object userIdObj = session.getAttribute("userId");

        // 🔴 Redirect to login if not logged in
        if (userObj == null || userIdObj == null) {
            response.sendRedirect("../login.jsp");
            return;
        }

        Customer customer = (Customer) userObj;
        int custId = (Integer) userIdObj;

        try {
            List<Account> accounts = accountDAO.getApprovedAccounts(custId);
            request.setAttribute("accounts", accounts);
            System.out.println("✅ Dashboard: Loaded " + accounts.size() + " accounts");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to load accounts.");
        }

        // ✅ Forward to JSP
        request.getRequestDispatcher("/customer/dashboard.jsp").forward(request, response);
    }
}