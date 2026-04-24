package com.ws101.delaCruz.EcommerceApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    // Unique identifier
    private int id;

    // Product name
    private String name;

    // Product description
    private String description;

    // Product price
    private double price;

    // Product category
    private String category;

    // Available stock
    private int stockQuantity;

    // Optional product image
    private String imageUrl;
}
