package com.tss.model;

public class Book {
	private int book_id;
	private String name;
	private String author;
	private boolean isIssued;
	public Book(int book_id, String name, String author) {
		super();
		this.book_id = book_id;
		this.name = name;
		this.author = author;
		this.isIssued = false;
	}
	public Book() {
		super();
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public boolean isIssued() {
		return isIssued;
	}
	public void setIssued(boolean isIssued) {
		this.isIssued = isIssued;
	}
	@Override
	public String toString() {
		return "Book [book_id=" + book_id + ", name=" + name + ", author=" + author + ", isIssued=" + isIssued + "]";
	}
	

}
