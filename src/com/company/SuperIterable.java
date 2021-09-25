package com.company;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {
    public static final String OWNER = "Hassan";
    private Iterable<E> self;

    public SuperIterable(Iterable<E> self) {
        this.self = self;
    }

    public static void main(String[] args) {
        SuperIterable<String> strings = new SuperIterable<>(Arrays.asList("Ahamd", "ddsfsd", "Cdfsfsfs", "sdfDdff"));
        strings.forEach(System.out::println);
        System.out.println("-------------------------------------------------------------------------------------");

        strings.filter(s -> Character.isUpperCase(s.charAt(0))).forEach(System.out::println);

        System.out.println("-------------------------------------------------------------------------------------");

        strings.map(String::toUpperCase).forEach(System.out::println);

        System.out.println("---------------------------------------------------------------------------------------");
        SuperIterable<Car> carIter = new SuperIterable<>(
                Arrays.asList(
                        Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"),
                        Car.withGasColorPassengers(3, "Octarine", "Hassan", "Jim", "Sheila"),
                        Car.withGasColorPassengers(9, "Black", "Sarah", "Jim", "Sheila"),
                        Car.withGasColorPassengers(7, "Green", "Moch", "Jim", "Sheila"),
                        Car.withGasColorPassengers(6, "Red", " Fred", "Jim", "Sheila")
                )
        );

        carIter.map(c -> c.addGas(10)).filter(c -> c.getGasLevel() > 10).forEach(c -> System.out.println("> " + c));
        System.out.println("--------------------------------------------------------------------------");

        carIter.
                filter(car -> car.getPassengers().size() >= 3)
                .flatMap(car -> new SuperIterable<>(car.getPassengers()))
                .map(String::toUpperCase)
                .forEach(System.out::println);

        System.out.println("--------------------------------------------------------------------------");

        carIter
                .flatMap(car -> new SuperIterable<>(car.getPassengers())
                        .map(p -> p + " is riding in a " + car.getColor()))
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


    public <F> SuperIterable<F> flatMap(Function<E, SuperIterable<F>> operation) {
        List<F> results = new ArrayList<>();
        self.forEach(e -> operation.apply(e).forEach(results::add));
        return new SuperIterable<>(results);
    }

    @Override
    public Iterator<E> iterator() {
        return self.iterator();
    }

    public <F> SuperIterable<F> map(Function<E, F> operation) {
        List<F> results = new ArrayList<>();

        self.forEach(e -> results.add(operation.apply(e)));

        return new SuperIterable<>(results);
    }

    public SuperIterable<E> filter(Predicate<E> predicate) {
        List<E> result = new ArrayList<>();
        self.forEach(e -> {
            if (predicate.test(e)) {
                result.add(e);
            }
        });
        return new SuperIterable<>(result);
    }
}
