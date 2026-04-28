package com.example.ecommerce.repository;

import com.example.ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Category Repository
 *
 * Handles database operations for Category entity
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
