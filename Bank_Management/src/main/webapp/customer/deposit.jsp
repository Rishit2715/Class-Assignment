<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.tss.model.Customer" %>
<%@ page import="com.tss.model.Account" %>
<%@ page import="java.util.List" %>
<%
    Customer customer = (Customer) session.getAttribute("user");
    if (customer == null) {
        response.sendRedirect("../login.jsp");
        return;
    }

    List<Account> accounts = (List<Account>) request.getAttribute("accounts");
    if (accounts == null || accounts.isEmpty()) {
        accounts = new java.util.ArrayList<>();
    }
%>
<html>
<head>
    <title>Deposit Money</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5" style="max-width: 600px;">
        <h3><i class="bi bi-cash"></i> Deposit Money</h3>
        <hr>

        <a href="dashboard.jsp" class="btn btn-secondary btn-sm mb-4">← Back to Dashboard</a>

        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
        <% } %>
        <% if (request.getAttribute("success") != null) { %>
            <div class="alert alert-success"><%= request.getAttribute("success") %></div>
        <% } %>

        <form action="${pageContext.request.contextPath}/customer/deposit" method="post">
            <!-- Select Account -->
            <div class="mb-3">
                <label for="accNo">Deposit To</label>
                <select name="accNo" id="accNo" class="form-select" required>
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

            <!-- Amount -->
            <div class="mb-3">
                <label for="amount">Amount (₹)</label>
                <input type="number" name="amount" id="amount" class="form-control"
                       min="1" step="0.01" placeholder="Enter amount" required>
            </div>

            <!-- Remarks -->
            <div class="mb-3">
                <label for="remarks">Remarks</label>
                <input type="text" name="remarks" id="remarks" class="form-control"
                       placeholder="e.g., Cash Deposit, Salary" maxlength="100">
            </div>

            <button type="submit" class="btn btn-success w-100">Deposit Now</button>
        </form>
    </div>
</body>
</html>