package com.tss.basics3.arrays;

import java.util.Scanner;

public class FindElementFromArray {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("enter number of elemts you want");
		int size = scanner.nextInt();
		
		System.out.println("Enter number you want find/search");
		int search = scanner.nextInt();

		int arr[] = new int[size];
		int max=0;
		System.out.println("enter array elememts: ");
		for(int i=0; i<size; i++) {
			arr[i]=scanner.nextInt();
			if(search==arr[i]) {
				System.out.println("Element found at position: "+(i+1));
				return;
			}
		}
		System.out.println("Element is not in the list");

		
	}

}
