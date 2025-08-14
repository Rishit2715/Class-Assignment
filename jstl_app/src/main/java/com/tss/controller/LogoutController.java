package com.tss.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    protected void doGet(HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws IOException {
        HttpSession s = req.getSession(false);
        if (s != null) s.invalidate();
        // remove cookie
        Cookie cookie = new Cookie("uname", "");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
        resp.sendRedirect(req.getContextPath()+"/auth");
    }
}
