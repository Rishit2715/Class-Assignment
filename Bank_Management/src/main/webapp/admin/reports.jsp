<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.tss.model.Customer"%>
<%
    // Check if admin is logged in
    Object userObj = session.getAttribute("user");
    Object roleObj = session.getAttribute("role");
    if (userObj == null || !"admin".equals(roleObj)) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
    Customer admin = (Customer) userObj;
%>
<html>
<head>
    <title>Reports & Analytics</title>
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <!-- Chart.js -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

    <style>
        .sidebar {
            min-height: 100vh;
            background: #2c3e50;
            color: #ecf0f1;
            padding: 1rem;
            box-shadow: 3px 0 10px rgba(0,0,0,0.1);
        }
        .sidebar .nav-link {
            color: #bdc3c7;
            border-radius: 8px;
            padding: 0.7rem 1rem;
            transition: all 0.3s;
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
            margin-bottom: 1.5rem;
            overflow: hidden;
        }
        .card-title {
            font-size: 1.1rem;
            font-weight: 600;
            color: #2c3e50;
        }
        .chart-container {
            position: relative;
            height: 300px;
            width: 100%;
        }
        .stats-card {
            background: white;
            text-align: center;
            padding: 1rem;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
        }
        .stats-value {
            font-size: 1.6rem;
            font-weight: 700;
            color: #2c3e50;
        }
        .stats-label {
            font-size: 0.9rem;
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
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/dashboard">
                        <i class="bi bi-speedometer2"></i> Dashboard
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/manage_customers">
                        <i class="bi bi-people"></i> Manage Customers
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/manage_accounts">
                        <i class="bi bi-credit-card-2-front"></i> Manage Accounts
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/transactions">
                        <i class="bi bi-clock-history"></i> Transactions
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="${pageContext.request.contextPath}/admin/reports">
                        <i class="bi bi-file-earmark-bar-graph"></i> Reports
                    </a>
                </li>
                <li class="nav-item mt-auto pt-3">
                    <a class="nav-link text-danger" href="logout.jsp">Logout</a>
                </li>
            </ul>
        </div>

        <!-- Main Content -->
        <div class="col-md-10">
            <div class="main-content">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h2>Reports & Analytics</h2>
                    <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn btn-secondary btn-sm">← Back</a>
                </div>

                <!-- Stats Row -->
                <div class="row g-4 mb-4">
                    <div class="col-md-3">
                        <div class="stats-card">
                            <div class="stats-value" id="totalCustomers">0</div>
                            <div class="stats-label">Total Customers</div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="stats-card">
                            <div class="stats-value" id="totalDeposits">₹0</div>
                            <div class="stats-label">Total Deposits</div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="stats-card">
                            <div class="stats-value" id="approvedAccounts">0</div>
                            <div class="stats-label">Approved Accounts</div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="stats-card">
                            <div class="stats-value text-warning" id="pendingApprovals">0</div>
                            <div class="stats-label">Pending Approvals</div>
                        </div>
                    </div>
                </div>

                <!-- Charts Row -->
                <div class="row">
                    <!-- Chart 1: Deposit vs Withdrawal -->
                    <div class="col-md-6">
                        <div class="card">
                            <div class="card-header bg-white">
                                <h5 class="card-title"><i class="bi bi-currency-rupee"></i> Deposit vs Withdrawal</h5>
                            </div>
                            <div class="card-body">
                                <div class="chart-container">
                                    <canvas id="depositWithdrawChart"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Chart 2: Account Type Distribution -->
                    <div class="col-md-6">
                        <div class="card">
                            <div class="card-header bg-white">
                                <h5 class="card-title"><i class="bi bi-pie-chart"></i> Account Type Distribution</h5>
                            </div>
                            <div class="card-body">
                                <div class="chart-container">
                                    <canvas id="accountTypeChart"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Chart 3: Monthly Customer Growth -->
                    <div class="col-md-6">
                        <div class="card">
                            <div class="card-header bg-white">
                                <h5 class="card-title"><i class="bi bi-graph-up"></i> Monthly Customer Growth</h5>
                            </div>
                            <div class="card-body">
                                <div class="chart-container">
                                    <canvas id="growthChart"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Chart 4: Total Balance by Account Type -->
                    <div class="col-md-6">
                        <div class="card">
                            <div class="card-header bg-white">
                                <h5 class="card-title"><i class="bi bi-bar-chart"></i> Balance by Account Type</h5>
                            </div>
                            <div class="card-body">
                                <div class="chart-container">
                                    <canvas id="balanceTypeChart"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Data Table (Optional) -->
                <div class="card mt-4">
                    <div class="card-header bg-white">
                        <h5><i class="bi bi-table"></i> Summary Data</h5>
                    </div>
                    <div class="card-body">
                        <p class="text-muted">This dashboard visualizes real-time banking data.</p>
                        <small>Last updated: <%= new java.util.Date() %></small>
                    </div>
                </div>
            </div>
        </div>
    </div>

  <script>
    // Get real data from JSP
    const totalCustomers = <%= request.getAttribute("totalCustomers") %>;
    const totalDeposits = <%= request.getAttribute("totalDeposits") %>;
    const approvedAccounts = <%= request.getAttribute("approvedAccounts") %>;
    const pendingApprovals = <%= request.getAttribute("pendingApprovals") %>;

    // Update Stats
    document.getElementById('totalCustomers').textContent = totalCustomers;
    document.getElementById('totalDeposits').textContent = '₹' + totalDeposits.toLocaleString('en-IN');
    document.getElementById('approvedAccounts').textContent = approvedAccounts;
    document.getElementById('pendingApprovals').textContent = pendingApprovals;

    // Helper: Fill missing months
    function fillMonths(dataMap, defaultValue = 0) {
        const months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
        const result = [];
        months.forEach(m => result.push(dataMap[m] || defaultValue));
        return result;
    }

    // Chart 1: Deposit vs Withdrawal
    const monthlyDeposits = <%= request.getAttribute("monthlyDeposits") != null ? request.getAttribute("monthlyDeposits").toString().replaceAll("=", ":") : "{}" %>;
    const monthlyWithdrawals = <%= request.getAttribute("monthlyWithdrawals") != null ? request.getAttribute("monthlyWithdrawals").toString().replaceAll("=", ":") : "{}" %>;

    new Chart(document.getElementById('depositWithdrawChart'), {
        type: 'bar',
        data: {
            labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
            datasets: [
                {
                    label: 'Deposit',
                    data: fillMonths(monthlyDeposits),
                    backgroundColor: 'rgba(40, 167, 69, 0.7)'
                },
                {
                    label: 'Withdrawal',
                    data: fillMonths(monthlyWithdrawals),
                    backgroundColor: 'rgba(220, 53, 69, 0.7)'
                }
            ]
        },
        options: { responsive: true, scales: { y: { beginAtZero: true } } }
    });

    // Chart 2: Account Type Distribution
    const accountTypeCount = <%= request.getAttribute("accountTypeCount") != null ? request.getAttribute("accountTypeCount").toString().replaceAll("=", ":") : "{}" %>;
    const accountTypes = Object.keys(accountTypeCount);
    const accountCounts = Object.values(accountTypeCount);

    new Chart(document.getElementById('accountTypeChart'), {
        type: 'pie',
        data: {
            labels: accountTypes,
            datasets: [{ data: accountCounts, backgroundColor: ['#007bff', '#28a745', '#ffc107'] }]
        }
    });

    // Chart 3: Monthly Customer Growth
    const monthlyGrowth = <%= request.getAttribute("monthlyGrowth") != null ? request.getAttribute("monthlyGrowth").toString().replaceAll("=", ":") : "{}" %>;
    new Chart(document.getElementById('growthChart'), {
        type: 'line',
        data: {
            labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
            datasets: [{
                label: 'New Customers',
                data: fillMonths(monthlyGrowth, 0),
                borderColor: '#007bff',
                fill: false
            }]
        }
    });

    // Chart 4: Balance by Account Type
    const balanceByType = <%= request.getAttribute("balanceByType") != null ? request.getAttribute("balanceByType").toString().replaceAll("=", ":") : "{}" %>;
    const balanceTypes = Object.keys(balanceByType);
    const balanceValues = Object.values(balanceByType);

    new Chart(document.getElementById('balanceTypeChart'), {
        type: 'doughnut',
        data: {
            labels: balanceTypes,
            datasets: [{ data: balanceValues, backgroundColor: ['#ffc107', '#17a2b8', '#6f42c1'] }]
        }
    });
</script>
</body>
</html>