<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String message = (String) request.getAttribute("message");
    String name = (String) session.getAttribute("name");
    if (name == null) {
        // check cookie
        javax.servlet.http.Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (javax.servlet.http.Cookie c : cookies) {
                if ("username".equals(c.getName())) {
                    name = c.getValue();
                    break;
                }
            }
        }
    }
%>
<!DOCTYPE html>
<html>
<head><meta charset="utf-8"><title>Success</title></head>
<body>
    <p><strong><%= message != null ? message : "Success" %></strong></p>
    <p>You Feedback <em>submitted successfully</em></p>

    <p>Welcome back, <strong><%= name == null ? "Guest" : name %></strong></p>

    <p><a href="<%= request.getContextPath() %>/feedback">Submit another</a> |
       <a href="<%= request.getContextPath() %>/feedback?action=list">View all feedback</a>
    </p>
</body>
</html>
