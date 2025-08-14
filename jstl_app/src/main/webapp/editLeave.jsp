<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.tss.model.LeaveRequest" %>
<%
    LeaveRequest leave = (LeaveRequest) request.getAttribute("leave");
    if (leave == null) {
        out.println("<h3>No leave request found.</h3>");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Leave Request</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="mb-4">Edit Leave Request</h2>
    <form action="${pageContext.request.contextPath}/updateLeave" method="post" class="card p-4 shadow-sm">
        
        <!-- Hidden field for request ID -->
        <input type="hidden" name="requestId" value="${leave.requestId}">

        <div class="mb-3">
            <label for="leaveType" class="form-label">Leave Type</label>
            <select name="leaveType" id="leaveType" class="form-select" required>
                <option value="Annual" ${leave.leaveType == 'Annual' ? 'selected' : ''}>Annual</option>
                <option value="Sick" ${leave.leaveType == 'Sick' ? 'selected' : ''}>Sick</option>
                <option value="Casual" ${leave.leaveType == 'Casual' ? 'selected' : ''}>Casual</option>
            </select>
        </div>

        <div class="mb-3">
            <label for="fromDate" class="form-label">From Date</label>
            <input type="date" name="fromDate" id="fromDate" class="form-control"
                   value="${leave.fromDate}" required>
        </div>

        <div class="mb-3">
            <label for="toDate" class="form-label">To Date</label>
            <input type="date" name="toDate" id="toDate" class="form-control"
                   value="${leave.toDate}" required>
        </div>

        <div class="mb-3">
            <label for="days" class="form-label">Number of Days</label>
            <input type="number" name="days" id="days" class="form-control"
                   value="${leave.days}" required>
        </div>

        <div class="mb-3">
            <label for="reason" class="form-label">Reason</label>
            <textarea name="reason" id="reason" class="form-control" rows="3" required>${leave.reason}</textarea>
        </div>

        <button type="submit" class="btn btn-primary">Update</button>
        <a href="${pageContext.request.contextPath}/leaveHistory" class="btn btn-secondary">Cancel</a>
    </form>
</div>

</body>
</html>
