package com.tss.controller;

import java.util.Scanner;

import com.tss.service.TransferService;

public class TransferController {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TransferService service = new TransferService();

        System.out.print("Enter sender ID: ");
        int senderId = sc.nextInt();

        System.out.print("Enter receiver ID: ");
        int receiverId = sc.nextInt();

        System.out.print("Enter amount to transfer: ");
        double amount = sc.nextDouble();

        service.transferFunds(senderId, receiverId, amount);
        sc.close();
    }
}
