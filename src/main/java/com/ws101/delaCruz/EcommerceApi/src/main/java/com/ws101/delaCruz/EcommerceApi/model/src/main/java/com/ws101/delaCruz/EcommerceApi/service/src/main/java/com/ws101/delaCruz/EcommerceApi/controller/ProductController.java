package com.ws101.delaCruz.EcommerceApi.controller;

import com.ws101.delaCruz.EcommerceApi.model.Product;
import com.ws101.delaCruz.EcommerceApi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public String updateProduct(
            @PathVariable int id,
            @RequestBody Product updatedProduct
    ) {
        return productService.updateProduct(id, updatedProduct);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }
}
