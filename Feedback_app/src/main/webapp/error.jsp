<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <h3 style="color:red;">Oops! Something went wrong.</h3>
    <p><%= error %></p>
    <a href="<%= request.getContextPath() %>/feedback">Go back</a>
</body>
</html>
