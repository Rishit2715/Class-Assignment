//package com.tss.test;
//
//import java.util.Scanner;
//import com.tss.database.Database;
//import com.tss.model.Student;
//
//public class DbTest {
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		Database db = new Database();
//
//		System.out.print("Enter Student ID: ");
//		int studentId = sc.nextInt();
//
//		sc.nextLine(); 
//
//		System.out.print("Enter Student Name: ");
//		String studentName = sc.nextLine();
//
//		System.out.print("Enter Roll Number: ");
//		int rollNo = sc.nextInt();
//
//		System.out.print("Enter Age: ");
//		int age = sc.nextInt();
//
//		System.out.print("Enter Percentage: ");
//		double percentage = sc.nextDouble();
//
//		Student student = new Student(studentId, studentName, rollNo, age, percentage);
//
//		db.addStudent(student);
//		db.readAllStudents();
//
//		sc.close(); 
//	}
//}

//package com.tss.test;
//
//import com.tss.controller.StudentController;
//import java.util.Scanner;
//
//public class DbTest {
//    public static void main(String[] args) {
//        StudentController controller = new StudentController();
//        Scanner scanner = new Scanner(System.in);
//        int choice;
//
//        do {
//            System.out.println("\nStudent Management System");
//            System.out.println("1. Read All Students");
//            System.out.println("2. Add New Student");
//            System.out.println("3. Read A Student By ID");
//            System.out.println("4. Update Student's Percentage By ID");
//            System.out.println("5. Delete a Student By ID");
//            System.out.println("6. Exit");
//            System.out.print("Enter your choice: ");
//            choice = scanner.nextInt();
//
//            switch (choice) {
//                case 1:
//                    controller.readAllRecords();
//                    break;
//                case 2:
//                    controller.addNewStudent();
//                    break;
//                case 3:
//                    controller.readStudentById();
//                    break;
//                case 4:
//                    controller.updatePercentageById();
//                    break;
//                case 5:
//                    controller.deleteStudentById();
//                    break;
//                case 6:
//                    System.out.println("Exiting application");
//                    break;
//                default:
//                    System.out.println("Invalid choice! Please try again.");
//            }
//        } while (choice != 6);
//
//        scanner.close();
//    }
//}

package com.tss.test;

import java.sql.Connection;
import java.sql.SQLException;

import com.tss.database.DBConnection;
import com.tss.util.MetaDataUtility;

public class DbTest {
	public static void main(String[] args) {
		Connection connection = DBConnection.connect();

		MetaDataUtility.printDatabaseMetadata(connection);
		MetaDataUtility.printTableMetadata(connection, "students");

		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
