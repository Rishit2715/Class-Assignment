package com.tss.model;

import java.util.Objects;

public class Song {
	private String title;
	private String artist;
	private double duration; 

	public Song(String title, String artist, double duration) {
		this.title = title;
		this.artist = artist;
		this.duration = duration;
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public double getDuration() {
		return duration;
	}

	@Override
	public String toString() {
		return  title + " by " + artist + " (" + duration + " mins)";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Song))
			return false;
		Song other = (Song) obj;
		return this.title.equalsIgnoreCase(other.title) && this.artist.equalsIgnoreCase(other.artist);
	}

}
