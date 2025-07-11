package com.tss.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.tss.model.Book;
import com.tss.model.BookAuthorComparator;
import com.tss.model.BookNameComparator;

public class BookTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<Book> books = new ArrayList<>();

		while (true) {
			System.out.println("1. Add a new book");
			System.out.println("2. Issue a book by ID");
			System.out.println("3. Display all available books");
			System.out.println("4. Display all issued books");
			System.out.println("5. Return a book");
			System.out.println("6. Sort Books");
			System.out.println("7. Exit");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				readBooks(books, scanner);
				break;

			case 2:
				issueById(books, scanner);
				break;

			case 3:
				displayAvailableBooks(books);
				break;

			case 4:
				displayIssuedBooks(books);
				break;

			case 5:
				returnBookById(books, scanner);
				break;

			case 6:
				sortBooks(books, scanner);
				break;

			case 7:
				System.out.println("Exiting...");
				scanner.close();
				return;

			default:
				System.out.println("Invalid choice. Try again.");
			}
		}
	}

	private static void readBooks(List<Book> books, Scanner scanner) {
		System.out.print("Enter book ID: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Enter book title: ");
		String title = scanner.nextLine();

		System.out.print("Enter author name: ");
		String author = scanner.nextLine();

		books.add(new Book(id, title, author));
		System.out.println("Book added successfully.");
	}

	private static void issueById(List<Book> books, Scanner scanner) {
		System.out.print("Enter book ID to issue: ");
		int id = scanner.nextInt();

		for (Book b : books) {
			if (b.getBook_id() == id && !b.isIssued()) {
				b.setIssued(true);
				System.out.println("Book issued successfully.");
				return;
			}
		}
		System.out.println("Book not found or already issued.");
	}

	private static void displayAvailableBooks(List<Book> books) {
		System.out.println("Available Books:");
		for (Book b : books) {
			if (!b.isIssued()) {
				System.out.println(b);
			}
		}
	}

	private static void displayIssuedBooks(List<Book> books) {
		System.out.println("Issued Books:");
		for (Book b : books) {
			if (b.isIssued()) {
				System.out.println(b);
			}
		}
	}

	private static void returnBookById(List<Book> books, Scanner scanner) {
		System.out.print("Enter book ID to return: ");
		int id = scanner.nextInt();

		for (Book b : books) {
			if (b.getBook_id() == id && b.isIssued()) {
				b.setIssued(false);
				System.out.println("Book returned successfully.");
				return;
			}
		}
		System.out.println("Book not found or not issued.");
	}

	private static void sortBooks(List<Book> books, Scanner scanner) {
		System.out.println("Books after sorting by Author: ");
		Collections.sort(books, new BookAuthorComparator());
		for (Book b : books) {
			System.out.println(b);
		}

		Collections.sort(books, new BookNameComparator());

		System.out.println("Books after sorting by Title:");
		for (Book b : books) {
			System.out.println(b);
		}
	}

}
