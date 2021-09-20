package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;

public class Car {

    private static final CarCriterion RED_CAR_CRITERION = car -> car.getColor().equals("Red");
    private static final Comparator<Car> CAR_COMPARATOR = (car1, car2) -> car1.getGasLevel() - car2.getGasLevel();

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

    public static CarCriterion getGasLevelCriterion(int threshold) {
        return new GasLevelCarCriterion(threshold);
    }

    public static Car withGasColorPassengers(int gas, String color, String... passengers) {
        List<String> p = List.of(passengers);
        return new Car(gas, color, p, null);
    }

    public static Car withGasColorPassengersAndTruck(int gas, String color, String... passengers) {
        List<String> p = List.of(passengers);
        return new Car(gas, color, p, List.of("jack", "wrench", "spare wheel"));

    }

    public static List<Car> getCarsByCriterion(Iterable<Car> cars, CarCriterion carCriterion) {
        List<Car> output = new ArrayList<>();
        for (Car car : cars) {
            if (carCriterion.test(car))
                output.add(car);
        }
        return output;
    }

    public static List<Car> getCarsByGasLevel(Iterable<Car> cars, int gasLevel) {
        List<Car> output = new ArrayList<>();
        for (Car car : cars) {
            if (car.getGasLevel() >= gasLevel)
                output.add(car);
        }
        return output;
    }

    public static CarCriterion getRedCarCriterion() {
        return RED_CAR_CRITERION;
    }

    public static Comparator<Car> getCarComparator() {
        return CAR_COMPARATOR;
    }

    public static CarCriterion getFourPassengerCriterion() {
        return c -> c.getPassengers().size() == 4;
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

    @FunctionalInterface
    interface CarCriterion {
        boolean test(Car car);
    }


    private static class GasLevelCarCriterion implements CarCriterion {
        private final int threshold;

        public GasLevelCarCriterion(int threshold) {
            this.threshold = threshold;
        }

        @Override
        public boolean test(Car car) {
            return car.gasLevel >= threshold;
        }
    }


}
