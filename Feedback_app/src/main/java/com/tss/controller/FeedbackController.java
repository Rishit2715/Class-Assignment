package com.tss.controller;



import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.model.Feedback;
import com.tss.service.FeedbackService;

@WebServlet("/feedback")
public class FeedbackController extends HttpServlet {
    private final FeedbackService service = new FeedbackService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // show form or list depending on param
        String action = req.getParameter("action");
        if ("list".equals(action)) {
            try {
                List<Feedback> list = service.getAllFeedback();
                req.setAttribute("feedbackList", list);
                req.getRequestDispatcher("/listFeedback.jsp").forward(req, resp);
            } catch (Exception e) {
                req.setAttribute("error", e.getMessage());
                req.getRequestDispatcher("/error.jsp").forward(req, resp);
            }
            return;
        }

        // default: show form. Pre-fill name from cookie or session if available.
        HttpSession session = req.getSession();
        String savedName = (String) session.getAttribute("name");
        if (savedName == null) {
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if ("username".equals(c.getName())) {
                        savedName = c.getValue();
                        break;
                    }
                }
            }
        }
        req.setAttribute("savedName", savedName);
        req.getRequestDispatcher("/feedbackForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // read form params
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String sessionDateStr = req.getParameter("sessionDate");
        String comments = req.getParameter("comments");
        String sessionContent = req.getParameter("sessionContent");
        int queryResolution = parseIntSafe(req.getParameter("queryResolution"));
        int interactivity = parseIntSafe(req.getParameter("interactivity"));
        int impactfulLearning = parseIntSafe(req.getParameter("impactfulLearning"));
        int contentDeliverySkills = parseIntSafe(req.getParameter("contentDeliverySkills"));

        Feedback f = new Feedback();
        f.setName(name);
        if (sessionDateStr != null && !sessionDateStr.isBlank()) {
            f.setSessionDate(Date.valueOf(sessionDateStr)); // expects yyyy-MM-dd
        }
        f.setComments(comments);
        f.setSessionContent(sessionContent);
        f.setQueryResolution(queryResolution);
        f.setInteractivity(interactivity);
        f.setImpactfulLearning(impactfulLearning);
        f.setContentDeliverySkills(contentDeliverySkills);

        try {
            boolean ok = service.submitFeedback(f);
            if (ok) {
                // set session attribute and cookie
                HttpSession session = req.getSession();
                session.setAttribute("name", name);
                // cookie persists for 7 days
                Cookie cookie = new Cookie("username", name);
                cookie.setMaxAge(7 * 24 * 3600);
                cookie.setPath(req.getContextPath().isEmpty() ? "/" : req.getContextPath());
                resp.addCookie(cookie);

                req.setAttribute("message", "Feedback submitted successfully");
                req.getRequestDispatcher("/success.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", "Feedback not submitted");
                req.getRequestDispatcher("/error.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("error", "Error: " + e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    private int parseIntSafe(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return 0;
        }
    }
}
