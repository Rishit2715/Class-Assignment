package com.tss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/customerController")
public class CustomerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = (String) request.getAttribute("username");
        String role = (String) request.getAttribute("role");
        String theme = (String) request.getAttribute("theme");

        String color = theme.equalsIgnoreCase("Green") ? "green" : "blue";

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body style='color:" + color + ";'>");
        out.println("<h2>Good Morning, " + username + " (" + role + ")</h2>");
        out.println("</body></html>");
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
