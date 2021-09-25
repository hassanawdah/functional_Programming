package com.company;

import java.util.*;

public class StreamVersion {
    public static final String OWNER = "Hassan";

    public static void println() {
        System.out.println("-------------------------------------------------------------------------------------");

    }

    public static void main(String[] args) {

        List<String> strings = Arrays.asList("Ahamd", "ddsfsd", "Cdfsfsfs", "sdfDdff");
        strings.forEach(System.out::println);
        println();
        strings.stream().filter(s -> Character.isUpperCase(s.charAt(0))).forEach(System.out::println);
        strings.stream().map(String::toUpperCase).forEach(System.out::println);


        println();
        List<Car> carIter = new ArrayList<>(
                Arrays.asList(
                        Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"),
                        Car.withGasColorPassengers(3, "Octarine", "Hassan", "Jim", "Sheila"),
                        Car.withGasColorPassengers(9, "Black", "Sarah", "Jim", "Sheila"),
                        Car.withGasColorPassengers(7, "Green", "Moch", "Jim", "Sheila"),
                        Car.withGasColorPassengers(6, "Red", " Fred", "Jim", "Sheila")
                )
        );

        carIter.stream().map(c -> c.addGas(10)).filter(c -> c.getGasLevel() > 10).forEach(c -> System.out.println("> " + c));
        println();
        carIter.
                stream().
                filter(car -> car.getPassengers().size() >= 3)
                .flatMap(car -> car.getPassengers().stream())
                .map(String::toUpperCase)
                .forEach(System.out::println);

        System.out.println("--------------------------------------------------------------------------");

        carIter.stream()
                .flatMap(car -> car.getPassengers().stream()
                        .map(p -> p + " is riding in a " + car.getColor() + " car"))
                .forEach(System.out::println);


        Map<String, Car> carMap = new HashMap<>();
        carMap.put("Hassan", Car.withGasColorPassengersAndTruck(9, "Red", "kaleed", "Abdullah"));
        carMap.put("Ahmad", Car.withGasColorPassengersAndTruck(9, "Red", "kaleed", "Abdullah"));
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("--------------------------------------------------------------------------");
        Optional<Map<String, Car>> optionalMap = Optional.of(carMap);
        optionalMap.map(m -> m.get(OWNER))
                .map(Car::getTrunkContents)
                .map(x -> OWNER + " Has " + x + " in the car ")
                .ifPresent(System.out::println);
    }


}
