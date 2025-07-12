package com.tss.test;

import com.tss.exception.*;
import com.tss.service.MovieService;

import java.util.Scanner;

public class MovieTest {

    public static void main(String[] args) {
        MovieService service = new MovieService();
        try (Scanner scanner = new Scanner(System.in)) {
            int choice;
            do {
                printMenu();
                choice = getIntInput(scanner, "Enter your choice: ");

                try {
                    handleChoice(choice, scanner, service);
                } catch (MovieEmptyException | CapacityFullException |
                         NoSuchMovieFoundException | DuplicateMovieException |
                         IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }

            } while (choice != 6);

            service.saveMovies();
            System.out.println("Exiting. Movie data saved.");
        }
    }

    private static void printMenu() {
        System.out.println("\nWelcome to Movie Store");
        System.out.println("1. Display movies");
        System.out.println("2. Add movie");
        System.out.println("3. Delete movie by ID");
        System.out.println("4. Clear all");
        System.out.println("5. Search movie by ID");
        System.out.println("6. Exit");
    }

    private static int getIntInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void handleChoice(int choice, Scanner scanner, MovieService service) {
        scanner.nextLine(); // flush newline

        switch (choice) {
            case 1 -> service.displayMovies();

            case 2 -> {
                System.out.print("Enter movie name: ");
                String name = scanner.nextLine();
                String genre = getValidGenre(scanner);
                int year = getValidYear(scanner);
                service.addMovie(name, genre, year);
                service.saveMovies();
            }

            case 3 -> {
                if (service.getMovies().isEmpty())
                    throw new MovieEmptyException("Movie list is empty.");

                System.out.print("Enter movie ID to delete: ");
                String id = scanner.nextLine();
                service.deleteMovieById(id);
                service.saveMovies();
            }

            case 4 -> {
                System.out.print("Are you sure you want to delete all movies? (yes/no): ");
                String confirm = scanner.nextLine().trim().toLowerCase();
                if (confirm.equals("yes")) {
                    service.clearAll();
                    service.saveMovies();
                } else {
                    System.out.println("Clear cancelled.");
                }
            }

            case 5 -> {
                if (service.getMovies().isEmpty())
                    throw new MovieEmptyException("Movie list is empty.");

                System.out.print("Enter movie ID to search: ");
                String searchId = scanner.nextLine();
                service.searchMovieById(searchId);
            }

            case 6 -> System.out.println("Thank you for using Movie Store.");

            default -> System.out.println("Invalid choice. Try again.");
        }
    }

    private static int getValidYear(Scanner scanner) {
        while (true) {
            System.out.print("Enter year (4 digits): ");
            String input = scanner.nextLine();
            try {
                int year = Integer.parseInt(input);
                if (year >= 1000 && year <= 9999) return year;
                System.out.println("Year must be a 4-digit number between 1000 and 9999.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid 4-digit number.");
            }
        }
    }

    private static String getValidGenre(Scanner scanner) {
        while (true) {
            System.out.print("Enter genre (letters only): ");
            String genre = scanner.nextLine().trim();
            if (genre.matches("[a-zA-Z ]+")) return genre;
            System.out.println("Genre should contain only letters. No numbers or special characters allowed.");
        }
    }
}
