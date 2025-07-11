package com.tss.basics3.arrays;

import java.util.Scanner;

public class SumOfArrayElements {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("enter number of elemts you want");
		int n = scanner.nextInt();

		int arr[] = new int[n];
		int sum=0;
		System.out.println("enter array elememts: ");
		for(int i=0; i<n; i++) {
			arr[i]=scanner.nextInt();
			sum += arr[i];
		}
		System.out.println("sum of elements: "+sum);
		
	}
}
