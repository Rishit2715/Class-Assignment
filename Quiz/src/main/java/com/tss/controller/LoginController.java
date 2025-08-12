package com.tss.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.model.User;
import com.tss.service.UserService;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.getUserByCredentials(username, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());      // Assuming getUserId() returns int
            session.setAttribute("username", user.getUsername());  // For display purpose
            session.setAttribute("user", user);                    // Optional: whole object
            response.sendRedirect("QuizController"); // Proceed to quiz
        } else {
            response.setContentType("text/html");
            response.getWriter().println("❌ Invalid credentials. Please try again.");
        }
    }
}
