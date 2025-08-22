<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.tss.model.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tss.model.Beneficiary" %>
<%@ page import="com.tss.model.Account" %>

<html>
<head>
    <title>Manage Beneficiaries</title>
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
        .table th {
            background-color: #f1f3f5;
            font-weight: 600;
        }
        .badge {
            font-weight: 500;
            padding: 0.5em 0.8em;
        }
    </style>
</head>
<body>
    <div class="d-flex">
        <%@ include file="sidebar.jsp" %>

        <div class="col-md-10">
            <div class="main-content p-4">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h2>👥 Manage Beneficiaries</h2>
                    <div>
                        <a href="${pageContext.request.contextPath}/customer/add_beneficiary.jsp" class="btn btn-primary btn-sm me-2">
                            <i class="bi bi-person-plus"></i> Add New
                        </a>
                        <a href="${pageContext.request.contextPath}/customer/dashboard" class="btn btn-secondary btn-sm">← Back to Dashboard</a>
                    </div>
                </div>

                <!-- Success/Error Messages -->
                <% if (request.getAttribute("error") != null) { %>
                    <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
                <% } %>
                <% if (request.getAttribute("success") != null) { %>
                    <div class="alert alert-success"><%= request.getAttribute("success") %></div>
                <% } %>

                <!-- Beneficiaries List -->
                <div class="card">
                    <div class="card-header">
                        <h5><i class="bi bi-people"></i> Your Beneficiaries</h5>
                    </div>
                    <div class="card-body">
                        <% 
                        // This would normally come from a servlet
                        List<Beneficiary> beneficiaries = (List<Beneficiary>) request.getAttribute("beneficiaries");
                        if (beneficiaries == null) beneficiaries = new java.util.ArrayList<>();
                        %>
                        
                        <% if (beneficiaries.isEmpty()) { %>
                            <div class="text-center py-5">
                                <i class="bi bi-people text-muted" style="font-size: 3rem;"></i>
                                <h5 class="text-muted mt-3">No Beneficiaries Yet</h5>
                                <p class="text-muted">You haven't added any beneficiaries yet.</p>
                                <a href="${pageContext.request.contextPath}/customer/add_beneficiary.jsp" class="btn btn-primary">
                                    <i class="bi bi-person-plus"></i> Add Your First Beneficiary
                                </a>
                            </div>
                        <% } else { %>
                            <div class="table-responsive">
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>Nickname</th>
                                            <th>Account Number</th>
                                            <th>Added Date</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for (Beneficiary b : beneficiaries) { %>
                                        <tr>
                                            <td>
                                                <strong><%= b.getNickname() %></strong>
                                            </td>
                                            <td>
                                                <span class="text-muted">
                                                    ****<%= b.getBeneficiaryAccNo().substring(b.getBeneficiaryAccNo().length() - 4) %>
                                                </span>
                                            </td>
                                            <td>
                                                <small class="text-muted">
                                                    <%= b.getAddedDate() != null ? b.getAddedDate() : "N/A" %>
                                                </small>
                                            </td>
                                            <td>
                                                <button class="btn btn-sm btn-outline-primary" 
                                                        onclick="transferToBeneficiary('<%= b.getBeneficiaryAccNo() %>', '<%= b.getNickname() %>')">
                                                    <i class="bi bi-arrow-left-right"></i> Transfer
                                                </button>
                                                <button class="btn btn-sm btn-outline-danger" 
                                                        onclick="removeBeneficiary('<%= b.getBeneficiaryAccNo() %>', '<%= b.getNickname() %>')">
                                                    <i class="bi bi-trash"></i> Remove
                                                </button>
                                            </td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>
                            </div>
                        <% } %>
                    </div>
                </div>

                <!-- Quick Transfer Section -->
                <div class="card mt-4">
                    <div class="card-header">
                        <h5><i class="bi bi-lightning"></i> Quick Actions</h5>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-6">
                                <a href="${pageContext.request.contextPath}/customer/transfer" class="btn btn-success w-100 mb-2">
                                    <i class="bi bi-arrow-left-right"></i> Make a Transfer
                                </a>
                            </div>
                            <div class="col-md-6">
                                <a href="${pageContext.request.contextPath}/customer/add_beneficiary.jsp" class="btn btn-primary w-100 mb-2">
                                    <i class="bi bi-person-plus"></i> Add New Beneficiary
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function transferToBeneficiary(accNo, nickname) {
            if (confirm("Transfer money to " + nickname + "?")) {
                // Redirect to transfer page with beneficiary pre-selected
                window.location.href = window.location.origin + window.location.pathname.replace('/manage_beneficiaries.jsp', '/transfer?beneficiary=' + accNo);
            }
        }

        function removeBeneficiary(accNo, nickname) {
            if (confirm("Are you sure you want to remove " + nickname + " from your beneficiaries?")) {
                // This would normally submit a form to remove the beneficiary
                alert('Remove functionality would be implemented here');
            }
        }
    </script>
</body>
</html>
