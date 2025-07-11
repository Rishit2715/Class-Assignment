package com.tss.model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReadTest {
	public static void main(String[] args) {
		int charCount = 0;
		int wordCount = 0;
		int lineCount = 0;

		try (FileReader reader = new FileReader("input.txt");
		     FileWriter writer = new FileWriter("output.txt")) {

			int ch;
			boolean inWord = false;

			while ((ch = reader.read()) != -1) {
				char c = (char) ch;
				charCount++;

				if (c == '\n') {
					lineCount++;
				}

				if (Character.isWhitespace(c)) {
					inWord = false;
				} else {
					if (!inWord) {
						wordCount++;
						inWord = true;
					}
				}
			}

			if (charCount > 0 && ch != '\n') {
				lineCount++;
			}

			System.out.println("\nFile copied successfully!");
			System.out.println("Total Lines: " + lineCount);
			System.out.println("Total Words: " + wordCount);
			System.out.println("Total Characters: " + charCount);

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
