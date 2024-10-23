package com.assignment1.service;

import com.assignment1.model.Car;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * To find the suitable car in the most efficient manner for the customer
 */
public class FindCar {
    private List<Car> carsByPrice;
    private List<Car> carsByMileage;
    private Map<String, List<Car>> carsByBrand;

    // Constructor initializes and preprocesses the car list
    public FindCar(List<Car> cars) {
        // Sorting cars by price
        this.carsByPrice = new ArrayList<>(cars);
        this.carsByPrice.sort(Comparator.comparingDouble(Car::getPrice));

        // Sorting cars by mileage
        this.carsByMileage = new ArrayList<>(cars);
        this.carsByMileage.sort(Comparator.comparingDouble(Car::getMileageInKM));

        // Creating a HashMap for fast lookup of cars by brand
        this.carsByBrand = new HashMap<>();
        for (Car car : cars) {
            carsByBrand.computeIfAbsent(car.getBrand().toLowerCase(), k -> new ArrayList<>()).add(car);
        }
    }

    /**
     * method to perform binary search on sorted lists (price or mileage)
     *
     * @param sortedCars
     * @param minValue
     * @param maxValue
     * @param comparator
     * @param getter
     * @return List<Car>
     * K is number of cars within the range
     * Time Complexity  Space Complexity
     *  O(log N + K)             O(K)
     *
     */
    private List<Car> findCarsByRange(List<Car> sortedCars, double minValue, double maxValue,
                                      Comparator<Car> comparator, Function<Car, Double> getter) {
        // Finding the index for the starting point using binary search
        int startIndex = Collections.binarySearch(sortedCars, new Car(0, minValue, "", "", 0, 0), Comparator.comparing(getter));
        int endIndex = Collections.binarySearch(sortedCars, new Car(0, maxValue, "", "", 0, 0), Comparator.comparing(getter));

        // If exact value not found, binary search returns a negative index; convert it to a valid insertion point
        if (startIndex < 0) startIndex = -(startIndex + 1);
        if (endIndex < 0) endIndex = -(endIndex + 1);

        // Return the sublist within the found range
        return sortedCars.subList(Math.max(0, startIndex), Math.min(endIndex + 1, sortedCars.size()));
    }


    /**
     * Method to find cars by price range using binary search
     * @param minPrice
     * @param maxPrice
     * @return List<Car>
     * K is number of cars within the range
     * Time Complexity  Space Complexity
     * O(log N + K)             O(K)
     */
    public List<Car> findCarsByPriceRange(double minPrice, double maxPrice) {
        return findCarsByRange(carsByPrice, minPrice, maxPrice, Comparator.comparingDouble(Car::getPrice), Car::getPrice);
    }

    /**
     * Method to find cars by mileage range using binary search
     * @param minMileage
     * @param maxMileage
     * @return List<Car>
     * K is number of cars within the range
     * Time Complexity  Space Complexity
     * O(log N + K)             O(K)
     */
    public List<Car> findCarsByMileageRange(double minMileage, double maxMileage) {
        return findCarsByRange(carsByMileage, minMileage, maxMileage, Comparator.comparingDouble(Car::getMileageInKM), Car::getMileageInKM);
    }

    /**
     * Method to find cars by brand using the pre-built HashMap
     * @param brand
     * @return List<Car>
     * Time Complexity  Space Complexity
     * O(1)             O(1)
     */
    public List<Car> findCarsByBrand(String brand) {
        return carsByBrand.getOrDefault(brand.toLowerCase(), new ArrayList<>());
    }


    /**
     * Method to find the best-rated car in a given price range
     * @param minPrice
     * @param maxPrice
     * @return Car
     * K is number of cars within the range
     * Time Complexity  Space Complexity
     * O(log N + K)             O(K)
     */
    public Car findBestRatedCarInPriceRange(double minPrice, double maxPrice) {
        // First, get cars within the price range
        List<Car> carsInRange = findCarsByPriceRange(minPrice, maxPrice);
        // Then, find the car with the highest rating
        return carsInRange.stream()
                .max(Comparator.comparingDouble(Car::getRating))
                .orElse(null);  // Return null if no cars are found
    }


    /**
     * Method to find cars by multiple filters: price range, brand, mileage range, and minimum rating
     * @param minPrice
     * @param maxPrice
     * @param brand
     * @param minMileage
     * @param maxMileage
     * @param minRating
     * @return List<Car>
     * K is number of cars within the range
     * Time Complexity  Space Complexity
     * O(log N + K)             O(K)
     */
    public List<Car> findCars(double minPrice, double maxPrice, String brand, double minMileage, double maxMileage, double minRating) {
        // Start by finding cars within the price range
        List<Car> filteredCars = findCarsByPriceRange(minPrice, maxPrice);

        // If a brand is specified, filter further by brand
        if (brand != null && !brand.isEmpty()) {
            List<Car> brandCars = findCarsByBrand(brand);
            filteredCars.retainAll(brandCars); // Keep only cars in both lists (intersection)
        }

        // Apply additional filters: mileage and rating
        filteredCars = filteredCars.stream()
                .filter(car -> car.getMileageInKM() >= minMileage && car.getMileageInKM() <= maxMileage)
                .filter(car -> car.getRating() >= minRating)
                .collect(Collectors.toList());

        return filteredCars;
    }
}