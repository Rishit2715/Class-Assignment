<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Portal</title>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(135deg, #dfe9f3, #ffffff);
            padding: 20px;
            margin: 0;
        }
        h1 {
            color: #2c3e50;
            text-align: center;
            margin-top: 10px;
        }
        .info-box {
            max-width: 400px;
            margin: 30px auto;
            padding: 20px;
            border-radius: 15px;
            background-color: #ecf9ff;
            box-shadow: 0px 8px 20px rgba(0,0,0,0.1);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            animation: fadeIn 0.6s ease;
        }
        .info-box:hover {
            transform: translateY(-5px);
            box-shadow: 0px 12px 25px rgba(0,0,0,0.15);
        }
        .grade-A { border: 3px solid #27ae60; background-color: #e9f9f0; }
        .grade-B { border: 3px solid #2980b9; background-color: #eaf3fa; }
        .grade-C { border: 3px solid #e67e22; background-color: #fdf3e7; }
        .grade-F { border: 3px solid #c0392b; background-color: #fdecea; }
        .info-box h2 {
            color: #34495e;
            margin-bottom: 15px;
            text-align: center;
        }
        .info-box p {
            margin: 8px 0;
            font-size: 16px;
        }
        .date-time {
            text-align: center;
            margin-bottom: 20px;
            color: #555;
            font-style: italic;
        }

        @media screen and (max-width: 500px) {
            .info-box {
                margin: 20px;
                padding: 15px;
            }
        }
    </style>
</head>
<body>

    <!-- Title -->
    <h1><%= "Welcome to Student Portal" %></h1>

    <!-- Current Date & Time -->
    <div class="date-time">
        Current Date & Time: <%= new java.text.SimpleDateFormat("EEEE, MMMM dd yyyy - hh:mm a").format(new java.util.Date()) %>
    </div>

    <%-- Declaration of variables --%>
    <%! 
        String studentName = "Rishit Rathod";
        String course = "Information and Communication Technology";
        int marks = 79;
        String grade = "";
        String gradeClass = "";
    %>

    <%-- Grade Calculation with class assignment --%>
    <%
        if (marks >= 90) {
            grade = "A";
            gradeClass = "grade-A";
        } else if (marks >= 75 && marks < 90) {
            grade = "B";
            gradeClass = "grade-B";
        } else if (marks >= 50 && marks < 75) {
            grade = "C";
            gradeClass = "grade-C";
        } else {
            grade = "F";
            gradeClass = "grade-F";
        }
    %>

    <!-- Display Student Info in a styled, dynamic box -->
    <div class="info-box <%= gradeClass %>">
        <h2>Student Information</h2>
        <p><strong>Name:</strong> <%= studentName %></p>
        <p><strong>Course:</strong> <%= course %></p>
        <p><strong>Marks:</strong> <%= marks %></p>
        <p><strong>Grade:</strong> <%= grade %></p>
    </div>

</body>
</html>
