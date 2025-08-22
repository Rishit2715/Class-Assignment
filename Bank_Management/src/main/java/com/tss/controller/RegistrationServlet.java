package com.tss.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.service.CustomerService;

// ✅ Fixed: Add leading slash
@WebServlet("/controller/register")
public class RegistrationServlet extends HttpServlet {
    private CustomerService customerService = new CustomerService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        boolean success = customerService.registerCustomer(username, password, name, email, phone, address);

        if (success) {
            request.setAttribute("success", "Registration successful! You can now login.");
            request.getRequestDispatcher("../login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Username or email already exists.");
            request.getRequestDispatcher("../register.jsp").forward(request, response);
        }
    }
}