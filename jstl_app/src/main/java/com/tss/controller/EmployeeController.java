package com.tss.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.exception.AppException;
import com.tss.model.LeaveBalance;
import com.tss.model.LeaveRequest;
import com.tss.model.User;
import com.tss.service.LeaveService;

@WebServlet("/employee")
public class EmployeeController extends HttpServlet {
    private LeaveService service = new LeaveService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/auth");
            return;
        }

        User u = (User) session.getAttribute("user");
        if (!"EMPLOYEE".equalsIgnoreCase(u.getRole())) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Not allowed");
            return;
        }

        try {
            // Leave balance
            LeaveBalance balance = service.getLeaveBalance(u.getUserId());
            req.setAttribute("balance", balance);

            // User's leave requests
            List<LeaveRequest> myRequests = service.getRequestsByUser(u.getUserId());
            req.setAttribute("myRequests", myRequests);

            req.getRequestDispatcher("employeeDashboard.jsp").forward(req, resp);
        } catch (AppException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("employeeDashboard.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/auth");
            return;
        }

        User u = (User) session.getAttribute("user");
        if (!"EMPLOYEE".equalsIgnoreCase(u.getRole())) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Not allowed");
            return;
        }

        try {
            String action = req.getParameter("action"); // "apply" or "update"
            String leaveType = req.getParameter("leaveType");
            String fromDateStr = req.getParameter("fromDate");
            String toDateStr = req.getParameter("toDate");
            String reason = req.getParameter("reason");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            LeaveRequest leaveReq = new LeaveRequest();
            leaveReq.setUserId(u.getUserId());
            leaveReq.setLeaveType(leaveType);
            leaveReq.setFromDate(sdf.parse(fromDateStr));
            leaveReq.setToDate(sdf.parse(toDateStr));
            long diff = leaveReq.getToDate().getTime() - leaveReq.getFromDate().getTime();
            leaveReq.setDays((int) (diff / (1000 * 60 * 60 * 24)) + 1);
            leaveReq.setReason(reason);

            if ("update".equalsIgnoreCase(action)) {
                // Get requestId for update
                String requestIdStr = req.getParameter("requestId");
                if (requestIdStr != null && !requestIdStr.isEmpty()) {
                    leaveReq.setRequestId(Integer.parseInt(requestIdStr));
                    service.updateLeave(leaveReq); // <-- must exist in LeaveService
                    req.setAttribute("success", "✅ Leave request updated successfully!");
                } else {
                    req.setAttribute("error", "❌ Missing leave request ID for update.");
                }
            } else {
                // Default: apply new leave
                service.applyLeave(leaveReq);
                req.setAttribute("success", "✅ Leave request submitted successfully!");
            }

            doGet(req, resp); // reload page
        } catch (AppException e) {
            req.setAttribute("error", "❌ " + e.getMessage());
            doGet(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", "❌ Invalid date format or unexpected error");
            doGet(req, resp);
        }
    }
}
