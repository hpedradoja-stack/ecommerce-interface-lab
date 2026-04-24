package com.ws101.delaCruz.EcommerceApi.controller;

import com.ws101.delaCruz.EcommerceApi.model.Product;
import com.ws101.delaCruz.EcommerceApi.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    ProductController - REST API Layer

    This controller handles HTTP requests and responses.
    It acts as the bridge between the client and service layer.

    Responsibilities:
    - Handle HTTP requests
    - Validate inputs (basic level)
    - Return proper HTTP responses using ResponseEntity
*/

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    // Constructor injection
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // =========================
    // GET: All Products
    // =========================
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // =========================
    // GET: Product by ID
    // =========================
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = productService.getProductById(id);

        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(product);
    }

    // =========================
    // GET: Filter Products
    // =========================
    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterProducts(
            @RequestParam String filterType,
            @RequestParam String filterValue) {

        List<Product> result;

        switch (filterType.toLowerCase()) {
            case "category":
                result = productService.filterByCategory(filterValue);
                break;

            case "name":
                result = productService.searchByName(filterValue);
                break;

            case "price":
                String[] range = filterValue.split("-");
                double min = Double.parseDouble(range[0]);
                double max = Double.parseDouble(range[1]);
                result = productService.filterByPrice(min, max);
                break;

            default:
                return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(result);
    }

    // =========================
    // POST: Create Product
    // =========================
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product created = productService.addProduct(product);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // =========================
    // PUT: Replace Product
    // =========================
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(
            @PathVariable int id,
            @RequestBody Product product) {

        String response = productService.updateProduct(id, product);

        if (response.equals("Product not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }

    // =========================
    // PATCH: Partial Update (simple reuse of PUT logic)
    // =========================
    @PatchMapping("/{id}")
    public ResponseEntity<String> patchProduct(
            @PathVariable int id,
            @RequestBody Product product) {

        String response = productService.updateProduct(id, product);

        if (response.equals("Product not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }

    // =========================
    // DELETE: Remove Product
    // =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        String response = productService.deleteProduct(id);

        if (response.equals("Product not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }
}
