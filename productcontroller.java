package com.example.inventory.controller;

import com.example.inventory.entity.Product;
import com.example.inventory.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // GET all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // GET product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);

        if (product == null) {
            throw new EntityNotFoundException("Product not found with ID: " + id);
        }

        return product;
    }

    // POST create new product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    // PUT update product
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product existingProduct = productService.getProductById(id);

        if (existingProduct == null) {
            throw new EntityNotFoundException("Product not found with ID: " + id);
        }

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setStock(updatedProduct.getStock());
        existingProduct.setCategory(updatedProduct.getCategory());

        return productService.saveProduct(existingProduct);
    }

    // DELETE product
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Product existingProduct = productService.getProductById(id);

        if (existingProduct == null) {
            throw new EntityNotFoundException("Product not found with ID: " + id);
        }

        productService.deleteProduct(id);
        return "Product deleted successfully!";
    }

    // GET products by category
    @GetMapping("/category/{categoryName}")
    public List<Product> getProductsByCategory(@PathVariable String categoryName) {
        return productService.getProductsByCategory(categoryName);
    }

    // GET products by price range
    @GetMapping("/price")
    public List<Product> getProductsByPriceRange(
            @RequestParam Double min,
            @RequestParam Double max) {
        return productService.getProductsByPriceRange(min, max);
    }
}
