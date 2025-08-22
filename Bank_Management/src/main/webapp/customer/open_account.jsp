<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.tss.model.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tss.model.Account" %>

<html>
<head>
    <title>Open New Account</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            background-color: #f5f7fa;
            font-family: 'Segoe UI', sans-serif;
            color: #333;
        }
        .sidebar {
            min-height: 100vh;
            background: #2c3e50;
            color: #ecf0f1;
            padding: 1rem;
            box-shadow: 3px 0 10px rgba(0,0,0,0.1);
        }
        .sidebar h5 {
            color: #ffffff;
            font-weight: 600;
        }
        .sidebar .nav-link {
            color: #bdc3c7;
            border-radius: 8px;
            padding: 0.7rem 1rem;
            font-size: 0.95rem;
        }
        .sidebar .nav-link:hover, .sidebar .nav-link.active {
            background: #34495e;
            color: white;
        }
        .main-content {
            padding: 2rem;
        }
        .card {
            border: none;
            border-radius: 12px;
            box-shadow: 0 4px 16px rgba(0,0,0,0.05);
        }
        .card-header {
            background: #f8f9fa;
            border-bottom: 1px solid #e9ecef;
            color: #2c3e50;
        }
    </style>
</head>
<body>
    <div class="d-flex">
        <%@ include file="sidebar.jsp" %>

        <div class="col-md-10">
            <div class="main-content p-4">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h2>🏦 Open New Account</h2>
                    <a href="${pageContext.request.contextPath}/customer/dashboard" class="btn btn-secondary btn-sm">← Back to Dashboard</a>
                </div>

                <!-- Success/Error Messages -->
                <% if (request.getAttribute("error") != null) { %>
                    <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
                <% } %>
                <% if (request.getAttribute("success") != null) { %>
                    <div class="alert alert-success"><%= request.getAttribute("success") %></div>
                <% } %>

                <!-- Account Type Selection Form -->
                <div class="card">
                    <div class="card-header">
                        <h5><i class="bi bi-plus-circle"></i> Select Account Type</h5>
                    </div>
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/customer/openAccount" method="post">
                            <div class="mb-3">
                                <label for="accountType" class="form-label">Account Type <span class="text-danger">*</span></label>
                                <select name="accountType" id="accountType" class="form-select" required>
                                    <option value="">-- Choose Type --</option>
                                    <option value="Savings">Savings Account</option>
                                    <option value="Current">Current Account</option>
                                </select>
                                <div class="form-text">Select the type of account you want to open</div>
                            </div>
                            <button type="submit" class="btn btn-primary">
                                <i class="bi bi-plus-circle"></i> Submit Request
                            </button>
                        </form>
                    </div>
                </div>

                <!-- Account Information -->
                <div class="card mt-4">
                    <div class="card-header">
                        <h5><i class="bi bi-info-circle"></i> Account Information</h5>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-6">
                                <h6>Savings Account</h6>
                                <ul class="list-unstyled">
                                    <li>✓ Interest earning</li>
                                    <li>✓ Minimum balance requirement</li>
                                    <li>✓ Suitable for personal use</li>
                                </ul>
                            </div>
                            <div class="col-md-6">
                                <h6>Current Account</h6>
                                <ul class="list-unstyled">
                                    <li>✓ No interest</li>
                                    <li>✓ Unlimited transactions</li>
                                    <li>✓ Suitable for business use</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>