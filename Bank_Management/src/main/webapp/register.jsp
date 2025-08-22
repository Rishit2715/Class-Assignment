<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register - Bank Management</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            background: #f0f9ff;
            font-family: 'Segoe UI', sans-serif;
            color: #333;
        }
        .register-container {
            max-width: 600px;
            margin: 60px auto;
            padding: 40px;
            background: white;
            border-radius: 12px;
            box-shadow: 0 6px 20px rgba(0,0,0,0.1);
        }
        .logo {
            text-align: center;
            margin-bottom: 30px;
        }
        .logo i {
            font-size: 3rem;
            color: #2c3e50;
        }
        .logo h1 {
            color: #2c3e50;
            font-weight: 600;
            margin: 10px 0 0;
            font-size: 1.5rem;
        }
        .form-label {
            font-weight: 500;
            color: #2c3e50;
        }
        .form-control, .form-select {
            border-radius: 8px;
            padding: 12px 16px;
        }
        .form-control:focus, .form-select:focus {
            border-color: #2c3e50;
            box-shadow: 0 0 0 0.25rem rgba(44, 62, 80, 0.25);
        }
        .btn-success {
            background-color: #2c3e50;
            border: none;
            padding: 12px;
            border-radius: 8px;
            font-weight: 600;
            transition: all 0.3s;
        }
        .btn-success:hover {
            background-color: #1a252f;
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        .links {
            text-align: center;
            margin-top: 20px;
            color: #6c757d;
        }
        .links a {
            color: #2c3e50;
            text-decoration: none;
            font-weight: 500;
        }
        .links a:hover {
            text-decoration: underline;
            color: #1a252f;
        }
        .alert {
            border-radius: 8px;
        }
        .required::after {
            content: " *";
            color: #e63946;
        }
    </style>
</head>
<body>
    <div class="register-container">
        <div class="logo">
            <i class="bi bi-person-plus"></i>
            <h1>Create Account</h1>
        </div>

        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger">
                <i class="bi bi-exclamation-circle"></i> <%= request.getAttribute("error") %>
            </div>
        <% } %>

        <form action="${pageContext.request.contextPath}/controller/register" method="post">
            <div class="row">
                <div class="col-md-6 mb-3">
                    <label class="form-label required">Username</label>
                    <input type="text" name="username" class="form-control" placeholder="Choose a username" required>
                </div>
                <div class="col-md-6 mb-3">
                    <label class="form-label required">Password</label>
                    <input type="password" name="password" class="form-control" placeholder="Create password" required>
                </div>
            </div>

            <div class="mb-3">
                <label class="form-label required">Full Name</label>
                <input type="text" name="name" class="form-control" placeholder="Enter your full name" required>
            </div>

            <div class="mb-3">
                <label class="form-label required">Email</label>
                <input type="email" name="email" class="form-control" placeholder="Enter your email" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Phone</label>
                <input type="tel" name="phone" class="form-control" placeholder="Enter phone number">
            </div>

            <div class="mb-4">
                <label class="form-label">Address</label>
                <textarea name="address" class="form-control" rows="3" placeholder="Enter your address"></textarea>
            </div>

            <button type="submit" class="btn btn-success w-100">
                <i class="bi bi-person-plus"></i> Create Account
            </button>
        </form>

        <div class="links mt-4">
            <p class="mb-2">Already have an account?</p>
            <a href="${pageContext.request.contextPath}/login.jsp">Sign in here</a>
        </div>
    </div>
</body>
</html>