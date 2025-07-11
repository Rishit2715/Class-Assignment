package com.tss.model;

import java.io.File;

public class FileDetails {

	public static void main(String[] args) {

		File file = new File("C:\\Users\\rishit.rathod\\Desktop\\training-rishit\\assignments\\filehandling_app");
		printNames(file);

	}

	private static void printNames(File file) {
		File[] files;

		boolean isDirectory = file.isDirectory();

		if (!isDirectory) {
			System.out.println("Total length of File " + file.getName() + "is => " + file.length());
		} else {
			files = file.listFiles();
			if(files!=null) {
				for (File filee : files) {
					printNames(filee);
				}
			}
			System.out.println(file.getName());

		}
	}
}
