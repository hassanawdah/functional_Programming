package com.company;

import java.util.stream.IntStream;

public class SumIntStream {
    public static void main(String[] args) {
        int reduce = IntStream.iterate(0, i -> i + 1)
                .limit(100)
                .reduce(50, (a, b) -> a + b);

        System.out.println(reduce);
        //  reduce.ifPresent(System.out::println);

    }
}
