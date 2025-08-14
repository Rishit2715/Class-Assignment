package com.tss.controller;

import com.tss.exception.AppException;
import com.tss.model.User;
import com.tss.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/auth")
public class AuthController extends HttpServlet {
    private AuthService auth = new AuthService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            User u = auth.login(username, password);
            if (u == null) {
                req.setAttribute("error", "Invalid credentials");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
                return;
            }
            // store in session
            HttpSession session = req.getSession(true);
            session.setAttribute("user", u);
            session.setMaxInactiveInterval(30*60);

            // set a cookie (example)
            Cookie ck = new Cookie("uname", u.getUsername());
            ck.setMaxAge(7*24*3600);
            resp.addCookie(ck);

            // redirect based on role
            if ("ADMIN".equals(u.getRole())) {
                resp.sendRedirect(req.getContextPath()+"/admin");
            } else {
                resp.sendRedirect(req.getContextPath()+"/employee");
            }
        } catch (AppException e) {
            req.setAttribute("error", "Server error: " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
