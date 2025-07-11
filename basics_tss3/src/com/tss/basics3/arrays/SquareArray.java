package com.tss.basics3.arrays;

import java.util.Scanner;

public class SquareArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter number of elemnts you want to entrer in array");
		int size = scanner.nextInt();
		
		int array[] = new int[size];
		System.out.println("Enter elements in sorted order");

		for(int i=0; i<size; i++) {
			array[i]= scanner.nextInt();
		}
		
        int result[] = new int[size];
		int left=0, right=size-1;
		int index = size-1;
		
		while(left<=right) {
			int leftSquare = array[left] * array[left];
			int rightSquare = array[right] * array[right];
			
			if(leftSquare > rightSquare) {
				result[index] = leftSquare;
				left++;
			}
			else {
				result[index] = rightSquare;
				right--;
			}
			index--;
		}
		
		System.out.println("sqyared array:");
		for(int i=0; i<size; i++) {
			System.out.print(result[i]+"\t");
		}
		

	}

}
