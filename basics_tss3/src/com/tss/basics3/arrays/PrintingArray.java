package com.tss.basics3.arrays;

import java.util.Scanner;

public class PrintingArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int arr[] = new int[5];
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("enter array elememts: ");
		for(int i=0; i<5; i++) {
			arr[i]=scanner.nextInt();
		}
		
		for(int i=0; i<5; i++) {
			System.out.print("\t"+arr[i]);
		}
		
	}

}
