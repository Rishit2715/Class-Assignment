<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>Employee Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <!-- DataTables CSS -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/dataTables.bootstrap5.min.css">

<style>
    body { background-color: #f8f9fa; }
    .card { border-radius: 10px; border: none; }
    .table { background: white; border-radius: 8px; overflow: hidden; }
    .table thead { background: #0d6efd; color: white; }
    .badge { font-size: 0.9rem; }
    .gap-card { margin-bottom: 20px; }
</style>
</head>
<body>

<div class="container-fluid p-4">

    <!-- Header -->
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h3 class="fw-bold"><i class="bi bi-person-badge"></i> Welcome, ${user.fullName}</h3>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger btn-sm">
            <i class="bi bi-box-arrow-right"></i> Logout
        </a>
    </div>

    <!-- Alerts -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>

    <div class="row g-4">

        <!-- Left Column -->
        <div class="col-lg-4">

            <!-- Leave Balance -->
            <div class="card shadow-sm gap-card">
                <div class="card-body">
                    <h5 class="card-title"><i class="bi bi-graph-up"></i> Leave Balance</h5>
                    <hr>
                    <p>Annual Left: <span class="badge bg-primary">${balance.annualLeft}</span></p>
                    <p>Sick Left: <span class="badge bg-warning text-dark">${balance.sickLeft}</span></p>
                </div>
            </div>

            <!-- Apply / Edit Leave Form -->
            <div class="card shadow-sm p-3 gap-card">
                <h5><i class="bi bi-pencil-square"></i> Apply for Leave</h5>
                <hr>
                <form id="leaveForm" action="${pageContext.request.contextPath}/employee" method="post" class="row g-3">
                    <input type="hidden" name="action" id="formAction" value="apply" />
                    <input type="hidden" name="requestId" id="requestId" value="" />

                    <div class="col-12">
                        <label class="form-label">Leave Type</label>
                        <select name="leaveType" id="leaveType" class="form-select" required>
                            <option value="">Select</option>
                            <option value="ANNUAL">Annual</option>
                            <option value="SICK">Sick</option>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">From Date</label>
                        <input type="date" id="fromDate" name="fromDate" class="form-control" required>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">To Date</label>
                        <input type="date" id="toDate" name="toDate" class="form-control" required>
                    </div>
                    <div class="col-12">
                        <label class="form-label">Days</label>
                        <input id="days" name="days" type="number" class="form-control" readonly>
                    </div>
                    <div class="col-12">
                        <label class="form-label">Reason</label>
                        <textarea name="reason" id="reason" class="form-control" rows="2" required></textarea>
                    </div>
                    <div class="col-12">
                        <button type="submit" id="submitBtn" class="btn btn-primary w-100">
                            <i class="bi bi-send"></i> Submit Request
                        </button>
                    </div>
                </form>
            </div>
        </div>

        <!-- Right Column -->
        <div class="col-lg-8">
            <h5><i class="bi bi-list-ul"></i> My Leave Requests</h5>
            <c:if test="${empty myRequests}">
                <div class="alert alert-info">No leave requests found.</div>
            </c:if>
            <c:if test="${not empty myRequests}">
                <div class="table-responsive shadow-sm">
                    <table id="leaveTable" class="table table-bordered table-hover align-middle">
                        <thead>
                            <tr>
                                <th>Leave Type</th>
                                <th>From</th>
                                <th>To</th>
                                <th>Days</th>
                                <th>Reason</th>
                                <th>Status</th>
                                <th>Admin Comment</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="r" items="${myRequests}">
                                <tr>
                                    <td>${r.leaveType}</td>
                                    <td>${r.fromDate}</td>
                                    <td>${r.toDate}</td>
                                    <td>${r.days}</td>
                                    <td>${r.reason}</td>
                                    <td>
                                        <span class="badge 
                                            ${r.status eq 'APPROVED' ? 'bg-success' : 
                                              r.status eq 'REJECTED' ? 'bg-danger' : 
                                              'bg-secondary'}">
                                            ${r.status}
                                        </span>
                                    </td>
                                    <td>${r.adminComment}</td>
                                    <td>
                                        <c:if test="${r.status eq 'PENDING'}">
                                            <button type="button" class="btn btn-sm btn-warning edit-btn"
                                                    data-id="${r.requestId}"
                                                    data-type="${r.leaveType}"
                                                    data-from="${r.fromDate}"
                                                    data-to="${r.toDate}"
                                                    data-days="${r.days}"
                                                    data-reason="${r.reason}">
                                                <i class="bi bi-pencil"></i> Edit
                                            </button>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>

    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- DataTables JS -->
<script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.4/js/dataTables.bootstrap5.min.js"></script>

<script>
document.addEventListener("DOMContentLoaded", function () {
    const fromDate = document.getElementById("fromDate");
    const toDate = document.getElementById("toDate");
    const daysField = document.getElementById("days");

    const today = new Date().toISOString().split("T")[0];
    fromDate.setAttribute("min", today);
    toDate.setAttribute("min", today);

    function calculateDays() {
        if (fromDate.value && toDate.value) {
            const start = new Date(fromDate.value);
            const end = new Date(toDate.value);
            if (end >= start) {
                const diffTime = end - start;
                const diffDays = Math.floor(diffTime / (1000 * 60 * 60 * 24)) + 1;
                daysField.value = diffDays;
            } else {
                daysField.value = "";
            }
        }
    }

    fromDate.addEventListener("change", () => {
        toDate.setAttribute("min", fromDate.value);
        calculateDays();
    });
    toDate.addEventListener("change", calculateDays);

    // Initialize DataTable
    $('#leaveTable').DataTable();

    // Edit button functionality
    $('.edit-btn').on('click', function () {
        const btn = $(this);
        
        $('#formAction').val('update'); // Change form action to update
        $('#requestId').val(btn.data('id'));
        $('#leaveType').val(btn.data('type'));
        $('#fromDate').val(btn.data('from'));
        $('#toDate').val(btn.data('to'));
        $('#days').val(btn.data('days'));
        $('#reason').val(btn.data('reason'));
        
        // Scroll to form
        $('html, body').animate({
            scrollTop: $("#leaveForm").offset().top
        }, 500);

        // Change submit button text
        $('#submitBtn').html('<i class="bi bi-pencil"></i> Update Request');
    });

    // Reset form after submission if needed
    $('#leaveForm').on('submit', function () {
        if ($('#formAction').val() === 'update') {
            setTimeout(() => {
                $('#leaveForm')[0].reset();
                $('#formAction').val('apply');
                $('#submitBtn').html('<i class="bi bi-send"></i> Submit Request');
            }, 100);
        }
    });
});
</script>
</body>
</html>
