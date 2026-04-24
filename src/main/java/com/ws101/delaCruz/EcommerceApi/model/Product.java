package com.ws101.delaCruz.EcommerceApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    Product Model
    This class represents the product entity in our e-commerce system.
*/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    // Unique identifier of the product
    private int id;

    // Product name
    private String name;

    // Product description
    private String description;

    // Product price
    private double price;

    // Product category
    private String category;

    // Available stock quantity
    private int stockQuantity;

    // Optional image URL of the product
    private String imageUrl;
}
