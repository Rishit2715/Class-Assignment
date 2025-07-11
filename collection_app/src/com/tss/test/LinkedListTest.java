package com.tss.test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListTest {

 
	    public static void main(String[] args) {

	        LinkedList<String> names = new LinkedList<String>();

	        names.add("Rishit");
	        names.add("Jay");
	        names.add("Kirtan");
	        names.add("Kirtan");

	        System.out.println("Initial list: " + names);

	        names.remove(3);
	        System.out.println("After removing index 3: " + names);

	        names.set(2, "hello");
	        System.out.println("After setting index 1 to 'hello': " + names);

	        System.out.println("\nUsing index-based for loop:");
	        for (int i = 0; i < names.size(); i++) {
	            System.out.println(names.get(i));
	        }

	        System.out.println("\nUsing for-each loop:");
	        for (String name : names) {
	            System.out.println(name);
	        }
	        System.out.println("\nUsing Iterator:");
	        Iterator<String> iterator = names.iterator();
	        while (iterator.hasNext()) {
	            System.out.println(iterator.next());
	        }

	        System.out.println("\nUsing ListIterator forward:");
	        ListIterator<String> listIterator = names.listIterator();
	        while (listIterator.hasNext()) {
	            System.out.println(listIterator.next());
	        }

	        System.out.println("\nUsing ListIterator backward:");
	        while (listIterator.hasPrevious()) {
	            System.out.println(listIterator.previous());
	        }
	    }
	

}
