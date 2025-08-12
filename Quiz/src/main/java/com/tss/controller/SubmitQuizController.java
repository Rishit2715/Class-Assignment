package com.tss.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.dao.QuestionDAO;
import com.tss.model.Question;

@WebServlet("/submitQuiz")
public class SubmitQuizController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        QuestionDAO dao = new QuestionDAO();
        List<Question> questions = dao.getAllQuestions();

        int score = 0;

        for (Question q : questions) {
            String selected = request.getParameter("q" + q.getId());
            if (selected != null && selected.equalsIgnoreCase(q.getCorrectOption())) {
                score++;
            }
        }

        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email"); // assuming user is logged in

        // Optional: Save score to DB (you can create ScoreDAO for it)

        request.setAttribute("score", score);
        request.setAttribute("total", questions.size());

        RequestDispatcher rd = request.getRequestDispatcher("score.jsp");
        rd.forward(request, response);
    }
}
