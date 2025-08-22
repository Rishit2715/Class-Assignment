<%@ page import="com.tss.model.Account" %>
<%@ page import="com.tss.service.AccountService" %>
<%@ page import="java.util.List" %>
<%
    if (!"admin".equals(session.getAttribute("role"))) {
        response.sendRedirect("../login.jsp");
        return;
    }
    List<Account> pending = new AccountService().getPendingAccounts();
%>
<!-- Simple table -->
<div class="container mt-4">
    <h3>Pending Account Requests</h3>
    <table class="table table-bordered">
        <tr>
            <th>Account ID</th>
            <th>Customer ID</th>
            <th>Type</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        <% for (Account a : pending) { %>
        <tr>
            <td><%= a.getAccNo() %></td>
            <td><%= a.getCustId() %></td>
            <td><%= a.getAccountType() %></td>
            <td><span class="badge bg-warning text-dark">Pending</span></td>
            <td>
                <a href="approve_account?accNo=<%= a.getAccNo() %>" class="btn btn-success btn-sm">Approve</a>
                <a href="reject_account?accNo=<%= a.getAccNo() %>" class="btn btn-danger btn-sm">Reject</a>
            </td>
        </tr>
        <% } %>
    </table>
</div>