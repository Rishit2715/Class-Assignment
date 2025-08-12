package com.tss.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.service.UsersService;

@WebServlet("/SelectController")
public class SelectController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsersService userService;

    @Override
    public void init() throws ServletException {
        userService = new UsersService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String theme = request.getParameter("theme");

        boolean isValidUser = userService.validateUser(username, password, role);

        if (isValidUser) {
            request.setAttribute("username", username);
            request.setAttribute("role", role);
            request.setAttribute("theme", theme);

            RequestDispatcher dispatcher = null;
            if (role.equalsIgnoreCase("Admin")) {
                dispatcher = request.getRequestDispatcher("adminController");
            } else {
                dispatcher = request.getRequestDispatcher("customerController");
            }
            dispatcher.forward(request, response);
        } else {
            response.setContentType("text/html");
            response.getWriter().println("<h3>Invalid credentials! Try again.</h3>");
        }
    }
}
