package com.tss.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter writer = response.getWriter();

    String password = request.getParameter("password");
    String confirmPassword = request.getParameter("confirm_password");

    writer.println("<html><body>");

    if (password != null && password.equals(confirmPassword)) {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String gender = request.getParameter("gender");
        String city = request.getParameter("city");
        String[] languages = request.getParameterValues("language");
        String username = request.getParameter("name");

        writer.println("<h2>Registration Details</h2>");
        writer.println("<b>Name:</b> " + name + "<br><br>");
        writer.println("<b>Address:</b> " + address + "<br><br>");
        writer.println("<b>Gender:</b> " + gender + "<br><br>");
        writer.println("<b>City:</b> " + city + "<br><br>");

        writer.println("<b>Languages Known:</b><br>");
        if (languages != null) {
            for (String lang : languages) {
                writer.println("- " + lang + "<br>");
            }
        } else {
            writer.println("None<br>");
        }

        writer.println("<br><b>Username:</b> " + username + "<br><br>");
        writer.println("<b>Password:</b> " + password + "<br><br>");
    } else {
        writer.println("Error: Password and Confirm Password do not match!</h2>");
    }

    writer.println("</body></html>");
    writer.close();
}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
