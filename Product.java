package com.ws101.delaCruz.EcommerceApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;

/*
    TASK 6.2 - Request Validation Rules
*/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private int id;

    // Product name: required + min length
    @NotBlank(message = "Product name is required")
    @Size(min = 3, message = "Product name must be at least 3 characters")
    private String name;

    private String description;

    // Price must be positive
    @Positive(message = "Price must be greater than 0")
    private double price;

    // Category required
    @NotBlank(message = "Category is required")
    private String category;

    // Stock must not be negative
    @Min(value = 0, message = "Stock quantity cannot be negative")
    private int stockQuantity;

    private String imageUrl;
}
