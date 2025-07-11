package com.tss.basics3.arrays;

import java.util.Scanner;

public class MaxOfArray {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("enter number of elemts you want");
		int size = scanner.nextInt();

		int arr[] = new int[size];
		int max=0;
		System.out.println("enter array elememts: ");
		for(int i=0; i<size; i++) {
			arr[i]=scanner.nextInt();
			if(max<arr[i]) {
				max=arr[i];
			}
		}
		System.out.println("max of array elements: "+max);
		
	}
}
