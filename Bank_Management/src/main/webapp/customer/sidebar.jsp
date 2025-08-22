<!-- File: web/customer/sidebar.jsp -->
<%@ page import="com.tss.model.Customer" %>
<%
    Customer customer = (Customer) session.getAttribute("user");
    if (customer == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
    
    // Get current page for active state
    String currentPage = request.getRequestURI();
    String contextPath = request.getContextPath();
%>

<style>
    body {
        background-color: #f5f7fa;
        font-family: 'Segoe UI', sans-serif;
        color: #333;
    }

    /* Sidebar - Dark Elegant Grey */
    .sidebar {
        min-height: 100vh;
        background: #2c3e50;
        color: #ecf0f1;
        padding: 1rem;
        box-shadow: 3px 0 10px rgba(0, 0, 0, 0.1);
        width: 250px;
        flex-shrink: 0;
    }
    .sidebar h5 {
        color: #ffffff;
        font-weight: 600;
        letter-spacing: 0.5px;
    }
    .sidebar .nav-link {
        color: #bdc3c7;
        border-radius: 8px;
        margin-bottom: 0.4rem;
        padding: 0.7rem 1rem;
        font-size: 0.95rem;
        transition: all 0.3s ease;
        text-decoration: none;
    }
    .sidebar .nav-link:hover {
        background: rgba(255, 255, 255, 0.1);
        color: #ffffff;
        text-decoration: none;
    }
    .sidebar .nav-link.active {
        background: #34495e;
        color: #ffffff;
        font-weight: 500;
    }
    .sidebar .nav-link i {
        margin-right: 8px;
        width: 20px;
        text-align: center;
    }

    /* Main Content */
    .main-content {
        padding: 2rem;
        flex: 1;
    }

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

<div class="sidebar">
    <h5 class="text-center mb-4"><i class="bi bi-bank"></i> Bank</h5>
    <ul class="nav flex-column">
        <li class="nav-item">
            <a class="nav-link <%= currentPage.contains("/dashboard") ? "active" : "" %>" 
               href="${pageContext.request.contextPath}/customer/dashboard">
                <i class="bi bi-speedometer2"></i> Dashboard
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#" data-bs-toggle="modal" data-bs-target="#depositModal">
                <i class="bi bi-cash"></i> Deposit
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#" data-bs-toggle="modal" data-bs-target="#withdrawModal">
                <i class="bi bi-wallet"></i> Withdraw
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link <%= currentPage.contains("/transfer") ? "active" : "" %>" 
               href="${pageContext.request.contextPath}/customer/transfer">
                <i class="bi bi-arrow-left-right"></i> Fund Transfer
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link <%= currentPage.contains("/open_account") ? "active" : "" %>" 
               href="${pageContext.request.contextPath}/customer/open_account.jsp">
                <i class="bi bi-plus-circle"></i> Open Account
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link <%= currentPage.contains("/add_beneficiary") ? "active" : "" %>" 
               href="${pageContext.request.contextPath}/customer/add_beneficiary.jsp">
                <i class="bi bi-people"></i> Add Beneficiary
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/customer/manage_beneficiaries.jsp">
                <i class="bi bi-person-lines-fill"></i> Manage Beneficiaries
            </a>
        </li>
        <li class="nav-item mt-auto pt-3">
            <a class="nav-link text-danger" href="${pageContext.request.contextPath}/logout.jsp">
                <i class="bi bi-box-arrow-right"></i> Logout
            </a>
        </li>
    </ul>
</div>