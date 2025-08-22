<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.tss.model.Customer" %>
<%@ page import="java.util.List" %>
<%
    // Check if admin is logged in
    Object userObj = session.getAttribute("user");
    Object roleObj = session.getAttribute("role");
    
    if (userObj == null || !"admin".equals(roleObj)) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
    
    Customer admin = (Customer) userObj;

    // Get real data from request attributes (set by servlet)
    Integer totalCustomers = (Integer) request.getAttribute("totalCustomers");
    Integer pendingAccounts = (Integer) request.getAttribute("pendingAccounts");
    Integer approvedAccounts = (Integer) request.getAttribute("approvedAccounts");
    Double totalDeposits = (Double) request.getAttribute("totalDeposits");
    Integer activeFDs = (Integer) request.getAttribute("activeFDs");

    // Default to 0 if null
    totalCustomers = (totalCustomers != null) ? totalCustomers : 0;
    pendingAccounts = (pendingAccounts != null) ? pendingAccounts : 0;
    approvedAccounts = (approvedAccounts != null) ? approvedAccounts : 0;
    totalDeposits = (totalDeposits != null) ? totalDeposits : 0.0;
    activeFDs = (activeFDs != null) ? activeFDs : 0;
%>
<html>
<head>
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            background-color: #f5f7fa;
            font-family: 'Segoe UI', sans-serif;
            color: #333;
        }

        /* Sidebar - Dark Elegant Grey */
        .sidebar {
            min-height: 100vh;
            background: #2c3e50;
            color: #ecf0f1;
            padding: 1rem;
            box-shadow: 3px 0 10px rgba(0, 0, 0, 0.1);
        }

        .sidebar h5 {
            color: #ffffff;
            font-weight: 600;
            letter-spacing: 0.5px;
        }

        .sidebar .nav-link {
            color: #bdc3c7;
            border-radius: 8px;
            margin-bottom: 0.4rem;
            padding: 0.7rem 1rem;
            font-size: 0.95rem;
            transition: all 0.3s ease;
        }

        .sidebar .nav-link:hover {
            background: rgba(255, 255, 255, 0.1);
            color: #ffffff;
        }

        .sidebar .nav-link.active {
            background: #34495e;
            color: #ffffff;
            font-weight: 500;
        }

        .sidebar .nav-link i {
            margin-right: 8px;
            width: 20px;
            text-align: center;
        }

        /* Main Content */
        .main-content {
            padding: 2rem;
        }

        /* Card Styling */
        .card {
            border: none;
            border-radius: 12px;
            box-shadow: 0 4px 16px rgba(0, 0, 0, 0.05);
            transition: transform 0.3s, box-shadow 0.3s;
        }

        .card:hover {
            transform: translateY(-4px);
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
        }

        .card-header {
            background: #f8f9fa;
            border-bottom: 1px solid #e9ecef;
            font-weight: 600;
            color: #2c3e50;
            border-radius: 12px 12px 0 0 !important;
        }

        /* Stat Cards */
        .stat-card {
            background: white;
            text-align: center;
            padding: 1.2rem 1rem;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
        }

        .stat-value {
            font-size: 1.6rem;
            font-weight: 700;
            color: #2c3e50;
        }

        .stat-label {
            font-size: 0.9rem;
            color: #7f8c8d;
        }

        /* Table */
        .table th {
            background-color: #f1f3f5;
            color: #2c3e50;
            font-weight: 600;
        }

        /* Buttons */
        .btn-outline-primary {
            border-color: #3498db;
            color: #3498db;
        }

        .btn-outline-primary:hover {
            background: #3498db;
            color: white;
            border-color: #3498db;
        }

        /* Welcome Section */
        .welcome-box {
            background: white;
            border-radius: 12px;
            padding: 1.5rem;
            box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
            margin-bottom: 1.8rem;
        }

        .welcome-box h2 {
            color: #2c3e50;
            font-weight: 600;
            margin-bottom: 0.3rem;
        }

        .welcome-box small {
            color: #7f8c8d;
        }
    </style>
</head>
<body>
    <div class="d-flex">
        <!-- Sidebar -->
        <div class="sidebar col-md-2 px-3">
            <h5 class="text-center mb-4"><i class="bi bi-building"></i> Admin Panel</h5>
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a class="nav-link active" href="dashboard.jsp">
                        <i class="bi bi-speedometer2"></i> Dashboard
                    </a>
                </li>
               <a class="nav-link active" 
   href="${pageContext.request.contextPath}/admin/manage_customers">
    <i class="bi bi-people"></i> Manage Customers
</a>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/manage_accounts">
                        <i class="bi bi-credit-card-2-front"></i> Manage Accounts
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="fd_schemes.jsp">
                        <i class="bi bi-graph-up-arrow"></i> FD Schemes
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/reports">
                        <i class="bi bi-file-earmark-bar-graph"></i> Reports
                    </a>
                </li>
                <li class="nav-item">
    <a class="nav-link" href="${pageContext.request.contextPath}/admin/transactions">
        <i class="bi bi-clock-history"></i> Transactions
    </a>
</li>
                <li class="nav-item mt-auto pt-3">
                    <a class="nav-link text-danger" href="logout.jsp">
                        <i class="bi bi-box-arrow-right"></i> Logout
                    </a>
                </li>
            </ul>
        </div>

        <!-- Main Content -->
        <div class="col-md-10">
            <div class="main-content">
                <!-- Welcome Section -->
                <div class="welcome-box">
                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <h2>Welcome, <%= admin.getName() %></h2>
                            <small>Email: <%= admin.getEmail() %> | Role: Administrator</small>
                        </div>
                    </div>
                </div>

                <!-- Stats Row -->
                <div class="row g-4 mb-4">
                    <div class="col-md-3">
                        <div class="stat-card">
                            <div class="stat-value"><%= totalCustomers %></div>
                            <div class="stat-label">Total Customers</div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="stat-card">
                            <div class="stat-value">₹<%= String.format("%.2f", totalDeposits) %></div>
                            <div class="stat-label">Total Deposits</div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="stat-card">
                            <div class="stat-value"><%= approvedAccounts %></div>
                            <div class="stat-label">Approved Accounts</div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="stat-card">
                            <div class="stat-value text-warning"><%= pendingAccounts %></div>
                            <div class="stat-label">Pending Approvals</div>
                        </div>
                    </div>
                </div>

                <!-- Recent Activity -->
                <div class="card">
                    <div class="card-header">
                        <h5><i class="bi bi-clock-history"></i> Recent Activity</h5>
                    </div>
                    <div class="card-body">
                        <p class="text-muted">Your dashboard is up to date.</p>
                        <ul class="list-group">
                            <li class="list-group-item">New customer registered: <strong>Rahul Sharma</strong></li>
                            <li class="list-group-item">Account pending approval: <strong>ACC-12345</strong></li>
                            <li class="list-group-item">FD opened: ₹50,000 for 3 years</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>