package com.tss.test;

import java.util.Scanner;
import com.tss.model.Book;

public class BookTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of books: ");
        int numBooks = scanner.nextInt();

        for (int i = 0; i < numBooks; i++) {
            System.out.println("\nEnter details for Book " + (i + 1) + ":");
            Book book = readBookDetails(scanner);
            book.calculateDiscount();
            book.display();
        }
    }

    public static Book readBookDetails(Scanner scanner) {

        System.out.print("Enter book id: ");
        int bookId = scanner.nextInt();

        System.out.print("Enter book name: ");
        String name = scanner.next();

        System.out.print("Enter author name: ");
        String author = scanner.next();

        System.out.print("Enter publication name: ");
        String publication = scanner.next();

        System.out.print("Enter book price: ");
        int price = scanner.nextInt();
       
        Book book = new Book(bookId, name, author, publication, price);

        return book;
    }
}
