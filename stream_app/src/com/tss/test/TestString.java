package com.tss.test;

import java.util.*;
import java.util.stream.Collectors;

public class TestString {
    public static void main(String[] args) {
        List<String> words = Arrays.asList(
                "apple", "banana", "apricot", "bluberry", "cherry"
        );

//        Map<Character, List<String>> grouped = words.stream()
//            .collect(Collectors.groupingBy(s -> s.charAt(0)));
//
//        System.out.println("== Grouped by starting character ==");
//        grouped.forEach((ch, list) -> System.out.println(ch + " -> " + list));

        
        
        Map<Character, Long> countMap = words.stream()
            .collect(Collectors.groupingBy(s -> s.charAt(0), Collectors.counting()));

        System.out.println("\n== Count of words by starting character ==");
        countMap.forEach((ch, count) -> System.out.println(ch + " -> " + count));
    }
}
