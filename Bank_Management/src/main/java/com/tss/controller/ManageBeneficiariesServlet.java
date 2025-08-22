package com.tss.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.dao.BeneficiaryDAO;
import com.tss.database.DBConnection;
import com.tss.model.Beneficiary;

public class ManageBeneficiariesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customer") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int custId = ((com.tss.model.Customer) session.getAttribute("customer")).getId();
        BeneficiaryDAO dao1 = new BeneficiaryDAO();
        String customerAccNo = null;
		try {
			customerAccNo = dao1.getCustomerAccount(custId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // fetch acc_no from DB

        try (Connection conn = DBConnection.getConnection()) {
            BeneficiaryDAO dao = new BeneficiaryDAO();
            List<Beneficiary> beneficiaries = dao.getBeneficiaries(customerAccNo);
            request.setAttribute("beneficiaries", beneficiaries);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Unable to fetch beneficiaries.");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/customer/manage_beneficiaries.jsp");
        rd.forward(request, response);
    }
}
