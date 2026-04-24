package com.ws101.delaCruz.EcommerceApi.service;

import com.ws101.delaCruz.EcommerceApi.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for product-related operations.
 *
 * Provides business logic for managing products including creation,
 * retrieval, updating, deletion, filtering, and searching.
 * Acts as an in-memory data layer using a List<Product>.
 *
 * This class simulates database operations using Java Collections.
 *
 * @author jerome dela cruz
 * @see Product
 */
@Service
public class ProductService {

    private List<Product> productList = new ArrayList<>();
    private int nextId = 1;

    /**
     * Initializes the service with sample product data.
     * Used for testing API endpoints without a database.
     */
    public ProductService() {
        productList.add(new Product(nextId++, "Laptop", "Gaming laptop", 55000, "Electronics", 10, ""));
        productList.add(new Product(nextId++, "Mouse", "Wireless mouse", 800, "Electronics", 50, ""));
        productList.add(new Product(nextId++, "Keyboard", "Mechanical keyboard", 2500, "Electronics", 30, ""));
    }

    /**
     * Retrieves all products in the system.
     *
     * @return List of all Product objects.
     */
    public List<Product> getAllProducts() {
        return productList;
    }

    /**
     * Retrieves a product by its unique ID.
     *
     * @param id the product ID to search for
     * @return Product object if found, otherwise null
     */
    public Product getProductById(int id) {
        return productList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Adds a new product to the in-memory list.
     *
     * @param product the product to be added
     * @return the created Product with generated ID
     */
    public Product addProduct(Product product) {
        product.setId(nextId++);
        productList.add(product);
        return product;
    }

    /**
     * Updates an existing product completely.
     *
     * @param id the ID of the product to update
     * @param updatedProduct the new product data
     * @return success message or "Product not found"
     */
    public String updateProduct(int id, Product updatedProduct) {
        for (Product p : productList) {
            if (p.getId() == id) {
                p.setName(updatedProduct.getName());
                p.setDescription(updatedProduct.getDescription());
                p.setPrice(updatedProduct.getPrice());
                p.setCategory(updatedProduct.getCategory());
                p.setStockQuantity(updatedProduct.getStockQuantity());
                p.setImageUrl(updatedProduct.getImageUrl());
                return "Product updated successfully";
            }
        }
        return "Product not found";
    }

    /**
     * Deletes a product by ID.
     *
     * @param id the product ID to delete
     * @return success or failure message
     */
    public String deleteProduct(int id) {
        return productList.removeIf(p -> p.getId() == id)
                ? "Product deleted successfully"
                : "Product not found";
    }

    /**
     * Filters products by category.
     *
     * @param category the category to filter by
     * @return list of products matching the category
     */
    public List<Product> filterByCategory(String category) {
        return productList.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    /**
     * Searches products by name (partial match supported).
     *
     * @param name the name keyword to search
     * @return list of matching products
     */
    public List<Product> searchByName(String name) {
        return productList.stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Filters products by price range.
     *
     * @param min minimum price (inclusive)
     * @param max maximum price (inclusive)
     * @return list of products within price range
     * @throws IllegalArgumentException if min > max
     */
    public List<Product> filterByPrice(double min, double max) {
        if (min > max) {
            throw new IllegalArgumentException("Min price cannot be greater than max price");
        }

        return productList.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }
}
