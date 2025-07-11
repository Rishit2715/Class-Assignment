package com.tss.basics3.arrays;

import java.util.Scanner;

public class MatrixAddition {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("enter number of rows");
		int rows = scanner.nextInt();
		System.out.println("enter number of columns");
		int columns = scanner.nextInt();
		
		if(rows!=columns) {
			System.out.println("multiplication not possible");
		}
		
		int matrix1[][] = new int[rows][columns];
		int matrix2[][] = new int[rows][columns];

		System.out.println("enter matrix1 elements");
		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				matrix1[i][j]=scanner.nextInt();
			}
		}
		System.out.println("enter matrix2 elements");
		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				matrix2[i][j]=scanner.nextInt();
			}
		}
		
		
	
		int sum[][] = add(matrix1,matrix2, rows, columns);
		print(sum, rows, columns, matrix1,matrix2);
		int multiply[][] = multiply(matrix1,matrix2, rows, columns);
		print(multiply, rows, columns, matrix1,matrix2);

	}
	
	
	public static int[][] add(int matrix1[][], int matrix2[][], int rows, int columns) {
		int matrix3[][] = new int[rows][columns];

		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				
				matrix3[i][j]= matrix1[i][j] + matrix2[i][j];
			}
		}
		
		System.out.println("Addition of matrix");
		
		return matrix3;
	}
	
	public static int[][] multiply(int matrix1[][], int matrix2[][], int rows, int columns) {
		int matrix3[][] = new int[rows][columns];

		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				for(int k=0; k<rows; k++) {
					matrix3[i][j] += matrix1[i][k] * matrix2[k][j];
				}
			}
		}
		
		System.out.println("Multiplication of matrix");
		
		return matrix3;
	}
	
	public static void print(int print[][], int rows, int columns, int matrix1[][], int matrix2[][]) {
		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				System.out.print(matrix1[i][j]+" ");
			}
			System.out.print("    ");
			for(int j=0; j<columns; j++) {
				System.out.print(matrix2[i][j]+" ");
			}
			System.out.print("    ");
			for(int j=0; j<columns; j++) {
				System.out.print(print[i][j]+" ");
			}
			System.out.println();
		}
		
	}
}
