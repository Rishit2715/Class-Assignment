package com.tss.controller;

import com.tss.service.AccountService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// ✅ Correct URL pattern to match the form action
@WebServlet("/customer/openAccount")
public class OpenAccountServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AccountService accountService = new AccountService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check if user is logged in
        HttpSession session = request.getSession();
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            response.sendRedirect("../login.jsp");
            return;
        }

        // Extract user ID
        Integer custId = (Integer) session.getAttribute("userId");
        System.out.println("DEBUG - custId from session: " + custId);  // 👈 Add this

        if (custId == null) {
            request.setAttribute("error", "Session expired. Please login again.");
            request.getRequestDispatcher("../login.jsp").forward(request, response);
            return;
        }
        // Get form data
        String accountType = request.getParameter("accountType");

        // Validate input
        if (accountType == null || (!accountType.equals("Savings") && !accountType.equals("Current"))) {
            request.setAttribute("error", "Please select a valid account type.");
            request.getRequestDispatcher("/customer/open_account.jsp").forward(request, response);
            return;
        }

        // Request account via service
        String result = accountService.requestAccount(custId, accountType);

        if (result.startsWith("success:")) {
            request.setAttribute("success", result.substring(8));
        } else {
            request.setAttribute("error", result);
        }

        // Forward back to the form
        request.getRequestDispatcher("/customer/open_account.jsp").forward(request, response);
    }
}