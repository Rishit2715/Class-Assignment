package com.tss.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.dao.LeaveDao;
import com.tss.exception.AppException;
import com.tss.model.LeaveRequest;

@WebServlet("/updateLeave")
public class UpdateLeaveController extends HttpServlet {
    private LeaveDao leaveDao = new LeaveDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int requestId = Integer.parseInt(request.getParameter("requestId"));
        String leaveType = request.getParameter("leaveType");
        String fromDateStr = request.getParameter("fromDate");
        String toDateStr = request.getParameter("toDate");
        int days = Integer.parseInt(request.getParameter("days"));
        String reason = request.getParameter("reason");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LeaveRequest leave = new LeaveRequest();
        leave.setRequestId(requestId);
        leave.setLeaveType(leaveType);

        try {
            leave.setFromDate(sdf.parse(fromDateStr));
            leave.setToDate(sdf.parse(toDateStr));

            leave.setDays(days);
            leave.setReason(reason);

            boolean updated = leaveDao.updateLeave(leave);
            if (updated) {
                response.sendRedirect(request.getContextPath() + "/leaveHistory");
            } else {
                request.setAttribute("error", "No record updated. It may already be approved/rejected.");
                request.getRequestDispatcher("/editLeave.jsp").forward(request, response);
            }
        } catch (ParseException e) {
            request.setAttribute("error", "Invalid date format. Please use yyyy-MM-dd.");
            request.getRequestDispatcher("/editLeave.jsp").forward(request, response);
        } catch (AppException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/editLeave.jsp").forward(request, response);
        }
    }
}
