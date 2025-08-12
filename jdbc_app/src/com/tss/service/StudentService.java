package com.tss.service;

import java.util.List;

import com.tss.dao.StudentDao;
import com.tss.model.Student;

public class StudentService {
	private StudentDao studentDao;

	public StudentService() {
		super();
		this.studentDao = new StudentDao();
	}
	
	public List<Student> readAllStudent()
	{
		return studentDao.readAllStudents();
	}

	public Student readStudentById(int studentId) {
		return studentDao.readStudentById(studentId);
	}
	
	public Student updatePercentageById(int studentId, double newPercentage) {
	    return studentDao.updatePercentageById(studentId, newPercentage);
	}

	public boolean deleteStudentById(int studentId) {
	    return studentDao.deleteStudentById(studentId);
	}

	public void addStudent(Student student) {
		
		if(student.getAge()<18)
		{
			System.out.println("Age Must Be Greater Than 18 !!");
			return;
		}
		if(student.getPercentage()<0)
		{
			System.out.println("Percentage Must Be Positive !!");
			return;
		}
		studentDao.addNewStudent(student);

		
	}

}