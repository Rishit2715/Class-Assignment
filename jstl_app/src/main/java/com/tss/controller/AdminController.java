package com.tss.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.exception.AppException;
import com.tss.model.LeaveRequest;
import com.tss.model.User;
import com.tss.service.LeaveService;

@WebServlet("/admin")
public class AdminController extends HttpServlet {
    private LeaveService service = new LeaveService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession s = req.getSession(false);
        if (s == null || s.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/auth");
            return;
        }

        User u = (User) s.getAttribute("user");
        if (!"ADMIN".equals(u.getRole())) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Not allowed");
            return;
        }

        loadLeaveLists(req); // ✅ Centralized method to load pending & past
        req.getRequestDispatcher("adminDashboard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession s = req.getSession(false);
        if (s == null || s.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/auth");
            return;
        }

        User u = (User) s.getAttribute("user");
        if (!"ADMIN".equals(u.getRole())) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Not allowed");
            return;
        }

        try {
            String action = req.getParameter("action");
            int requestId = Integer.parseInt(req.getParameter("requestId"));
            String comment = req.getParameter("comment");

            if ("approve".equalsIgnoreCase(action)) {
                service.processRequest(requestId, u.getUserId(), true, comment);
                req.setAttribute("success", "Leave request approved successfully.");
            } else if ("reject".equalsIgnoreCase(action)) {
                service.processRequest(requestId, u.getUserId(), false, comment);
                req.setAttribute("success", "Leave request rejected successfully.");
            }
        } catch (AppException e) {
            req.setAttribute("error", e.getMessage());
        }

        loadLeaveLists(req); // ✅ Always refresh after action
        req.getRequestDispatcher("adminDashboard.jsp").forward(req, resp);
    }

    /**
     * Loads both pending and past leave requests into request attributes
     */
    private void loadLeaveLists(HttpServletRequest req) {
        try {
            List<LeaveRequest> pending = service.getPendingRequests(); // PENDING only
            List<LeaveRequest> past = service.getPastRequests(); // APPROVED + REJECTED
            req.setAttribute("pending", pending);
            req.setAttribute("past", past);
        } catch (AppException e) {
            req.setAttribute("error", e.getMessage());
        }
    }
}
