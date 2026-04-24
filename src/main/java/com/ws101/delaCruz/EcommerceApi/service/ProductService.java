package com.ws101.delaCruz.EcommerceApi.service;

import com.ws101.delaCruz.EcommerceApi.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
    ProductService - In-Memory Data Storage

    This service class manages product data without using a database.
    Instead of persistent storage, we use a List<Product> to store data temporarily in memory.

    Features:
    - Stores products in an ArrayList
    - Uses manual ID generation (counter-based)
    - Provides CRUD operations (Create, Read, Update, Delete)
    - Supports filtering (category, price, name)

    Note:
    Data will be lost once the application stops since no database is used.
*/

@Service
public class ProductService {

    // =========================
    // In-memory storage
    // =========================
    private List<Product> productList = new ArrayList<>();

    // =========================
    // ID Generation Strategy
    // =========================
    private int nextId = 1;

    // =========================
    // Constructor - Sample Data (10 products)
    // =========================
    public ProductService() {
        productList.add(new Product(nextId++, "Laptop", "Gaming laptop", 55000, "Electronics", 10, ""));
        productList.add(new Product(nextId++, "Mouse", "Wireless mouse", 800, "Electronics", 50, ""));
        productList.add(new Product(nextId++, "Keyboard", "Mechanical keyboard", 2500, "Electronics", 30, ""));
        productList.add(new Product(nextId++, "Phone", "Android smartphone", 15000, "Electronics", 15, ""));
        productList.add(new Product(nextId++, "Shoes", "Running shoes", 3000, "Fashion", 20, ""));
        productList.add(new Product(nextId++, "T-Shirt", "Cotton shirt", 500, "Fashion", 100, ""));
        productList.add(new Product(nextId++, "Bag", "Backpack", 900, "Accessories", 25, ""));
        productList.add(new Product(nextId++, "Watch", "Digital watch", 2000, "Accessories", 18, ""));
        productList.add(new Product(nextId++, "Book", "Programming book", 1200, "Education", 40, ""));
        productList.add(new Product(nextId++, "Headset", "Gaming headset", 1800, "Electronics", 22, ""));
    }

    // =========================
    // CRUD OPERATIONS
    // =========================

    // Retrieve all products
    public List<Product> getAllProducts() {
        return productList;
    }

    // Find product by ID
    public Product getProductById(int id) {
        return productList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Create new product
    public Product addProduct(Product product) {
        product.setId(nextId++);
        productList.add(product);
        return product;
    }

    // Update existing product
    public String updateProduct(int id, Product updatedProduct) {
        for (Product product : productList) {
            if (product.getId() == id) {
                product.setName(updatedProduct.getName());
                product.setDescription(updatedProduct.getDescription());
                product.setPrice(updatedProduct.getPrice());
                product.setCategory(updatedProduct.getCategory());
                product.setStockQuantity(updatedProduct.getStockQuantity());
                product.setImageUrl(updatedProduct.getImageUrl());
                return "Product updated successfully";
            }
        }
        return "Product not found";
    }

    // Delete product
    public String deleteProduct(int id) {
        return productList.removeIf(product -> product.getId() == id)
                ? "Product deleted successfully"
                : "Product not found";
    }

    // =========================
    // FILTERING METHODS
    // =========================

    // Filter by category
    public List<Product> filterByCategory(String category) {
        return productList.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // Filter by price range
    public List<Product> filterByPrice(double min, double max) {
        return productList.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }

    // Search by name
    public List<Product> searchByName(String name) {
        return productList.stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
}
