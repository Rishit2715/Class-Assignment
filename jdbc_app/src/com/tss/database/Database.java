package com.tss.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tss.model.Student;

public class Database {

	private Connection connection = null;
	private Statement statement;

	public Database() {
		try {
			// Registering driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish connection (assign to class-level variable)
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tss_student_db", "root", "Rishit91@#15");

			System.out.println("Connection established successfully");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void readAllStudents() {
		try {
			statement = connection.createStatement();

			ResultSet result = statement.executeQuery("SELECT * FROM students");

			
			while (result.next()) {
				System.out.println(
					result.getInt("student_id") + "\t" +
					result.getInt("roll_no") + "\t" +
					result.getString("student_name") + "\t" +
					result.getInt("age") + "\t" +
					result.getDouble("percentage")
				);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addStudent(Student student) {
		try {
			statement = connection.createStatement();
			
			String sql = "INSERT INTO students (student_id, student_name, roll_no, age, percentage) VALUES (" +
					student.getStudentId() + ", '" +
					student.getStudentName() + "', " +
					student.getRollNo() + ", " +
					student.getAge() + ", " +
					student.getPercentage() + ")";

			int updates = statement.executeUpdate(sql);
			if (updates > 0) {
				System.out.println("Student inserted successfully");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
