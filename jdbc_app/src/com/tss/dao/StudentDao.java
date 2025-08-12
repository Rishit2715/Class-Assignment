package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tss.database.DBConnection;
import com.tss.model.Student;

public class StudentDao {

	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement prepareStatement = null;

	public StudentDao() {
		this.connection = DBConnection.connect();
	}

	public List<Student> readAllStudents() {
		List<Student> students = new ArrayList<Student>();
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from students");

			while (result.next()) {
				Student student = new Student();
				student.setStudentId(result.getInt("student_id"));
				student.setAge(result.getInt("age"));
				student.setPercentage(result.getDouble("percentage"));
				student.setStudentName(result.getString("student_name"));
				student.setRollNo(result.getInt("roll_no"));
				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

	public Student readStudentById(int studentId) {
	    Student student = null;

	    try {
	        prepareStatement = connection.prepareStatement("SELECT * FROM students WHERE student_id = ?");
	        prepareStatement.setInt(1, studentId);

	        ResultSet result = prepareStatement.executeQuery();

	        if (result.next()) {
	            student = new Student();
	            student.setStudentId(result.getInt("student_id"));
	            student.setStudentName(result.getString("student_name"));
	            student.setAge(result.getInt("age"));
	            student.setPercentage(result.getDouble("percentage"));
	            student.setRollNo(result.getInt("roll_no"));
	        } else {
	            System.out.println("No student found with ID: " + studentId);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return student;
	}


	
public Student updatePercentageById(int studentId, double newPercentage) {
    Student student = null;

    try {
        prepareStatement = connection.prepareStatement("UPDATE students SET percentage = ? WHERE student_id = ?");
        prepareStatement.setDouble(1, newPercentage);
        prepareStatement.setInt(2, studentId);

        int updates = prepareStatement.executeUpdate();

        if (updates > 0) {
            System.out.println("Percentage updated successfully !!");

            prepareStatement = connection.prepareStatement("SELECT * FROM students WHERE student_id = ?");
            prepareStatement.setInt(1, studentId);

            ResultSet result = prepareStatement.executeQuery();

            if (result.next()) {
                student = new Student();
                student.setStudentId(result.getInt("student_id"));
                student.setStudentName(result.getString("student_name"));
                student.setAge(result.getInt("age"));
                student.setPercentage(result.getDouble("percentage"));
                student.setRollNo(result.getInt("roll_no"));
            }
        } else {
            System.out.println("No student found with ID: " + studentId);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return student;
}


	
	
	public boolean deleteStudentById(int studentId) {
	    String sql = "DELETE FROM students WHERE student_id = ?";
	    boolean deleted = false;

	    try (PreparedStatement ps = connection.prepareStatement(sql)) {
	        ps.setInt(1, studentId);
	        int rowsAffected = ps.executeUpdate();
	        deleted = rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return deleted;
	}




	public void addNewStudent(Student student) {
		try {
			prepareStatement = connection.prepareStatement("insert into students values(?,?,?,?,?)");
			prepareStatement.setInt(1, student.getStudentId());
			prepareStatement.setString(2, student.getStudentName());
			prepareStatement.setInt(3, student.getAge());
			prepareStatement.setDouble(4, student.getPercentage());
			prepareStatement.setInt(5, student.getRollNo());

			int updates = prepareStatement.executeUpdate();
			if (updates > 0) {
				System.out.println("inserted Successfully !!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}