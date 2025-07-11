package com.tss.prototype.model;

public class Resume implements IDocument {
	private String name;
	private String content;

	public Resume(String name, String content) {
		this.name = name;
		this.content = content;
	}

	public void print() {
		System.out.println("Resume of " + name + ": " + content);
	}

	public IDocument clone() {
		return new Resume(this.name, this.content);
	}
}