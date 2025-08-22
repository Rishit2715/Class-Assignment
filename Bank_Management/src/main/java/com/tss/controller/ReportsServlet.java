// File: src/com/tss/controller/ReportsServlet.java
package com.tss.controller;

import com.tss.dao.ReportDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/admin/reports")
public class ReportsServlet extends HttpServlet {
    private ReportDAO reportDAO = new ReportDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Object roleObj = session.getAttribute("role");
        if (!"admin".equals(roleObj)) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        try {
            // Load all real data
            request.setAttribute("totalCustomers", reportDAO.getTotalCustomers());
            request.setAttribute("totalDeposits", reportDAO.getTotalDeposits());
            request.setAttribute("approvedAccounts", reportDAO.getApprovedAccountsCount());
            request.setAttribute("pendingApprovals", reportDAO.getPendingAccountsCount());

            request.setAttribute("monthlyDeposits", reportDAO.getMonthlyTransactionSum("deposit"));
            request.setAttribute("monthlyWithdrawals", reportDAO.getMonthlyTransactionSum("withdraw"));
            request.setAttribute("accountTypeCount", reportDAO.getAccountTypeDistribution());
            request.setAttribute("monthlyGrowth", reportDAO.getMonthlyCustomerGrowth());
            request.setAttribute("balanceByType", reportDAO.getBalanceByAccountType());

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to load reports.");
        }

        request.getRequestDispatcher("/admin/reports.jsp").forward(request, response);
    }
}