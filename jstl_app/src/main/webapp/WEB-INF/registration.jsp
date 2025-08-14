<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Registration</title>
</head>
<body>
<h2>Employee Registration</h2>

<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>
<c:if test="${not empty success}">
    <p style="color:green;">${success}</p>
</c:if>

<form action="RegisterController" method="post">
    Username: <input type="text" name="username" required><br>
    Password: <input type="password" name="password" required><br>
    Full Name: <input type="text" name="fullName" required><br>
    Department: <input type="text" name="dept" required><br>
    <button type="submit">Register</button>
</form>

</body>
</html>
