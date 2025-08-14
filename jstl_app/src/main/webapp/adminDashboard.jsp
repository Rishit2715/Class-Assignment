<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.tss.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) { 
        response.sendRedirect(request.getContextPath()+"/auth"); 
        return; 
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/dataTables.bootstrap5.min.css"/>
    <link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.4.1/css/responsive.bootstrap5.min.css"/>
    <style>
        body { background: #f4f6f9; font-family: 'Segoe UI', sans-serif; }
        .dashboard-header { background: linear-gradient(90deg, #007bff, #0056b3); color: white; padding: 20px; border-radius: 10px; }
        table thead { background: #343a40; color: white; }
        table tbody tr:hover { background-color: #f1f5ff; transition: background 0.3s ease; }
        .btn-approve { background-color: #28a745; color: white; }
        .btn-approve:hover { background-color: #218838; }
        .btn-reject { background-color: #dc3545; color: white; }
        .btn-reject:hover { background-color: #c82333; }
        .comment-input { min-width: 150px; }
        .badge-status { font-size: 0.85rem; }
    </style>
</head>
<body>

<div class="container mt-4">
    <div class="dashboard-header d-flex flex-wrap justify-content-between align-items-center">
        <h2 class="mb-2">Admin - ${user.fullName}</h2>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-light btn-sm">Logout</a>
    </div>

    <!-- Messages -->
    <c:if test="${not empty success}">
        <div class="alert alert-success mt-3">✅ ${success}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-danger mt-3">❌ ${error}</div>
    </c:if>

    <!-- Pending Requests -->
    <h3 class="mt-4 mb-3 fw-semibold">📌 Pending Leave Requests</h3>
    <c:if test="${not empty pending}">
        <div class="table-responsive">
            <table id="pendingTable" class="table table-striped table-hover align-middle display responsive nowrap" style="width:100%">
                <thead>
                    <tr>
                        <th>Employee</th>
                        <th>Leave Type</th>
                        <th>From</th>
                        <th>To</th>
                        <th>Days</th>
                        <th>Reason</th>
                        <th>Comment</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="r" items="${pending}">
                        <tr>
                            <td>${r.applicantName}</td>
                            <td><span class="badge bg-primary badge-status">${r.leaveType}</span></td>
                            <td><fmt:formatDate value="${r.fromDate}" pattern="dd-MMM-yyyy" /></td>
                            <td><fmt:formatDate value="${r.toDate}" pattern="dd-MMM-yyyy" /></td>
                            <td>${r.days}</td>
                            <td>${r.reason}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/admin" method="post" class="d-flex flex-wrap gap-2">
                                    <input type="hidden" name="requestId" value="${r.requestId}" />
                                    <input type="text" name="comment" class="form-control form-control-sm comment-input" placeholder="Enter comment" />
                            </td>
                            <td>
                                    <div class="d-flex gap-2">
                                        <button type="submit" name="action" value="approve" class="btn btn-approve btn-sm">Approve</button>
                                        <button type="submit" name="action" value="reject" class="btn btn-reject btn-sm">Reject</button>
                                    </div>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
    <c:if test="${empty pending}">
        <div class="alert alert-info mt-3">ℹ No pending requests.</div>
    </c:if>

    <!-- Past Records -->
    <h3 class="mt-5 mb-3 fw-semibold">📜 Past Leave Records</h3>
    <c:if test="${not empty past}">
        <div class="table-responsive">
            <table id="pastTable" class="table table-bordered table-hover align-middle display responsive nowrap" style="width:100%">
                <thead>
                    <tr>
                        <th>Employee</th>
                        <th>Leave Type</th>
                        <th>From</th>
                        <th>To</th>
                        <th>Days</th>
                        <th>Reason</th>
                        <th>Status</th>
                        <th>Comment</th>
                        <th>Processed On</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="p" items="${past}">
                        <tr>
                            <td>${p.applicantName}</td>
                            <td>${p.leaveType}</td>
                            <td><fmt:formatDate value="${p.fromDate}" pattern="dd-MMM-yyyy" /></td>
                            <td><fmt:formatDate value="${p.toDate}" pattern="dd-MMM-yyyy" /></td>
                            <td>${p.days}</td>
                            <td>${p.reason}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${p.status == 'APPROVED'}">
                                        <span class="badge bg-success">Approved</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-danger">Rejected</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${p.adminComment}</td>
                            <td><fmt:formatDate value="${p.processedOn}" pattern="dd-MMM-yyyy" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
    <c:if test="${empty past}">
        <div class="alert alert-secondary">📂 No past leave records found.</div>
    </c:if>
</div>

<!-- JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.4/js/dataTables.bootstrap5.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.4.1/js/dataTables.responsive.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.4.1/js/responsive.bootstrap5.min.js"></script>
<script>
    $(document).ready(function () {
        $('#pendingTable').DataTable({
            responsive: true,
            pageLength: 5,
            lengthMenu: [5, 10, 20, 50],
            language: { search: "🔍 Search:", lengthMenu: "Show _MENU_ entries" }
        });
        $('#pastTable').DataTable({
            responsive: true,
            pageLength: 5,
            order: [[8, 'desc']],
            lengthMenu: [5, 10, 20, 50],
            language: { search: "🔍 Search:", lengthMenu: "Show _MENU_ entries" }
        });
    });
</script>

</body>
</html>
