<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Registration</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
    body {
        background: linear-gradient(135deg, #74ebd5, #9face6);
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }
    .card {
        border-radius: 15px;
        padding: 20px;
        background: white;
    }
</style>
</head>
<body class="d-flex justify-content-center align-items-center vh-100">

<div class="card shadow-lg" style="width: 400px;">
    <h2 class="text-center mb-4">Employee Registration</h2>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>

    <form action="RegisterController" method="post">
        <div class="mb-3">
            <label class="form-label">Username</label>
            <input type="text" name="username" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Password</label>
            <input type="password" name="password" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Full Name</label>
            <input type="text" name="fullName" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-success w-100">Register</button>
    </form>

    <div class="text-center mt-3">
        <a href="login.jsp" class="text-decoration-none">Already have an account? Login here</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
