package com.tss.builder.model;

public class CustomComputerBuilder implements IComputerBuilder {
	private Computer computer = new Computer();

	public void buildCPU(String cpu) {
		computer.setCPU(cpu);
	}

	public void buildRAM(String ram) {
		computer.setRAM(ram);
	}

	public void buildGraphicsCard(boolean hasGraphicsCard) {
		computer.setGraphicsCard(hasGraphicsCard);
	}

	public Computer getComputer() {
		return computer;
	}
}