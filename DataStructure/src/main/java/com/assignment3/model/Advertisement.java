package com.assignment3.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // Generates getters, setters, equals, hashCode, and toString methods
@AllArgsConstructor // Generates constructor with all arguments
public class Advertisement {
    public String id;
    public double price;
    public int height;
    public String title;
    public String content;

    // function to calculate the price-to-height ratio
    public double pricePerHeight() {
        return this.price / this.height;
    }
}