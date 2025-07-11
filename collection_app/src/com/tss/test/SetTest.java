package com.tss.test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {
	public static void main(String args[]) {
		
		
		
		Set<Integer> s = new HashSet<>();
		
		s.add(10);
		s.add(20);
		s.add(40);
		s.add(30);
		s.add(60);
		System.out.println(s.getClass());

		
		System.out.println(s);
		
Set<Integer> s1 = new LinkedHashSet<>();
		
		s1.add(10);
		s1.add(20);
		s1.add(40);
		s1.add(30);
		s1.add(60);

		
		System.out.println(s1);
		
Set<Integer> s2 = new TreeSet<>();
		
		s2.add(10);
		s2.add(20);
		s2.add(40);
		s2.add(30);
		s2.add(60);

		
		System.out.println(s2);
		
	}

}
