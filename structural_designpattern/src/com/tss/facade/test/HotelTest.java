package com.tss.facade.test;


import java.util.Scanner;
import com.tss.facade.model.HotelReception;

public class HotelTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HotelReception reception = new HotelReception();

        while (true) {
            System.out.println("1. Show Indian Menu");
            System.out.println("2. Show Italian Menu");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    reception.getIndianMenu();
                    break;
                case 2:
                    reception.getItalianMenu();
                    break;
                case 3:
                    System.out.println("Exiting. Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
