package com.tss.basics3.arrays;

import java.util.Scanner;

public class MatrixTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		
		int matrix[][] = new int[3][3];
		System.out.println("enter matrix elements");
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				matrix[i][j]=scanner.nextInt();
			}
		}

		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}

}
