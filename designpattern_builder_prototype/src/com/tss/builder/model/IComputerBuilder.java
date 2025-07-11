package com.tss.builder.model;

public interface IComputerBuilder {
	void buildCPU(String cpu);

	void buildRAM(String ram);

	void buildGraphicsCard(boolean hasGraphicsCard);

	Computer getComputer();
}
