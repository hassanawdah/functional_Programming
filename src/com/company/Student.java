package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Student {
    private static final NavigableMap<Integer, String> gradeLetters = new TreeMap<>();

    static {
        gradeLetters.put(90, "A");
        gradeLetters.put(80, "B");
        gradeLetters.put(70, "C");
        gradeLetters.put(60, "D");
        gradeLetters.put(50, "E");
        gradeLetters.put(0, "F");
    }

    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public static void main(String[] args) {
        List<Student> school = new ArrayList<>(Arrays.asList(
                new Student("Fred1", 71),
                new Student("Fred2", 89),
                new Student("Fred4", 90),
                new Student("Fred5", 60),
                new Student("Fred6", 54),
                new Student("Fred7", 0),
                new Student("Fred8", 10)
        ));
        school.forEach(System.out::println);
        Map<String, List<Student>> collect = school.stream().collect(Collectors.groupingBy(Student::getLetterGrade));
        collect.forEach((key, value) -> {
            System.out.println(key + "  " + value);
        });

        Map<String, Long> table2 = school.stream().collect(Collectors.groupingBy(Student::getLetterGrade, Collectors.counting()));
        table2.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(
                entry -> System.out.println(entry.getValue() + " Students got grade " + entry.getKey())
        );

    }

    public String getLetterGrade() {
        return gradeLetters.floorEntry(score).getValue();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Student.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("score=" + score)
                .toString();
    }
}
