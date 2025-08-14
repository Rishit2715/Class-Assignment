package com.tss.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.tss.exception.AppException;
import com.tss.model.User;
import com.tss.service.UserService;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setRole("EMPLOYEE");

        String result = null;
        try {
            result = userService.registerEmployee(user);
        } catch (AppException e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred during registration.");
            request.getRequestDispatcher("registration.jsp").forward(request, response);
            return;
        }

        if ("SUCCESS".equals(result)) {
            // Optional auto-login
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", "EMPLOYEE");

            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(60 * 60 * 24);
            response.addCookie(cookie);

            // Redirect to login.jsp with success message
            response.sendRedirect("login.jsp?success=Registration successful! Please log in.");
        } else {
            request.setAttribute("error", result);
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        }
    }
}
