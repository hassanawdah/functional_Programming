package com.company;

import java.util.*;
import java.util.function.Predicate;

public class Car {

    private static final Predicate<Car> RED_CAR_PREDICATE = car -> car.getColor().equals("Red");
    private static final Comparator<Car> CAR_COMPARATOR = Comparator.comparingInt(Car::getGasLevel);

    private final int gasLevel;
    private final String color;
    private final List<String> passengers;
    private final List<String> trunkContents;

    public Car(int levelGas, String color, List<String> passengers, List<String> trunkContents) {
        this.gasLevel = levelGas;
        this.color = color;
        this.passengers = passengers;
        this.trunkContents = trunkContents;
    }


    public static Car withGasColorPassengers(int gas, String color, String... passengers) {
        List<String> p = List.of(passengers);
        return new Car(gas, color, p, null);
    }

    public static Car withGasColorPassengersAndTruck(int gas, String color, String... passengers) {
        List<String> p = List.of(passengers);
        return new Car(gas, color, p, List.of("jack", "wrench", "spare wheel"));

    }

    public static <E> List<E> getCarsByCriterion(Iterable<E> cars, Predicate<E> predicate) {
        List<E> output = new ArrayList<>();
        for (E e : cars) {
            if (predicate.test(e))
                output.add(e);
        }
        return output;
    }

    public static <E> List<E> getCarsByGasLevel(Iterable<E> values, Predicate<E> predicate) {
        List<E> output = new ArrayList<>();
        for (E e : values) {
            if (predicate.test(e))
                output.add(e);
        }
        return output;
    }

    public static Predicate<Car> getRedCarCriterion() {
        return RED_CAR_PREDICATE;
    }

    public static Comparator<Car> getCarComparator() {
        return CAR_COMPARATOR;
    }

    public static Predicate<Car> getFourPassengerCriterion() {
        return c -> c.getPassengers().size() == 4;
    }

    public static Predicate<Car> getGasLevelCriterion(final int threshold) {
        return (Car car) -> car.gasLevel >= threshold;
    }

    public static Predicate<Car> getColorCarCriterion(String... colors) {
        Set<String> colorSet = new HashSet<>(Arrays.asList(colors));
        return car -> colorSet.contains(car.color);
    }

    public Car addGas(int g) {
        return new Car(gasLevel + g, color, passengers, trunkContents);
    }

    public List<String> getPassengers() {
        return passengers;
    }

    public List<String> getTrunkContents() {
        return trunkContents;
    }

    public int getGasLevel() {
        return gasLevel;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
                .add("levelGas=" + gasLevel)
                .add("color='" + color + "'")
                .add("passengers=" + passengers)
                .add("trunkContents=" + (trunkContents == null ? "no trucks" : trunkContents.toString()))
                .toString();
    }


}
