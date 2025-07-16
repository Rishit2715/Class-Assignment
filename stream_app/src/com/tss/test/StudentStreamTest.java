package com.tss.test;

import java.util.Arrays;
import java.util.Comparator;

public class StudentStreamTest {

    public static void main(String[] args) {
        String[] names = { "Jay", "Nimesh", "Mark", "Mahesh", "Ramesh" };

        System.out.println("a. First 3 names sorted in ascending order:");
        Arrays.stream(names)
        	  .limit(3)
              .sorted()
              .forEach(System.out::println);

        System.out.println("\nb. First 3 names with 'a', sorted ascending:");
        Arrays.stream(names)
        	  .limit(3)
              .filter(name -> name.toLowerCase().contains("a"))
              .sorted()
              .forEach(System.out::println);

        System.out.println("\nc. Names sorted in descending order:");
        Arrays.stream(names)
              .sorted(Comparator.reverseOrder())
              .forEach(System.out::println);

        System.out.println("\nd. First 3 characters of each name:");
        Arrays.stream(names)
              .map(name -> name.length() > 3 ? name.substring(0, 3) : name)
              .forEach(System.out::println);

        System.out.println("\ne. Names with length <= 4:");
        Arrays.stream(names)
              .filter(name -> name.length() <= 4)
              .forEach(System.out::println);
    }
}

