package com.assignment1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generates getters, setters, equals, hashCode, and toString methods
@AllArgsConstructor // Generates constructor with all arguments
@NoArgsConstructor  // Generates default constructor
public class Car {
    private long id;
    private double price;
    private String brand;
    private String model;
    private double mileageInKM;
    private double rating;
}