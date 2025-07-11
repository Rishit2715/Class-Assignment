package com.tss.test;

import com.tss.model.IPrintPattern;

public class PrintPatternTest {
	public static void main(String[] args) {
		IPrintPattern print = () -> {
			int temp = 1;
			for (int i = 1; i <= 3; i++) {
				for (int j = 0; j < i; j++) {
					System.out.print(temp++ + "\t");
				}
				System.out.println();
			}

		};

		print.printPattern();
	}
}
