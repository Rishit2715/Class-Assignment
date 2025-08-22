<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.tss.model.Customer"%>
<%@ page import="com.tss.model.Beneficiary"%>
<%@ page import="com.tss.model.Account"%>
<%@ page import="com.tss.model.Transaction"%>
<%@ page import="java.util.List"%>
<%
    // Get data from request (set by TransferServlet)
    List<Account> approvedAccounts = (List<Account>) request.getAttribute("approvedAccounts");
    List<Beneficiary> beneficiaries = (List<Beneficiary>) request.getAttribute("beneficiaries");
    List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");

    // Initialize if null
    if (approvedAccounts == null) approvedAccounts = new java.util.ArrayList<>();
    if (beneficiaries == null) beneficiaries = new java.util.ArrayList<>();
    if (transactions == null) transactions = new java.util.ArrayList<>();

    // Debug
    System.out.println("JSP DEBUG: approvedAccounts = " + approvedAccounts.size() + 
                      ", beneficiaries = " + beneficiaries.size() + 
                      ", transactions = " + transactions.size());
%>
<html>
<head>
    <title>Fund Transfer</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <!-- DataTables CSS -->
    <link href="https://cdn.datatables.net/1.13.6/css/dataTables.bootstrap5.min.css" rel="stylesheet">
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
        /* Ensure fields can be shown */
        #beneficiaryField, #toAccountField {
            display: none;
        }
        #beneficiaryField.show, #toAccountField.show {
            display: block !important;
        }
    </style>
</head>
<body>
    <div class="d-flex">
        <%@ include file="sidebar.jsp" %>

        <div class="col-md-10">
            <div class="main-content p-4">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h2>💸 Fund Transfer</h2>
                    <a href="${pageContext.request.contextPath}/customer/dashboard" class="btn btn-secondary btn-sm">← Back to Dashboard</a>
                </div>

                <% if (request.getAttribute("error") != null) { %>
                    <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
                <% } %>
                <% if (request.getAttribute("success") != null) { %>
                    <div class="alert alert-success"><%= request.getAttribute("success") %></div>
                <% } %>

                <!-- Transfer Form -->
                <div class="card mb-4">
                    <div class="card-header">
                        <h5><i class="bi bi-arrow-left-right"></i> Make a Transfer</h5>
                    </div>
                    <div class="card-body">
                        <% if (approvedAccounts.isEmpty()) { %>
                            <div class="alert alert-warning">
                                <i class="bi bi-exclamation-triangle"></i>
                                <strong>No Approved Accounts:</strong> You need at least one approved account to make transfers. 
                                <a href="${pageContext.request.contextPath}/customer/open_account" class="alert-link">Open a new account</a>.
                            </div>
                        <% } else { %>
                            <form action="${pageContext.request.contextPath}/customer/transfer" method="post" id="transferForm" onsubmit="return validateForm()">
                                <!-- From Account -->
                                <div class="mb-3">
                                    <label for="fromAccount">Debit From <span class="text-danger">*</span></label>
                                    <select name="fromAccount" id="fromAccount" class="form-select" required>
                                        <option value="">-- Select Account --</option>
                                        <% for (Account acc : approvedAccounts) {
                                            String masked = "****" + acc.getAccNo().substring(acc.getAccNo().length() - 4);
                                        %>
                                        <option value="<%= acc.getAccNo() %>">
                                            <%= acc.getAccountType() %> • <%= masked %> • ₹<%= String.format("%.2f", acc.getBalance()) %>
                                        </option>
                                        <% } %>
                                    </select>
                                </div>

                                <!-- Transfer Mode -->
                                <div class="mb-3">
                                    <label>Transfer To <span class="text-danger">*</span></label>
                                    <select name="transferMode" id="transferMode" class="form-select" onchange="toggleTransferMode()" required>
                                        <option value="">-- Choose Method --</option>
                                        <option value="beneficiary">Select from Beneficiaries</option>
                                        <option value="direct">Enter Account Number</option>
                                    </select>
                                </div>

                                <!-- Beneficiary Dropdown -->
                                <div id="beneficiaryField" class="mb-3">
                                    <label>Select Beneficiary <span class="text-danger">*</span></label>
                                    <% if (beneficiaries.isEmpty()) { %>
                                        <div class="alert alert-info">
                                            <i class="bi bi-info-circle"></i>
                                            <strong>No Beneficiaries Found:</strong> You haven't added any beneficiaries yet. 
                                            <a href="${pageContext.request.contextPath}/customer/add_beneficiary.jsp" class="alert-link">Add a beneficiary</a> or use direct transfer.
                                        </div>
                                    <% } else { %>
                                        <select name="toBeneficiary" id="toBeneficiary" class="form-select" required>
                                            <option value="">-- Select Beneficiary --</option>
                                            <% for (Beneficiary b : beneficiaries) {
                                                String masked = "****" + b.getBeneficiaryAccNo().substring(b.getBeneficiaryAccNo().length() - 4);
                                            %>
                                            <option value="<%= b.getBeneficiaryAccNo() %>">
                                                <%= b.getNickname() %> (<%= masked %>)
                                            </option>
                                            <% } %>
                                        </select>
                                    <% } %>
                                </div>

                                <!-- Direct Account Input -->
                                <div id="toAccountField" class="mb-3">
                                    <label>Receiver Account Number <span class="text-danger">*</span></label>
                                    <input type="text" name="toAccount" id="toAccount" class="form-control" pattern="\\d{11,16}" title="11 to 16 digits" placeholder="Enter 11-16 digit account number">
                                </div>

                                <!-- Amount -->
                                <div class="mb-3">
                                    <label>Amount (₹) <span class="text-danger">*</span></label>
                                    <input type="number" name="amount" id="amount" class="form-control" min="0.01" step="0.01" required placeholder="Enter amount">
                                </div>

                                <!-- Remarks -->
                                <div class="mb-3">
                                    <label>Remarks</label>
                                    <input type="text" name="remarks" class="form-control" maxlength="100" placeholder="Optional description">
                                </div>

                                <!-- Submit -->
                                <button type="submit" class="btn btn-success" id="submitBtn">
                                    <i class="bi bi-arrow-left-right"></i> Transfer Now
                                </button>
                            </form>
                        <% } %>
                    </div>
                </div>

                <!-- Transaction History -->
                <div class="card">
                    <div class="card-header">
                        <h5><i class="bi bi-clock-history"></i> Recent Transactions</h5>
                    </div>
                    <div class="card-body">
                        <% if (transactions.isEmpty()) { %>
                            <p class="text-muted">No transactions found.</p>
                        <% } else { %>
                            <div class="table-responsive">
                                <table id="transactionTable" class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>Date</th>
                                            <th>Type</th>
                                            <th>From</th>
                                            <th>To</th>
                                            <th>Amount</th>
                                            <th>Balance</th>
                                            <th>Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for (Transaction tx : transactions) { %>
                                        <tr>
                                            <td><%= tx.getTransactionDate() %></td>
                                            <td>
                                                <% if ("transfer_out".equals(tx.getType())) { %>
                                                    <span class="badge bg-primary">Out</span>
                                                <% } else { %>
                                                    <span class="badge bg-success">In</span>
                                                <% } %>
                                            </td>
                                           <td>
    <%= tx.getAccNo() != null 
        ? "****" + tx.getAccNo().substring(Math.max(0, tx.getAccNo().length() - 4)) 
        : "-" %>
</td>
<td>
    <%= tx.getRelatedAccNo() != null 
        ? "****" + tx.getRelatedAccNo().substring(Math.max(0, tx.getRelatedAccNo().length() - 4)) 
        : "-" %>
</td>

                                            <td>₹<%= String.format("%.2f", tx.getAmount()) %></td>
                                            <td>₹<%= String.format("%.2f", tx.getBalanceAfter()) %></td>
                                            <td><span class="badge bg-success">Success</span></td>
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

    <!-- JavaScript -->
    <script>
        function toggleTransferMode() {
            const mode = document.getElementById("transferMode").value;
            const toAccountField = document.getElementById("toAccountField");
            const beneficiaryField = document.getElementById("beneficiaryField");
            const toAccountInput = document.getElementById("toAccount");
            const toBeneficiaryInput = document.getElementById("toBeneficiary");

            // Hide both
            if (toAccountField) toAccountField.classList.remove("show");
            if (beneficiaryField) beneficiaryField.classList.remove("show");
            if (toAccountInput) toAccountInput.required = false;
            if (toBeneficiaryInput) toBeneficiaryInput.required = false;

            if (mode === "beneficiary" && !<%= beneficiaries.isEmpty() %>) {
                if (beneficiaryField) {
                    beneficiaryField.classList.add("show");
                    if (toBeneficiaryInput) toBeneficiaryInput.required = true;
                }
            } else if (mode === "direct") {
                if (toAccountField) {
                    toAccountField.classList.add("show");
                    if (toAccountInput) toAccountInput.required = true;
                }
            }
        }

        function validateForm() {
            const fromAccount = document.getElementById("fromAccount").value;
            const transferMode = document.getElementById("transferMode").value;
            const toAccount = document.getElementById("toAccount")?.value || "";
            const toBeneficiary = document.getElementById("toBeneficiary")?.value || "";
            const amount = document.getElementById("amount").value;

            if (!fromAccount) {
                alert("Please select a debit account.");
                return false;
            }
            if (!transferMode) {
                alert("Please select a transfer method.");
                return false;
            }
            if (transferMode === "beneficiary" && !toBeneficiary) {
                alert("Please select a beneficiary.");
                return false;
            }
            if (transferMode === "direct" && (!toAccount || !/^\d{11,16}$/.test(toAccount))) {
                alert("Enter valid 11-16 digit account number.");
                return false;
            }
            if (amount <= 0) {
                alert("Amount must be greater than zero.");
                return false;
            }
            if ((transferMode === "direct" && fromAccount === toAccount) ||
                (transferMode === "beneficiary" && fromAccount === toBeneficiary)) {
                alert("Cannot transfer to your own account.");
                return false;
            }

            if (!confirm("Confirm transfer of ₹" + parseFloat(amount).toFixed(2) + "?")) {
                return false;
            }

            document.getElementById("submitBtn").disabled = true;
            document.getElementById("submitBtn").innerHTML = '<i class="bi bi-hourglass-split"></i> Processing...';
            return true;
        }

        // Trigger on load
        document.addEventListener('DOMContentLoaded', function() {
            const transferMode = document.getElementById("transferMode");
            if (transferMode) {
                transferMode.addEventListener('change', toggleTransferMode);
                if (transferMode.value) toggleTransferMode(); // Initial call
            }
        });
    </script>

    <!-- Scripts -->
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.6/js/dataTables.bootstrap5.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#transactionTable').DataTable({
                "pageLength": 5,
                "ordering": true,
                "searching": true,
                "info": true,
                "autoWidth": false
            });
        });
    </script>
</body>
</html>