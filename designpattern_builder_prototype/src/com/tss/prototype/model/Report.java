package com.tss.prototype.model;

public class Report implements IDocument {
	private String title;
	private String data;

	public Report(String title, String data) {
		this.title = title;
		this.data = data;
	}

	public void print() {
		System.out.println("Report titled '" + title + "': " + data);
	}

	public IDocument clone() {
		return new Report(this.title, this.data);
	}
}