package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Concordance {
    private static final Pattern WORD_BREAK = Pattern.compile("\\W+");
    private static final Comparator<Map.Entry<String, Long>> valueOrder = Map.Entry.comparingByValue();
    private static final Comparator<Map.Entry<String, Long>> reverseOrder = valueOrder.reversed();


    public static void main(String[] args) throws IOException {
        Files.lines(Paths.get("PrideandPrejudice.txt"))
                .flatMap(WORD_BREAK::splitAsStream)
                .filter(w -> w.length() > 0)
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(reverseOrder)
                .limit(1000)
                .forEach(System.out::println);

    }
}
