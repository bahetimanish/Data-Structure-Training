package com.assignment1.main;

import com.assignment1.model.Car;
import com.assignment1.service.FindCar;

import java.util.ArrayList;
import java.util.List;

public class BestCarMain {

    public static void main(String[] args) {
        // Create a list of car objects
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(1, 25000, "Toyota", "Corolla", 50000, 4.5));
        cars.add(new Car(2, 30000, "Honda", "Civic", 40000, 4.7));
        cars.add(new Car(3, 15000, "Kia", "Sonet", 60000, 4.0));
        cars.add(new Car(4, 28000, "Maruti", "Brezza", 35000, 4.8));
        cars.add(new Car(5, 32000, "Kia", "Seltos", 30000, 4.6));

        // Create an instance of the OptimizedCarFinder class
        FindCar optimizedCarFinder = new FindCar(cars);

        // Find cars within a specified price range
        List<Car> affordableCars = optimizedCarFinder.findCarsByPriceRange(20000, 30000);
        System.out.println("Cars in price range 20000 - 30000:");
        affordableCars.forEach(System.out::println);

        // Find the best-rated car within a price range
        Car bestRatedCar = optimizedCarFinder.findBestRatedCarInPriceRange(20000, 30000);
        System.out.println("\nBest-rated car in price range 20000 - 30000:");
        System.out.println(bestRatedCar);

        // Find cars by combining multiple filters (price, brand, mileage, and rating)
        List<Car> filteredCars = optimizedCarFinder.findCars(10000, 40000, "Kia", 30000, 60000, 4.0);
        System.out.println("\nFiltered cars (Kia, price 20000 - 30000, mileage 30000 - 60000, rating >= 4.0):");
        filteredCars.forEach(System.out::println);
    }
}