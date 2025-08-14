<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Login</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background: linear-gradient(135deg, #667eea, #764ba2);
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
		<h2 class="text-center mb-4">Login</h2>

		<c:if test="${not empty error}">
			<div class="alert alert-danger">${error}</div>
		</c:if>
		<c:if test="${param.success != null}">
			<div class="alert alert-success">${param.success}</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/auth" method="post">
			<div class="mb-3">
				<label class="form-label">Username</label> <input name="username"
					class="form-control" required />
			</div>
			<div class="mb-3">
				<label class="form-label">Password</label> <input name="password"
					type="password" class="form-control" required />
			</div>
			<button type="submit" class="btn btn-primary w-100">Login</button>
		</form>

		<div class="text-center mt-3">
			<a href="registration.jsp" class="text-decoration-none">Don't
				have an account? Register here</a>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
