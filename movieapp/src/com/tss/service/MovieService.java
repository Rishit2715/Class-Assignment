package com.tss.service;

import com.tss.exception.*;
import com.tss.model.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MovieService {
    private static final int MAX_MOVIES = 5;
    private static final String FILE_NAME = "output.txt";
    private List<Movie> movieList;

    public MovieService() {
        this.movieList = loadMovies();
    }

    public List<Movie> getMovies() {
        return movieList;
    }

    public void displayMovies() {
        if (movieList.isEmpty()) throw new MovieEmptyException("Movie list is empty.");
        printHeader();
        movieList.forEach(this::printRow);
        printFooter();
    }

    public void addMovie(String name, String genre, int year) {
        if (movieList.size() >= MAX_MOVIES) {
            throw new CapacityFullException("No space for storing movies. Max limit is " + MAX_MOVIES);
        }

        if (!genre.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Genre must contain only letters.");
        }

        if (year < 1000 || year > 9999) {
            throw new IllegalArgumentException("Year must be a 4-digit number.");
        }

        String id = generateMovieId(name, genre, year);

        if (movieExists(id)) {
            throw new DuplicateMovieException("Duplicate ID: " + id + ". Movie already exists.");
        }

        movieList.add(new Movie(id, name, genre, year));
        System.out.println("Movie added with ID: " + id);
    }

    public void deleteMovieById(String id) {
        if (movieList.isEmpty()) throw new MovieEmptyException("Movie list is empty.");

        boolean removed = movieList.removeIf(m -> m.getId().equalsIgnoreCase(id.trim()));
        if (!removed) {
            throw new NoSuchMovieFoundException("Movie with ID " + id + " not found.");
        }

        System.out.println("Movie with ID " + id + " deleted.");
    }

    public void clearAll() {
        if (movieList.isEmpty()) {
            throw new MovieEmptyException("Movie list is already empty.");
        }
        movieList.clear();
        System.out.println("All movies cleared.");
    }

    public void searchMovieById(String id) {
        for (Movie m : movieList) {
            if (m.getId().equalsIgnoreCase(id.trim())) {
                printHeader();
                printRow(m);
                printFooter();
                return;
            }
        }
        throw new NoSuchMovieFoundException("Movie with ID " + id + " does not exist.");
    }

    public void saveMovies() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(movieList);
        } catch (IOException e) {
            System.out.println("Error saving movies: " + e.getMessage());
        }
    }

    private List<Movie> loadMovies() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Movie>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading movies: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Helpers
    private String generateMovieId(String name, String genre, int year) {
        String cleanName = name.replaceAll("\\s+", "");
        String cleanGenre = genre.replaceAll("\\s+", "");
        return cleanName.substring(0, Math.min(2, cleanName.length())).toUpperCase()
                + cleanGenre.substring(0, Math.min(2, cleanGenre.length())).toUpperCase()
                + String.format("%02d", year % 100);
    }

    private boolean movieExists(String id) {
        return movieList.stream().anyMatch(m -> m.getId().equalsIgnoreCase(id));
    }

    private void printHeader() {
        System.out.println("\n+------------+----------------------+-----------------+------+\n"
                + "| Movie ID   | Name                 | Genre           | Year |\n"
                + "+------------+----------------------+-----------------+------+" );
    }

    private void printRow(Movie m) {
        System.out.printf("| %-10s | %-20s | %-15s | %-4d |\n",
                m.getId(), m.getName(), m.getGenre(), m.getYear());
    }

    private void printFooter() {
        System.out.println("+------------+----------------------+-----------------+------+\n");
    }
}
