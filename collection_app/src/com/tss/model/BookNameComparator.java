package com.tss.model;

import java.util.Comparator;

public class BookNameComparator implements Comparator<Book> {
	@Override
	public int compare(Book o1, Book o2) {

		
		return o2.getAuthor().compareTo(o1.getAuthor());
	}
}
