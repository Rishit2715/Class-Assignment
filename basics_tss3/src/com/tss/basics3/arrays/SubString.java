package com.tss.basics3.arrays;

import java.util.Scanner;

public class SubString {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("enter string 1");
		String string1 = scanner.next().toLowerCase();
		System.out.println("enter string 2");
		String string2 = scanner.next().toLowerCase();

		if (isSubString(string1, string2)) {
			System.out.println("string 2 is substring of string 1");
		} else {
			System.out.println("string 2 is not substring of string 1");
		}

	}

	private static boolean isSubString(String string1, String string2) {
		// TODO Auto-generated method stub
		int size = string1.length() - string2.length();
		for (int i = 0; i <=size; i++) {
			boolean match = true;

			for (int j = 0; j < string2.length(); j++) {
				if (string1.charAt(i + j) != string2.charAt(j)) {
					match = false;
					break;
				}
			}
			if (match) {
				return true;
			}
		}
		return false;
	}

}
