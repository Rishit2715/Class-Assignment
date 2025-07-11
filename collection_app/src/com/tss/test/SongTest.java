package com.tss.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.tss.model.Song;

public class SongTest {

	public static void main(String[] args) {
		List<Song> playlist = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		int choice;

		do {
			System.out.println("\nMusic Playlist Manager");
			System.out.println("1. Add Song");
			System.out.println("2. Remove Song");
			System.out.println("3. Search by Title");
			System.out.println("4. Display All Songs");
			System.out.println("5. Shuffle Playlist");
			System.out.println("6. Exit");
			System.out.print("Enter your choice: ");

			while (!scanner.hasNextInt()) {
				System.out.print("Invalid input. Enter a number: ");
				scanner.next();
			}

			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				addSong(scanner, playlist);
				break;
			case 2:
				removeSong(scanner, playlist);
				break;
			case 3:
				searchSong(scanner, playlist);
				break;
			case 4:
				displaySongs(playlist);
				break;
			case 5:
				shuffleSongs(playlist);
				break;
			case 6:
				System.out.println("Exiting... Thank you!");
				break;
			default:
				System.out.println("❗ Invalid choice.");
			}
		} while (choice != 6);

		scanner.close();
	}

	private static void addSong(Scanner scanner, List<Song> playlist) {
		System.out.print("Enter title: ");
		String title = scanner.nextLine();
		System.out.print("Enter artist: ");
		String artist = scanner.nextLine();
		System.out.print("Enter duration (mins): ");

		Song newSong = new Song(title, artist, 0);

		if (playlist.contains(newSong)) {
			System.out.println("Song already exists.");
			return;
		}

		while (!scanner.hasNextDouble()) {
			System.out.print("Invalid. Enter a number: ");
			scanner.next();
		}
		double duration = scanner.nextDouble();
		scanner.nextLine();

		newSong = new Song(title, artist, duration);
		playlist.add(newSong);

		System.out.println("Song added!");
	}

	private static void removeSong(Scanner scanner, List<Song> playlist) {
		if (!playlist.isEmpty()) {

			System.out.print("Enter title: ");
			String title = scanner.nextLine();
			System.out.print("Enter artist: ");
			String artist = scanner.nextLine();

			Song temp = new Song(title, artist, 0);
			if (playlist.remove(temp)) {
				System.out.println("Song removed.");
			} else {
				System.out.println("Song not found.");
			}
			return;
		}
		System.out.println("playlist is empty.");
	}

	private static void searchSong(Scanner scanner, List<Song> playlist) {
		System.out.print("Enter title to search: ");
		String title = scanner.nextLine();
		boolean found = false;
		if (!playlist.isEmpty()) {

			for (Song song : playlist) {
				if (song.getTitle().equalsIgnoreCase(title)) {
					System.out.println(song);
					found = true;
				}
			}

			if (!found) {
				System.out.println("No song found with title: " + title);
			}
			return;
		}
		System.out.println("playlist is empty.");

	}

	private static void displaySongs(List<Song> playlist) {
		if (!playlist.isEmpty()) {
			System.out.println("Playlist:");
			for (Song song : playlist) {
				System.out.println(song);
			}
			return;
		}
		System.out.println("playlist is empty.");
	}

	private static void shuffleSongs(List<Song> playlist) {
		if (!playlist.isEmpty()) {
			Collections.shuffle(playlist);
			System.out.println("Playlist shuffled.");
			return;
		}
		System.out.println("Shuffle not possible playlist is empty.");

	}
}
