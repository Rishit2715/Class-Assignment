package com.tss.test;

import java.util.Scanner;

public class MatrixTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int matrix[][] = new int[3][3];
		
		Scanner scanner = new Scanner(System.in);
		
		readMatrix(matrix, scanner);
		printMartix(matrix);
	}
	
	public static void readMatrix(int matrix[][], Scanner scanner) {
		System.out.println("Enter matrix elements: ");
	
		for(int i=0; i<3; i++) {
			readRow(matrix, scanner, i);
		}	
	}
	
	public static void readRow(int matrix[][], Scanner scanner, int i) {
		for(int j=0; j<3; j++) {
			matrix[i][j]=scanner.nextInt();
		}
	}

	public static void printMartix(int matrix[][]) {
		System.out.println("matrix elements: ");
	
		for(int i=0; i<3; i++) {
			printRow(matrix, i);
			System.out.println();
		}	
	}
	
	public static void printRow(int matrix[][], int i) {
		for(int j=0; j<3; j++) {
			System.out.print(matrix[i][j]+" ");
		}
	}
}
