package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Product Service
 *
 * Uses Spring Data JPA Repository
 * instead of manual ArrayList logic
 */

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Get all products
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Get product by ID
     */
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * Save new product
     */
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Update existing product
     */
    public Product updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setDescription(updatedProduct.getDescription());
                    product.setPrice(updatedProduct.getPrice());
                    product.setStockQuantity(updatedProduct.getStockQuantity());
                    product.setCategory(updatedProduct.getCategory());
                    return productRepository.save(product);
                })
                .orElse(null);
    }

    /**
     * Delete product
     */
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * Find products by category name
     */
    public List<Product> getProductsByCategory(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }

    /**
     * Find products within price range
     */
    public List<Product> getProductsByPriceRange(double min, double max) {
        return productRepository.findProductsByPriceRange(min, max);
    }
}
