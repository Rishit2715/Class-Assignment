package com.tss.test;

import com.tss.exception.MovieEmptyException;
import com.tss.exception.MovieFullException;
import com.tss.model.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieTest {

    public static void main(String[] args) {
        int MAX_MOVIES = 5;
        String FILE_NAME = "output.txt";

        ArrayList<Movie> movieList = loadMovies(FILE_NAME);
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Display movies");
            System.out.println("2. Add movie");
            System.out.println("3. Delete movie by ID: ");
            System.out.println("4. Clear all");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            try {
                switch (choice) {
                    case 1:
                        displayMovies(movieList);
                        break;
                    case 2:
                        addMovie(scanner, movieList, MAX_MOVIES);
                        break;
                    case 3:
                        deleteMovie(scanner, movieList);
                        break;
                    case 4:
                        clearAll(movieList);
                        break;
                    case 5:
                        saveMovies(movieList, FILE_NAME);
                        System.out.println("Exiting. State saved.");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (MovieFullException | MovieEmptyException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void displayMovies(ArrayList<Movie> movieList) throws MovieEmptyException  {
        if (movieList.isEmpty()) {
            throw new MovieEmptyException("Movie list is empty.");
        }
        System.out.println("\nMovies in Store");
        for (int i = 0; i < movieList.size(); i++) {
            System.out.println((i + 1) + ". " + movieList);
        }
    }

    private static void addMovie(Scanner scanner, ArrayList<Movie> movieList, int maxMovies) throws MovieFullException {
        if (movieList.size() >= maxMovies) {
            throw new MovieFullException("Movie store already full.");
        }

        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        String yearLast2 = String.format("%02d", year % 100);
        String id = name.substring(0, 2).toUpperCase() + genre.substring(0, 2).toUpperCase() + yearLast2;

        Movie movie = new Movie(id, name, genre, year);
        movieList.add(movie);
        System.out.println("Movie added successfully with ID: " + id);
    }

    private static void deleteMovie(Scanner scanner, ArrayList<Movie> movieList) throws MovieEmptyException {
        if (movieList.isEmpty()) {
            throw new MovieEmptyException("Cannot delete. Movie list is empty.");
        }

        displayMovies(movieList);
        System.out.print("Enter movie ID to delete: ");
        String idToDelete = scanner.nextLine().trim();

        boolean found = false;
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getId().equalsIgnoreCase(idToDelete)) {
                Movie removed = movieList.remove(i);
                System.out.println("Deleted movie: " + removed.getName());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No movie found with ID: " + idToDelete);
        }
    }

    private static void clearAll(ArrayList<Movie> movieList) throws MovieEmptyException {
        if (movieList.isEmpty()) {
            throw new MovieEmptyException("Store is already empty.");
        }
        movieList.clear();
        System.out.println("All movies cleared.");
    }

    private static void saveMovies(ArrayList<Movie> movieList, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(movieList);
        } catch (IOException e) {
            System.out.println("Error saving movies: " + e.getMessage());
        }
    }

    private static ArrayList<Movie> loadMovies(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<Movie>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading saved movies: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
