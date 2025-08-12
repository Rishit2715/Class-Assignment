package com.tss.controller;

import com.tss.model.Score;
import com.tss.service.ScoreService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ScoreController")
public class ScoreController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ScoreService scoreService = new ScoreService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null || session.getAttribute("username") == null) {
            out.println("<h2>❌ Missing session data. Cannot save score.</h2>");
            out.println("<a href='index.html'>Back to Home</a>");
            return;
        }

        try {
            int score = Integer.parseInt(request.getParameter("score"));
            int userId = (int) session.getAttribute("userId");
            String username = (String) session.getAttribute("username");

            Score userScore = new Score();
            userScore.setUserId(userId);
            userScore.setUsername(username);
            userScore.setScore(score);

            boolean success = scoreService.storeScore(userScore);

            if (success) {
                out.println("<h2>✅ Score submitted successfully!</h2>");
            } else {
                out.println("<h2>❌ Failed to save score.</h2>");
            }
        } catch (Exception e) {
            out.println("<h2>❌ Error: Invalid input or server issue.</h2>");
        }

        out.println("<a href='index.html'>Back to Home</a>");
    }

}
