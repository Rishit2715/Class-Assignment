package com.tss.builder.model;

import java.util.Scanner;

public class Computer {
	private String CPU;
	private String RAM;
	private boolean hasGraphicsCard;

	public void setCPU(String cPU) {
		this.CPU = cPU;
	}

	public void setRAM(String rAM) {
		this.RAM = rAM;
	}

	public void setGraphicsCard(boolean hasGraphicsCard) {
		this.hasGraphicsCard = hasGraphicsCard;
	}

	public String toString() {
		return "Computer [CPU=" + CPU + ", RAM=" + RAM + ", GraphicsCard=" + hasGraphicsCard + "]";
	}
}
