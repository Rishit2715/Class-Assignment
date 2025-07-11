package com.tss.basics3.arrays;

import java.util.Scanner;

public class ShiftStringCharacters {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of shift you want");
        int shift = scanner.nextInt(); 
        System.out.println("Enter a string");
        String input = scanner.next();
        char[] result = new char[input.length()];

        int size = input.length();

        for (int i = 0; i < size; i++) {
            char temp = input.charAt(i);
            int asciiValue = (int) temp;
            if (Character.isLowerCase(temp)) {
                asciiValue = (asciiValue - 'a' + shift) % 26 + 'a';
                if (asciiValue < 'a') {
                    asciiValue += 26;  
                }
            } else if (Character.isUpperCase(temp)) {
                asciiValue = (asciiValue - 'A' + shift) % 26 + 'A';
                if (asciiValue < 'A') {
                    asciiValue += 26;  
                }
            }
            result[i] = (char) asciiValue;
        }

        input = new String(result);

        System.out.println("Shifted input\n" + input);
    }
}
