package com.tss.test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ListTest {

	public static void main(String[] args) {

		List<String> names = new LinkedList<String>();

		names.add("Rishit");
		names.add("Jay");
		names.add("Kirtan");
		names.add("krish");
		names.add("harshvardhan");
		
		System.out.println(names);

		for (int i = 0; i < names.size(); i++) {
			System.out.println(names.get(i));
		}

		Scanner scanner = new Scanner(System.in);
		System.out.println("enter A NAME");
		String name1 = scanner.next();
		
		boolean found = false;
		
		Iterator<String> iterator = names.iterator();
		while (iterator.hasNext()) {
			String temp = iterator.next();
			
			if(found) {
				System.out.println(temp);
			}
			
			if(temp.equalsIgnoreCase(name1)) {
				found=true;
			}
		}
		System.out.println();

	}

}
