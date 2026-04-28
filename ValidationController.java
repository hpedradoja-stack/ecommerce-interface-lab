// =======================
// DTO: RegisterUserDto
// =======================
package com.example.ecommerce.dto;

import jakarta.validation.constraints.*;

public class RegisterUserDto {

    @NotBlank(message = "Username is required")
    @Size(min = 8, max = 20, message = "Username must be 8–20 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be 8–20 characters")
    private String password;

    @NotBlank(message = "Role is required")
    private String role;

    // getters & setters
}


// =======================
// DTO: CreateProductDto
// =======================
package com.example.ecommerce.dto;

import jakarta.validation.constraints.*;

public class CreateProductDto {

    @NotBlank(message = "Product name is required")
    private String name;

    @Positive(message = "Price must be positive")
    private double price;

    @Positive(message = "Stock must be positive")
    private int stock;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    // getters & setters
}


// =======================
// DTO: ProductListingDto (READ ONLY)
// =======================
package com.example.ecommerce.dto;

public record ProductListingDto(
        Long prodId,
        String prodName,
        double prodPrice
) {}


// =======================
// CONTROLLER (USES DTO + VALIDATION)
// =======================
package com.example.ecommerce.controller;

import com.example.ecommerce.dto.*;
import com.example.ecommerce.entity.*;
import com.example.ecommerce.repository.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // =======================
    // REGISTER (PUBLIC)
    // =======================
    @PostMapping("/auth/register")
    public String register(@Valid @RequestBody RegisterUserDto dto) {

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());

        userRepository.save(user);

        return "User registered successfully!";
    }

    // =======================
    // CREATE PRODUCT (VALIDATED)
    // =======================
    @PostMapping("/products")
    public Product createProduct(@Valid @RequestBody CreateProductDto dto) {

        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow();

        product.setCategory(category);

        return productRepository.save(product);
    }

    // =======================
    // PRODUCT LIST (DTO OUTPUT)
    // =======================
    @GetMapping("/products")
    public List<ProductListingDto> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(p -> new ProductListingDto(
                        p.getId(),
                        p.getName(),
                        p.getPrice()
                ))
                .toList();
    }
}
