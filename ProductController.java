package com.ws101.delaCruz.EcommerceApi.controller;

import com.ws101.delaCruz.EcommerceApi.model.Product;
import com.ws101.delaCruz.EcommerceApi.service.ProductService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    TASK 6.1 - Manual API Testing (Postman / curl)
    This section contains all REST endpoints used for testing the API.
*/

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET ALL PRODUCTS
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // GET PRODUCT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable int id) {
        Product product = productService.getProductById(id);
        if (product == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(product);
    }

    // CREATE PRODUCT (POST)
    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        return ResponseEntity.status(201).body(productService.addProduct(product));
    }

    // UPDATE PRODUCT (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id,
                                         @Valid @RequestBody Product product) {

        String result = productService.updateProduct(id, product);

        if (result.equals("Product not found")) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result);
    }

    // PATCH PRODUCT
    @PatchMapping("/{id}")
    public ResponseEntity<String> patch(@PathVariable int id,
                                        @RequestBody Product product) {

        String result = productService.updateProduct(id, product);

        if (result.equals("Product not found")) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result);
    }

    // DELETE PRODUCT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        String result = productService.deleteProduct(id);

        if (result.equals("Product not found")) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    // FILTER PRODUCTS
    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filter(
            @RequestParam String filterType,
            @RequestParam String filterValue) {

        switch (filterType.toLowerCase()) {

            case "category":
                return ResponseEntity.ok(productService.filterByCategory(filterValue));

            case "name":
                return ResponseEntity.ok(productService.searchByName(filterValue));

            case "price":
                String[] range = filterValue.split("-");
                double min = Double.parseDouble(range[0]);
                double max = Double.parseDouble(range[1]);
                return ResponseEntity.ok(productService.filterByPrice(min, max));

            default:
                return ResponseEntity.badRequest().build();
        }
    }
}
