<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="userBean" class="com.tss.model.User" scope="request" />
<jsp:setProperty name="userBean" property="*" />

<html>
<head>
<title>JSP Actions Demo</title>
</head>
<body style="font-family: Arial;">

    <h2>JSP Standard Actions Example</h2>

    <p>
        Welcome, <b><jsp:getProperty name="userBean" property="username" /></b>!
    </p>

    <p>
        Email: <b><jsp:getProperty name="userBean" property="email" /></b>
    </p>

    <p>
        Age: <b><jsp:getProperty name="userBean" property="age" /></b>
    </p>

    <hr>

    <h3>Including header.jsp</h3>
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Dashboard" />
    </jsp:include>

    <hr>

    <h3>Forwarding to welcome.jsp</h3>
    <jsp:forward page="welcome.jsp">
        <jsp:param name="msg" value="Login Successful" />
        <jsp:param name="user" value="${userBean.username}" />
    </jsp:forward>

</body>
</html>
