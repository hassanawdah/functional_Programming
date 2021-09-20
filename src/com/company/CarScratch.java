package com.company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static com.company.Car.getCarsByCriterion;


public class CarScratch {
    public static void main(String[] args) {
        List<Car> cars = Arrays.asList(
                Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"),
                Car.withGasColorPassengers(3, "Octarine", "Hassan", "Jim", "Sheila"),
                Car.withGasColorPassengers(9, "Black", "Sarah", "Jim", "Sheila"),
                Car.withGasColorPassengers(7, "Green", "Moch", "Jim", "Sheila"),
                Car.withGasColorPassengers(6, "Red", " Fred", "Jim", "Sheila")
        );
        showAll(cars);
        cars.sort(new PassengerCountOrder());
        showAll(cars);

        showAll(getCarsByCriterion(cars, Car.getRedCarCriterion()));
        showAll(getCarsByCriterion(cars, Car.getGasLevelCriterion(1)));
    }

    public static void showAll(List<Car> cars) {
        for (Car c : cars) {
            System.out.println(c);
        }
        System.out.println("---------------------------------------");
    }

    static class PassengerCountOrder implements Comparator<Car> {
        @Override
        public int compare(Car o1, Car o2) {
            return o1.getPassengers().size() - o2.getPassengers().size();
        }
    }
}
