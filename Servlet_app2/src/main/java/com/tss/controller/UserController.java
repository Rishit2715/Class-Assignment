//package com.tss.controller;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.tss.model.User;
//import com.tss.service.UserService;
//
//@WebServlet("/UserController")
//public class UserController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	private UserService userService = new UserService();
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String username = request.getParameter("useNameTxt");
//		String password = request.getParameter("passwordTxt");
//
//		response.setContentType("text/html");
//
//		User user = new User(username, password);
//		boolean success = userService.registerUser(user);
//
//		if (success) {
//			response.getWriter().println(" User registered successfully");
//		} else {
//			response.getWriter().println("Failed to register user");
//		}
//	}
//	
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	    response.setContentType("text/html");
//
//	    List<User> users = userService.getAllUsers();
//	    
//
//	    PrintWriter out = response.getWriter();
//	    out.println("<html><body>");
//	    out.println("<h2>User List</h2>");
//	    out.println("<table border='1' cellpadding='10'>");
//	    out.println("<tr><th>Username</th><th>Password</th></tr>");
//
//	    for (User user : users) {
//	        out.println("<tr>");
//	        out.println("<td>" + user.getUserName() + "</td>");
//	        out.println("<td>" + user.getPassword() + "</td>");
//	        out.println("</tr>");
//	    }
//
//	    out.println("</table>");
//	    out.println("</body></html>");
//	}
//
//
//}
