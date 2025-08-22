package com.tss.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.model.Customer;
import com.tss.service.CustomerService;

@WebServlet("/controller/login")
public class LoginServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomerService customerService = new CustomerService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Customer customer = customerService.login(username, password);

        if (customer != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", customer);
            session.setAttribute("userId", customer.getId());
            session.setAttribute("role", customer.getRole());

            if ("admin".equals(customer.getRole())) {
                response.sendRedirect("../admin/dashboard");
            } else {
                response.sendRedirect("../customer/dashboard");
            }
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("../login.jsp").forward(request, response);
        }
    }
}