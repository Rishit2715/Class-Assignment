
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.tss.model.Customer"%>
<%@ page import="com.tss.model.Account"%>
<%@ page import="java.util.List"%>
<%
    // Check if user is logged in
    Object userObj = session.getAttribute("user");
    if (userObj == null) {
        response.sendRedirect("../login.jsp");
        return;
    }

    // Get accounts from request (set by CustomerDashboardServlet)
    List<Account> accounts = (List<Account>) request.getAttribute("accounts");
    if (accounts == null) accounts = new java.util.ArrayList<>();

    // Calculate totals
    double totalBalance = accounts.stream().mapToDouble(Account::getBalance).sum();
    int accountCount = accounts.size();
%>
<html>
<head>
    <title>Customer Dashboard</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <style>
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

        /* Account Cards */
        .account-item {
            background: #f8f9fa;
            border: 1px solid #dee2e6;
            border-radius: 10px;
            padding: 0.8rem;
            margin-bottom: 0.5rem;
        }
        .account-type {
            font-weight: 600;
            color: #2c3e50;
        }
        .account-details {
            font-size: 0.9rem;
            color: #555;
        }

        /* Table */
        .table th {
            background-color: #f1f3f5;
            color: #2c3e50;
            font-weight: 600;
        }
        .badge {
            font-weight: 500;
            padding: 0.5em 0.8em;
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
        <%@ include file="sidebar.jsp" %>

        <div class="col-md-10">
            <div class="main-content p-4">
                <!-- Welcome Section -->
                <div class="welcome-box">
                    <h2>Welcome back, <%= customer.getName() %>! 👋</h2>
                    <small>Here's your banking overview</small>
                </div>

                <!-- Statistics Cards -->
                <div class="row mb-4">
                    <div class="col-md-4">
                        <div class="stat-card">
                            <div class="stat-value">₹<%= String.format("%.2f", totalBalance) %></div>
                            <div class="stat-label">Total Balance</div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="stat-card">
                            <div class="stat-value"><%= accountCount %></div>
                            <div class="stat-label">Total Accounts</div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="stat-card">
                            <div class="stat-value"><%= accounts.stream().filter(acc -> "approved".equals(acc.getStatus())).count() %></div>
                            <div class="stat-label">Active Accounts</div>
                        </div>
                    </div>
                </div>

                <!-- All Accounts -->
                <div class="card mb-4">
                    <div class="card-header">
                        <h5><i class="bi bi-credit-card-2-front"></i> Your Accounts</h5>
                    </div>
                    <div class="card-body">
                        <% if (accounts.isEmpty()) { %>
                            <p class="text-muted">You have no approved accounts yet.</p>
                        <% } else { %>
                            <% for (Account acc : accounts) {
                                String masked = "****" + acc.getAccNo().substring(acc.getAccNo().length() - 4);
                            %>
                            <div class="account-item">
                                <div class="row align-items-center">
                                    <div class="col-md-8">
                                        <div class="account-type"><%= acc.getAccountType() %> Account</div>
                                        <div class="account-details">
                                            <strong>Account:</strong> <%= masked %> |
                                            <strong>Balance:</strong> ₹<%= String.format("%.2f", acc.getBalance()) %>
                                        </div>
                                    </div>
                                    <div class="col-md-4 text-end">
                                        <a href="${pageContext.request.contextPath}/customer/transfer?from=<%= acc.getAccNo() %>"
                                           class="btn btn-sm btn-outline-primary">
                                            <i class="bi bi-arrow-left-right"></i> Transfer
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <% } %>
                        <% } %>
                    </div>
                </div>

                <!-- Recent Transactions -->
                <div class="card">
                    <div class="card-header">
                        <h5><i class="bi bi-clock-history"></i> Recent Transactions</h5>
                    </div>
                    <div class="card-body">
                        <p class="text-muted mb-3">Your last 5 transactions</p>
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>Date</th>
                                        <th>Type</th>
                                        <th>Amount</th>
                                        <th>Balance</th>
                                        <th>Description</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>2025-08-20</td>
                                        <td><span class="badge bg-success">Credit</span></td>
                                        <td>₹2,000.00</td>
                                        <td>₹52,000.00</td>
                                        <td>Salary</td>
                                    </tr>
                                    <tr>
                                        <td>2025-08-19</td>
                                        <td><span class="badge bg-danger">Debit</span></td>
                                        <td>₹500.00</td>
                                        <td>₹50,000.00</td>
                                        <td>ATM Withdrawal</td>
                                    </tr>
                                    <tr>
                                        <td>2025-08-18</td>
                                        <td><span class="badge bg-success">Credit</span></td>
                                        <td>₹1,000.00</td>
                                        <td>₹50,500.00</td>
                                        <td>Received from 1234</td>
                                    </tr>
                                    <tr>
                                        <td>2025-08-17</td>
                                        <td><span class="badge bg-primary">Transfer</span></td>
                                        <td>₹2,500.00</td>
                                        <td>₹49,500.00</td>
                                        <td>Transfer to Mom</td>
                                    </tr>
                                    <tr>
                                        <td>2025-08-16</td>
                                        <td><span class="badge bg-success">Deposit</span></td>
                                        <td>₹10,000.00</td>
                                        <td>₹52,000.00</td>
                                        <td>Cash Deposit</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <a href="#" class="btn btn-outline-secondary btn-sm">View Full Statement</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Deposit Modal -->
    <div class="modal fade" id="depositModal" tabindex="-1" aria-labelledby="depositModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="${pageContext.request.contextPath}/customer/deposit" method="post">
                    <div class="modal-header">
                        <h5 class="modal-title" id="depositModalLabel"><i class="bi bi-cash"></i> Deposit Money</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Show success/error if set -->
                        <% if (request.getAttribute("error") != null) { %>
                            <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
                        <% } %>
                        <% if (request.getAttribute("success") != null) { %>
                            <div class="alert alert-success"><%= request.getAttribute("success") %></div>
                        <% } %>

                        <div class="mb-3">
                            <label for="accNo">Deposit To</label>
                            <select name="accNo" class="form-select" required>
                                <option value="">-- Select Account --</option>
                                <% for (Account acc : accounts) {
                                    String masked = "****" + acc.getAccNo().substring(acc.getAccNo().length() - 4);
                                %>
                                <option value="<%= acc.getAccNo() %>">
                                    <%= acc.getAccountType() %> • <%= masked %> • ₹<%= String.format("%.2f", acc.getBalance()) %>
                                </option>
                                <% } %>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="amount">Amount (₹)</label>
                            <input type="number" name="amount" class="form-control" min="0.01" step="0.01" required>
                        </div>

                        <div class="mb-3">
                            <label for="remarks">Remarks</label>
                            <input type="text" name="remarks" class="form-control" maxlength="100">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-success">Deposit Now</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Withdraw Modal -->
    <div class="modal fade" id="withdrawModal" tabindex="-1" aria-labelledby="withdrawModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="${pageContext.request.contextPath}/customer/withdraw" method="post">
                    <div class="modal-header">
                        <h5 class="modal-title" id="withdrawModalLabel"><i class="bi bi-wallet"></i> Withdraw Money</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="accNo">Withdraw From</label>
                            <select name="accNo" class="form-select" required>
                                <option value="">-- Select Account --</option>
                                <% for (Account acc : accounts) {
                                    String masked = "****" + acc.getAccNo().substring(acc.getAccNo().length() - 4);
                                %>
                                <option value="<%= acc.getAccNo() %>">
                                    <%= acc.getAccountType() %> • <%= masked %> • ₹<%= String.format("%.2f", acc.getBalance()) %>
                                </option>
                                <% } %>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="amount">Amount (₹)</label>
                            <input type="number" name="amount" class="form-control" min="0.01" step="0.01" required>
                        </div>

                        <div class="mb-3">
                            <label for="remarks">Remarks</label>
                            <input type="text" name="remarks" class="form-control" maxlength="100">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-danger">Withdraw Now</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>