<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tss.model.Feedback" %>
<%
    List<Feedback> list = (List<Feedback>) request.getAttribute("feedbackList");
    if (list == null) list = java.util.Collections.emptyList();
%>
<!DOCTYPE html>
<html>
<head><meta charset="utf-8"><title>Feedback List</title></head>
<body>
    <h2>All feedback</h2>
    <table border="1" cellpadding="6" cellspacing="0">
        <tr>
            <th>ID</th><th>Name</th><th>Session Date</th><th>Comments</th>
            <th>Session Content</th><th>Query Res</th><th>Interactivity</th><th>Impactful L.</th><th>Delivery</th>
        </tr>
        <% for (Feedback f : list) { %>
            <tr>
                <td><%= f.getId() %></td>
                <td><%= f.getName() %></td>
                <td><%= f.getSessionDate() %></td>
                <td><%= f.getComments() == null ? "" : f.getComments() %></td>
                <td><%= f.getSessionContent() %></td>
                <td><%= f.getQueryResolution() %></td>
                <td><%= f.getInteractivity() %></td>
                <td><%= f.getImpactfulLearning() %></td>
                <td><%= f.getContentDeliverySkills() %></td>
            </tr>
        <% } %>
    </table>
    <p><a href="<%= request.getContextPath() %>/feedback">Back to form</a></p>
</body>
</html>
