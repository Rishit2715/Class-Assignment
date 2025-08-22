<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.tss.model.Customer"%>
<%@ page import="java.util.List"%>
<%
    Object userObj = session.getAttribute("user");
    Object roleObj = session.getAttribute("role");
    if (userObj == null || !"admin".equals(roleObj)) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }

    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
    if (customers == null) customers = new java.util.ArrayList<>();
%>
<html>
<head>
    <title>Manage Customers</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <!-- DataTables CSS -->
    <link href="https://cdn.datatables.net/1.13.6/css/dataTables.bootstrap5.min.css" rel="stylesheet">

    <style>
        .sidebar { 
            background: #2c3e50; 
            color: #ecf0f1; 
            padding: 1rem; 
            min-height: 100vh;
        }
        .sidebar .nav-link { 
            color: #bdc3c7; 
            border-radius: 8px; 
            padding: 0.7rem 1rem; 
        }
        .sidebar .nav-link:hover, 
        .sidebar .nav-link.active { 
            background: #34495e; 
            color: white; 
        }
        .main-content { 
            padding: 2rem; 
        }
        .btn-action { 
            font-size: 0.85rem; 
            padding: 0.25rem 0.5rem; 
        }
        .status-approved {
            background: #d4edda;
            color: #155724;
            padding: 0.25em 0.6em;
            border-radius: 4px;
            font-size: 0.9em;
            font-weight: 500;
        }
        .status-inactive {
            background: #fff3cd;
            color: #856404;
            padding: 0.25em 0.6em;
            border-radius: 4px;
            font-size: 0.9em;
            font-weight: 500;
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
                    <a class="nav-link active" href="${pageContext.request.contextPath}/admin/manage_customers">
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
                <li class="nav-item mt-auto pt-3">
                    <a class="nav-link text-danger" href="logout.jsp">Logout</a>
                </li>
            </ul>
        </div>

        <!-- Main Content -->
        <div class="col-md-10">
            <div class="main-content">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h2>Manage Customers</h2>
                    <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn btn-secondary btn-sm">← Back</a>
                </div>

                <% if (request.getAttribute("message") != null) { %>
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <%= request.getAttribute("message") %>
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                <% } %>

                <div class="card">
                    <div class="card-header bg-white">
                        <h5><i class="bi bi-people"></i> Customer List</h5>
                    </div>
                    <div class="card-body">
                        <% if (customers.isEmpty()) { %>
                            <p class="text-muted">No customers found.</p>
                        <% } else { %>
                            <div class="table-responsive">
                                <table id="customersTable" class="table table-hover table-striped" style="width:100%">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Username</th>
                                            <th>Email</th>
                                            <th>Phone</th>
                                            <th>Address</th>
                                            <th>Status</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for (Customer c : customers) { %>
                                        <tr>
                                            <td><%= c.getId() %></td>
                                            <td><%= c.getName() %></td>
                                            <td><%= c.getUsername() %></td>
                                            <td><%= c.getEmail() %></td>
                                            <td><%= c.getPhone() != null ? c.getPhone() : "N/A" %></td>
                                            <td><%= c.getAddress() != null ? c.getAddress() : "N/A" %></td>
                                            <td>
                                                <% if ("active".equals(c.getStatus())) { %>
                                                    <span class="status-approved">Approved</span>
                                                <% } else if ("inactive".equals(c.getStatus())) { %>
                                                    <span class="status-inactive">Pending</span>
                                                <% } else { %>
                                                    <span class="badge bg-secondary"><%= c.getStatus() %></span>
                                                <% } %>
                                            </td>
                                            <td>
                                                <% if (!"active".equals(c.getStatus())) { %>
                                                    <form action="${pageContext.request.contextPath}/admin/approveCustomer" method="post" style="display:inline;">
                                                        <input type="hidden" name="customerId" value="<%= c.getId() %>">
                                                        <button type="submit" class="btn btn-success btn-action" 
                                                                onclick="return confirm('Approve this customer?')">
                                                            <i class="bi bi-check2"></i> Approve
                                                        </button>
                                                    </form>
                                                <% } else { %>
                                                    <small class="text-muted">Approved</small>
                                                <% } %>

                                                <form action="${pageContext.request.contextPath}/admin/deleteCustomer" method="post" style="display:inline;">
                                                    <input type="hidden" name="customerId" value="<%= c.getId() %>">
                                                    <button type="submit" class="btn btn-danger btn-action" 
                                                            onclick="return confirm('Delete this customer? This will delete all their accounts and data!')">
                                                        <i class="bi bi-trash"></i> Delete
                                                    </button>
                                                </form>
                                            </td>
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
            $('#customersTable').DataTable({
                "pageLength": 10,
                "ordering": true,
                "searching": true,
                "info": true,
                "autoWidth": false,
                "responsive": true,
                "language": {
                    "search": "Search:",
                    "lengthMenu": "Show _MENU_ entries per page",
                    "info": "Showing _START_ to _END_ of _TOTAL_ customers"
                }
            });
        });
    </script>
</body>
</html>