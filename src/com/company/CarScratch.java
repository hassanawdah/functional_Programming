package com.company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;


public class CarScratch {

    public static <E> ToIntFunction<E> compareWithThis(E target, Comparator<E> comparator) {
        return value -> comparator.compare(target, value);
    }

    public static <E> Predicate<E> lessThanZero(ToIntFunction<E> target) {
        return value -> target.applyAsInt(value) < 0;
    }

    public static void main(String[] args) {
        List<Car> cars = Arrays.asList(
                Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"),
                Car.withGasColorPassengers(3, "Octarine", "Hassan", "Jim", "Sheila"),
                Car.withGasColorPassengers(9, "Black", "Sarah", "Jim", "Sheila"),
                Car.withGasColorPassengers(7, "Green", "Moch", "Jim", "Sheila"),
                Car.withGasColorPassengers(6, "Red", " Fred", "Jim", "Sheila")
        );
       /* showAll(cars);
        cars.sort(new PassengerCountOrder());
        showAll(cars);

        showAll(getCarsByCriterion(cars, Car.getRedCarCriterion()));
        showAll(getCarsByCriterion(cars, Car.getGasLevelCriterion(1)));
        showAll(getCarsByCriterion(cars, getColorCarCriterion("Red", "Black")));*/

    /*    Car.Criterion<Car> level7 = Car.getGasLevelCriterion(7);
        showAll(Car.getCarsByCriterion(cars, level7));
        Car.Criterion<Car> notLevel7 = negate(Car.getGasLevelCriterion(7));
        showAll(Car.getCarsByCriterion(cars, notLevel7));*/

    /*    Predicate<Car> redCar = Car.getColorCarCriterion("Red");
        Predicate<Car> fourPassengerPredicate = Car.getFourPassengerCriterion();

        Predicate<Car> or = fourPassengerPredicate.or(redCar);
        showAll(Car.getCarsByCriterion(cars, or));*/


        Car bert = Car.withGasColorPassengers(5, "Red");
        ToIntFunction<Car> compareWithBert = compareWithThis(bert, Car.getCarComparator());
        for (Car car : cars) {
            System.out.println("comparing " + car + " with bert gives " + compareWithBert.applyAsInt(car));
        }


        showAll(Car.getCarsByCriterion(cars, lessThanZero(compareWithBert)));

    }

    public static <E> void showAll(List<E> values) {
        for (E e : values) {
            System.out.println(e);
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
