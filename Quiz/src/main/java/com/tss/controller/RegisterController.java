package com.tss.controller;

import com.tss.model.User;
import com.tss.service.UserService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get correct field names
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // Create user object
        User user = new User(name, password, email);

        // Register via service
        boolean success = userService.register(user);

        if (success) {
            response.sendRedirect("index.html"); // Redirect to login page on success
        } else {
            response.setContentType("text/html");
            response.getWriter().println("<h3 style='color:red;'>❌ Registration failed. Email or name might already exist.</h3>");
        }
    }
}
