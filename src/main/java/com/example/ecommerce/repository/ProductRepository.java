package com.example.ecommerce.repository;

import com.example.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Product Repository
 *
 * Handles database operations for Product entity
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Find products by category name using Method Naming
     *
     * Example:
     * findByCategoryName("Electronics")
     */
    List<Product> findByCategoryName(String name);

    /**
     * Find products within price range using JPQL
     */
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max")
    List<Product> findProductsByPriceRange(
            @Param("min") double min,
            @Param("max") double max
    );
}
