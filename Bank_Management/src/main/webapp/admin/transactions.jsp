<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.tss.model.Transaction"%>
<%@ page import="java.util.List"%>
<%
    // Check if admin is logged in
    Object userObj = session.getAttribute("user");
    Object roleObj = session.getAttribute("role");
    if (userObj == null || !"admin".equals(roleObj)) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }

    List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
    if (transactions == null) transactions = new java.util.ArrayList<>();
%>
<html>
<head>
    <title>Transaction Records</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <!-- DataTables CSS -->
    <link href="https://cdn.datatables.net/1.13.6/css/dataTables.bootstrap5.min.css" rel="stylesheet">

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
        .badge-credit {
            background: #d4edda;
            color: #155724;
        }
        .badge-debit {
            background: #f8d7da;
            color: #721c24;
        }
        .badge-transfer {
            background: #d1d1d1;
            color: #444;
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
                    <a class="nav-link" href="manage_accounts.jsp">
                        <i class="bi bi-credit-card-2-front"></i> Manage Accounts
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="${pageContext.request.contextPath}/admin/transactions">
                        <i class="bi bi-clock-history"></i> Transactions
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="fd_schemes.jsp">
                        <i class="bi bi-graph-up-arrow"></i> FD Schemes
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="reports.jsp">
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
                    <h2>Transaction Records</h2>
                    <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn btn-secondary btn-sm">
                        ← Back to Dashboard
                    </a>
                </div>

                <% if (request.getAttribute("error") != null) { %>
                    <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
                <% } %>

                <div class="card">
                    <div class="card-header bg-white">
                        <h5><i class="bi bi-clock-history"></i> All Transactions</h5>
                    </div>
                    <div class="card-body">
                        <% if (transactions.isEmpty()) { %>
                            <p class="text-muted">No transactions found.</p>
                        <% } else { %>
                            <div class="table-responsive">
                                <table id="transactionsTable" class="table table-hover table-striped" style="width:100%">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Customer</th>
                                            <th>Account</th>
                                            <th>Type</th>
                                            <th>Amount (₹)</th>
                                            <th>Balance After</th>
                                            <th>Description</th>
                                            <th>Date & Time</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for (Transaction tx : transactions) { %>
                                        <tr>
                                            <td><%= tx.getId() %></td>
                                            <td><%= tx.getCustomerName() %></td>
                                            <td><%= tx.getAccNo() %></td>
                                            <td>
                                                <% if ("deposit".equals(tx.getType()) || "transfer_in".equals(tx.getType())) { %>
                                                    <span class="badge bg-success badge-credit">Credit</span>
                                                <% } else if ("withdraw".equals(tx.getType()) || "transfer_out".equals(tx.getType())) { %>
                                                    <span class="badge bg-danger badge-debit">Debit</span>
                                                <% } else { %>
                                                    <span class="badge bg-secondary badge-transfer"><%= tx.getType() %></span>
                                                <% } %>
                                            </td>
                                            <td>₹<%= String.format("%.2f", tx.getAmount()) %></td>
                                            <td>₹<%= String.format("%.2f", tx.getBalanceAfter()) %></td>
                                            <td><%= tx.getDescription() %></td>
                                            <td><%= tx.getTransactionDate() %></td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>
                            </div>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Scripts -->
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.6/js/dataTables.bootstrap5.min.js"></script>

    <script>
        $(document).ready(function () {
            $('#transactionsTable').DataTable({
                "pageLength": 10,
                "ordering": true,
                "searching": true,
                "info": true,
                "autoWidth": false,
                "responsive": true,
                "language": {
                    "search": "Search:",
                    "lengthMenu": "Show _MENU_ entries per page",
                    "info": "Showing _START_ to _END_ of _TOTAL_ transactions"
                }
            });
        });
    </script>
</body>
</html>