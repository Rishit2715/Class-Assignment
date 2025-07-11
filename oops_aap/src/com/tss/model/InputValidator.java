package com.tss.model;

public class InputValidator {

	public static boolean validateUsername(String username) {
		return isValidLength(username, 3, 20);
	}

	public static boolean validatePassword(String password) {
		return isValidLength(password, 8, 30);
	}

	public static boolean validateEmail(String email) {
		return isValidLength(email, 5, 50) && email.contains("@") && email.contains(".");
	}

	private static boolean isValidLength(String input, int min, int max) {
		return (input != null) && (!input.isEmpty()) && (input.length() >= min) && (input.length() <= max);
	}

}
