package com.tss.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.dao.BeneficiaryDAO;
import com.tss.service.BeneficiaryService;

@WebServlet("/customer/addBeneficiary")
public class AddBeneficiaryServlet extends HttpServlet {
    private BeneficiaryService beneficiaryService = new BeneficiaryService();
    private BeneficiaryDAO beneficiaryDAO = new BeneficiaryDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Object userObj = session.getAttribute("user");
        Object userIdObj = session.getAttribute("userId");

        // Get form data
        String beneficiaryAccNo = request.getParameter("accNo");  // The account to add
        String nickname = request.getParameter("nickname");

        // Validate session and input
        if (userObj == null || userIdObj == null || beneficiaryAccNo == null || nickname == null) {
            request.setAttribute("error", "Invalid request.");
            request.getRequestDispatcher("/customer/add_beneficiary.jsp").forward(request, response);
            return;
        }

        int custId = (Integer) userIdObj;

        try {
            // 🔹 Step 1: Get customer's own approved account
            String customerAccNo = beneficiaryDAO.getCustomerAccount(custId);
            if (customerAccNo == null) {
                request.setAttribute("error", "You don't have an approved account to link beneficiaries.");
                request.getRequestDispatcher("/customer/add_beneficiary.jsp").forward(request, response);
                return;
            }

            // 🔹 Step 2: Validate beneficiary account
            if (!beneficiaryDAO.isApprovedAccount(beneficiaryAccNo)) {
                request.setAttribute("error", "Beneficiary account does not exist or is not approved.");
                request.getRequestDispatcher("/customer/add_beneficiary.jsp").forward(request, response);
                return;
            }

            // 🔹 Step 4: Prevent duplicate
            if (beneficiaryDAO.exists(customerAccNo, beneficiaryAccNo)) {
                request.setAttribute("error", "This account is already in your beneficiary list.");
                request.getRequestDispatcher("/customer/add_beneficiary.jsp").forward(request, response);
                return;
            }

            // 🔹 Step 5: Add beneficiary
            String result = beneficiaryService.addBeneficiary(
                customerAccNo,     // sender (customer's own account)
                beneficiaryAccNo, // receiver (entered account)
                nickname
            );

            if (result.startsWith("success:")) {
                request.setAttribute("success", result.substring(8));
            } else {
                request.setAttribute("error", result);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred. Please try again.");
        }

        request.getRequestDispatcher("/customer/add_beneficiary.jsp").forward(request, response);
    }
}