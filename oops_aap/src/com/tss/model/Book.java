package com.tss.model;

public class Book {
	
	private int bookId;
	private String name;
	private String author;
	private String publication;
	private int price;
	private double discount;
	
	public Book() {
		bookId = 0;
		name = "";
		author = "";
		publication = "";
		price = 0;
	}
	
	public Book(int bookId, String name, String author, String publication, int price) {
		this.bookId = bookId;
		this.name = name;
		this.author = author;
		this.publication = publication;
		this.price = price;
	}
	
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setPrice(int price) {
		this.price = price;
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
	public String getPublication() {
		return publication;
	}
	public void setPublication(String publication) {
		this.publication = publication;
	}
	public int getPrice() {
		return price;
	}
	public int getBookId() {
		return bookId;
	}
	
	
	public double calculateDiscount() {
		discount = (price * 10.0) / 100;
		return discount;
	}
	

	public void display() {
		System.out.println("Book Id:  " + bookId);
		System.out.println("Book Name:" + name);
		System.out.println("Author Name: " + author);
		System.out.println("Publication Name: " + publication);
		System.out.println("Price = " + price);
		System.out.println("Discount = "+ discount);
	}

}
