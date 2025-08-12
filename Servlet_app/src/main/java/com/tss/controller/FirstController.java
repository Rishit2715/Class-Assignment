package com.tss.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/FirstController")
public class FirstController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FirstController() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String firstName = request.getParameter("firstNameTxt");

        HttpSession session = request.getSession();
        session.setAttribute("firstName", firstName);

        writer.print("<html><body>");
        writer.print("<form action='SecondController' method='post'>");
        writer.print("Last Name : <input type='text' name='lastNameTxt'></input>");
        writer.print("<br><br>");
        writer.print("<button>Next</button>");
        writer.print("</form></body></html>");

        writer.close();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
