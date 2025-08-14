//package com.tss.controller;
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.tss.exception.AppException;
//import com.tss.model.LeaveRequest;
//import com.tss.service.LeaveService;
//
//@WebServlet("/editLeave")
//public class EditLeaveController extends HttpServlet {
//    private LeaveService service = new LeaveService();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int leaveId = Integer.parseInt(req.getParameter("id"));
//        LeaveRequest leave = null;
//		try {
//			leave = service.getLeaveById(leaveId);
//		} catch (AppException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//        // Prevent editing if not pending
//        if (!"PENDING".equalsIgnoreCase(leave.getStatus())) {
//            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Cannot edit approved/rejected leave");
//            return;
//        }
//
//        req.setAttribute("leave", leave);
//        req.getRequestDispatcher("editLeave.jsp").forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
//            int leaveId = Integer.parseInt(req.getParameter("leaveId"));
//            String leaveType = req.getParameter("leaveType");
//            String fromDate = req.getParameter("fromDate");
//            String toDate = req.getParameter("toDate");
//            String reason = req.getParameter("reason");
//
//            LeaveRequest leave = new LeaveRequest();
//            leave.setRequestId(leaveId); // FIXED
//            leave.setLeaveType(leaveType);
//            leave.setFromDate(new SimpleDateFormat("yyyy-MM-dd").parse(fromDate));
//            leave.setToDate(new SimpleDateFormat("yyyy-MM-dd").parse(toDate));
//            long diff = leave.getToDate().getTime() - leave.getFromDate().getTime();
//            leave.setDays((int) (diff / (1000 * 60 * 60 * 24)) + 1);
//            leave.setReason(reason);
//
//            service.updatePendingLeave(leave);
//
//            resp.sendRedirect(req.getContextPath() + "/employee?success=Leave updated successfully");
//        } catch (Exception e) {
//            req.setAttribute("error", "Failed to update leave: " + e.getMessage());
//            req.getRequestDispatcher("editLeave.jsp").forward(req, resp);
//        }
//    }
//}
