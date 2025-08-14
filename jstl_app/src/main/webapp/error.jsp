<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Error</title></head>
<body>
    <h2>Something went wrong</h2>
    <p style="color:red">${error}</p>
    <a href="${pageContext.request.contextPath}/auth">Back to login</a>
</body>
</html>
