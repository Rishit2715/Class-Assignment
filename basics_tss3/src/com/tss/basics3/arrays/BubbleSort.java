package com.tss.basics3.arrays;

import java.util.Scanner;

public class BubbleSort {
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
		}
		for(int i=0; i<5; i++) {
			System.out.print(arr[i]+"\t");
		}
		
		for(int i=0; i<size-1; i++) {
			for(int j=0; j<size-i-1; j++) {
				if(arr[j]>arr[j+1]) {
					int temp = arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
				}
			}
		}
		
		System.out.println("After Sorted");
		for(int l=0; l<size; l++) {
			System.out.print(arr[l]+"\t");
		}
		
		
	}
}
