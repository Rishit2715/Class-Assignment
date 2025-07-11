package com.tss.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import com.tss.model.Employee;

public class EmployeeTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Employee emp = new Employee();

        System.out.print("Enter employee ID: ");
        emp.setEmpid(scanner.nextInt());
        scanner.nextLine(); 

        System.out.print("Enter employee name: ");
        emp.setName(scanner.nextLine());

        System.out.print("Enter joining date (yyyy-MM-dd): ");
        String date = scanner.nextLine();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate joiningDate = LocalDate.parse(date, formatter);
            emp.setJoiningDate(joiningDate);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Please enter date as yyyy-MM-dd");
            e.printStackTrace();
            scanner.close();
            return;
        }

        System.out.print("Enter salary: ");
        emp.setSalary(scanner.nextDouble());

        System.out.println("\n--- Employee Details ---");
        emp.display();

        scanner.close();
    }
}
