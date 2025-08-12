package com.tss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.tss.model.Question;
import com.tss.model.Score;
import com.tss.model.User;
import com.tss.service.QuestionService;
import com.tss.service.ScoreService;
@WebServlet("/QuizController")
public class QuizController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private QuestionService service = new QuestionService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        List<Question> questions = (List<Question>) session.getAttribute("quizQuestions");
        if (questions == null) {
            questions = service.fetchQuizQuestions();
            session.setAttribute("quizQuestions", questions);
            session.setAttribute("score", 0);
        }

        int currentIndex = 0;
        String indexParam = request.getParameter("q");
        if (indexParam != null) {
            try {
                currentIndex = Integer.parseInt(indexParam);
            } catch (NumberFormatException e) {
                currentIndex = 0;
            }
        }

        if (currentIndex >= ((List<Question>) session.getAttribute("quizQuestions")).size()) {
            // Quiz finished → redirect to ScoreController
        	int finalScore = (int) session.getAttribute("score");
        	response.sendRedirect("ScoreController?score=" + finalScore);            return;
        }

        Question q = ((List<Question>) session.getAttribute("quizQuestions")).get(currentIndex);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Quiz</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f2f2f2; margin: 0; padding: 0; }");
        out.println(".container { max-width: 600px; margin: 80px auto; background: #fff; padding: 30px 40px; box-shadow: 0 0 10px rgba(0,0,0,0.2); border-radius: 10px; }");
        out.println("h1 { text-align: center; color: #333; }");
        out.println("form { margin-top: 20px; }");
        out.println("p { font-size: 18px; font-weight: bold; }");
        out.println("label { display: block; margin: 12px 0; font-size: 16px; }");
        out.println("input[type='submit'] { background-color: #4CAF50; color: white; padding: 10px 20px; border: none; font-size: 16px; border-radius: 5px; cursor: pointer; }");
        out.println("input[type='submit']:hover { background-color: #45a049; }");
        out.println("</style>");
        out.println("</head><body>");
        out.println("<div class='container'>");
        out.println("<h1>Gujarat Quiz - Question " + (currentIndex + 1) + "</h1>");
        out.println("<form method='post' action='QuizController'>");
        out.println("<p>" + q.getQuestionText() + "</p>");
        out.println("<input type='hidden' name='qIndex' value='" + currentIndex + "'>");
        out.println("<label><input type='radio' name='answer' value='A' required> " + q.getOptionA() + "</label>");
        out.println("<label><input type='radio' name='answer' value='B'> " + q.getOptionB() + "</label>");
        out.println("<label><input type='radio' name='answer' value='C'> " + q.getOptionC() + "</label>");
        out.println("<label><input type='radio' name='answer' value='D'> " + q.getOptionD() + "</label>");
        out.println("<br><input type='submit' value='Next'>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body></html>");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("quizQuestions") == null) {
            response.sendRedirect("Login.html");
            return;
        }

        int currentIndex = Integer.parseInt(request.getParameter("qIndex"));
        String selectedAnswer = request.getParameter("answer");

        @SuppressWarnings("unchecked")
        List<Question> questions = (List<Question>) session.getAttribute("quizQuestions");
        Question currentQuestion = questions.get(currentIndex);

        if (selectedAnswer != null && selectedAnswer.equalsIgnoreCase(currentQuestion.getCorrectOption())) {
            int currentScore = (int) session.getAttribute("score");
            session.setAttribute("score", currentScore + 1);
        }

        // Go to next question
        response.sendRedirect("QuizController?q=" + (currentIndex + 1));
    }
}
