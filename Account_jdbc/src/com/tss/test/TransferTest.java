package com.tss.test;

import com.tss.service.TransferService;

import java.util.Scanner;

public class TransferTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransferService service = new TransferService();

        try {
            System.out.print("Enter Sender Account ID: ");
            int senderId = scanner.nextInt();

            System.out.print("Enter Receiver Account ID: ");
            int receiverId = scanner.nextInt();

            System.out.print("Enter Amount to Transfer: ");
            double amount = scanner.nextDouble();

            service.transferFunds(senderId, receiverId, amount);
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}

