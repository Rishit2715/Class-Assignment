package com.tss.test;

import java.util.*;

import java.util.*;
import java.util.stream.*;

public class UniqueWords {

    public static void main(String[] args) {
        List<String> sentences = Arrays.asList(
            "Hello world",
            "hello Java",
            "Stream API"
        );

        List<String> uniqueWords = extractUniqueSortedWords(sentences);

        System.out.println(uniqueWords);
    }

    public static List<String> extractUniqueSortedWords(List<String> sentences) {
        return sentences.stream()
            .flatMap(s -> Arrays.stream(s.toLowerCase().split("\\W+")))
            .filter(token -> !token.isEmpty())
            .distinct()
            .sorted()
            .toList();
    }
}
