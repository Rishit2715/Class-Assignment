<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.tss.model.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tss.model.Beneficiary" %>

<html>
<head>
    <title>Add Beneficiary</title>
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
                    <h2>👤 Add Beneficiary</h2>
                    <a href="${pageContext.request.contextPath}/customer/dashboard" class="btn btn-secondary btn-sm">← Back to Dashboard</a>
                </div>

                <!-- Success/Error Messages -->
                <% if (request.getAttribute("error") != null) { %>
                    <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
                <% } %>
                <% if (request.getAttribute("success") != null) { %>
                    <div class="alert alert-success"><%= request.getAttribute("success") %></div>
                <% } %>

                <!-- Beneficiary Form -->
                <div class="card">
                    <div class="card-header">
                        <h5><i class="bi bi-person-plus"></i> Add New Beneficiary</h5>
                    </div>
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/customer/addBeneficiary" method="post">
                            <div class="mb-3">
                                <label for="accNo" class="form-label">Beneficiary Account Number <span class="text-danger">*</span></label>
                                <input type="text" name="accNo" id="accNo" class="form-control" 
                                       placeholder="Enter 11-16 digit account number" 
                                       title="Must be 11 to 16 digits" 
                                       required>
                                <div class="form-text">Enter the account number of the person you want to add as beneficiary</div>
                            </div>

                            <div class="mb-3">
                                <label for="nickname" class="form-label">Nickname <span class="text-danger">*</span></label>
                                <input type="text" name="nickname" id="nickname" class="form-control"
                                       placeholder="Enter a nickname (e.g., Mom, Brother, Friend)" maxlength="50" required>
                                <div class="form-text">Give a friendly name to easily identify this beneficiary</div>
                            </div>

                            <button type="submit" class="btn btn-primary">
                                <i class="bi bi-person-plus"></i> Add Beneficiary
                            </button>
                        </form>
                    </div>
                </div>

                <!-- Beneficiary Information -->
                <div class="card mt-4">
                    <div class="card-header">
                        <h5><i class="bi bi-info-circle"></i> About Beneficiaries</h5>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-6">
                                <h6>What is a Beneficiary?</h6>
                                <p>A beneficiary is someone you can transfer money to quickly without entering their account number each time.</p>
                                <ul class="list-unstyled">
                                    <li>✓ Faster transfers</li>
                                    <li>✓ Reduced errors</li>
                                    <li>✓ Easy management</li>
                                </ul>
                            </div>
                            <div class="col-md-6">
                                <h6>Requirements</h6>
                                <ul class="list-unstyled">
                                    <li>✓ Valid account number</li>
                                    <li>✓ Account must be approved</li>
                                    <li>✓ Cannot be your own account</li>
                                    <li>✓ Unique nickname</li>
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