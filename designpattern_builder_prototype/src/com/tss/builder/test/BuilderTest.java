package com.tss.builder.test;

import java.util.Scanner;

import com.tss.builder.model.Computer;
import com.tss.builder.model.CustomComputerBuilder;
import com.tss.builder.model.IComputerBuilder;

public class BuilderTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter CPU type: ");
		String cpu = scanner.nextLine();

		System.out.print("Enter RAM size: ");
		String ram = scanner.nextLine();

		System.out.print("Do you want a Graphics Card? (yes/no): ");
		String gcInput = scanner.nextLine().trim().toLowerCase();
		boolean hasGraphicsCard = gcInput.equals("yes");

		IComputerBuilder builder = new CustomComputerBuilder();
		builder.buildCPU(cpu);
		builder.buildRAM(ram);
		builder.buildGraphicsCard(hasGraphicsCard);

		Computer userComputer = builder.getComputer();
		System.out.println("Your custom-built computer: ");
		System.out.println(userComputer);

		scanner.close();
	}
}
