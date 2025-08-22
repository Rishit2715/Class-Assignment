package com.tss.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.dao.AdminDAO;

@WebServlet("/admin/rejectAccount")
public class RejectAccountServlet extends HttpServlet {
    private AdminDAO adminDAO = new AdminDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
            adminDAO.rejectAccount(accNo);
            System.out.println("❌ Admin rejected account: " + accNo);
            request.setAttribute("message", "❌ Account rejected.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "❌ Rejection failed: " + e.getMessage());
        }

        request.getRequestDispatcher("/admin/manage_customers").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/admin/manage_customers");
    }
}