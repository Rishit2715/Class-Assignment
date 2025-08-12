<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String savedName = (String) request.getAttribute("savedName");
    if (savedName == null) savedName = "";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Feedback App</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6f8;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            background: #fff;
            margin: 40px auto;
            padding: 25px 30px;
            border-radius: 8px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 25px;
        }
        label {
            font-weight: bold;
            color: #444;
            display: block;
            margin-top: 15px;
        }
        input[type="text"], 
        input[type="date"],
        input[type="number"],
        textarea {
            width: 100%;
            padding: 10px;
            margin-top: 6px;
            border-radius: 5px;
            border: 1px solid #ccc;
            outline: none;
            font-size: 14px;
            box-sizing: border-box;
        }
        textarea {
            resize: vertical;
        }
        input[type="text"]:focus, 
        input[type="date"]:focus,
        input[type="number"]:focus,
        textarea:focus {
            border-color: #4a90e2;
            box-shadow: 0 0 4px rgba(74, 144, 226, 0.3);
        }
        button {
            background: #4a90e2;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            margin-top: 20px;
            font-size: 15px;
            width: 100%;
        }
        button:hover {
            background: #357ab7;
        }
        .link-container {
            text-align: center;
            margin-top: 20px;
        }
        a {
            color: #4a90e2;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Feedback App</h2>

    <form method="post" action="${pageContext.request.contextPath}/feedback">
        <label>Name:</label>
        <input type="text" name="name" value="<%= savedName %>" required />

        <label>Session Date:</label>
        <input type="date" name="sessionDate" />

        <label>Feedback / Comments:</label>
        <textarea name="comments" rows="4"></textarea>

        <label>Session Content:</label>
        <input type="text" name="sessionContent" value="8"/>

        <label>Query Resolution / Feedback (0-10):</label>
        <input type="number" name="queryResolution" min="0" max="10" value="10"/>

        <label>Interactivity / Engagement (0-10):</label>
        <input type="number" name="interactivity" min="0" max="10" value="9"/>

        <label>Impactful Learning (0-10):</label>
        <input type="number" name="impactfulLearning" min="0" max="10" value="9"/>

        <label>Content Delivery Skills (0-10):</label>
        <input type="number" name="contentDeliverySkills" min="0" max="10" value="8"/>

        <button type="submit">Submit Feedback</button>
    </form>

    <div class="link-container">
        <a href="${pageContext.request.contextPath}/feedback?action=list">View all feedback</a>
    </div>
</div>
</body>
</html>
