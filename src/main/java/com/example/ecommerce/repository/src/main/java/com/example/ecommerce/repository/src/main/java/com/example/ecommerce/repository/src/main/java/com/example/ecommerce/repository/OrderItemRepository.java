package com.example.ecommerce.repository;

import com.example.ecommerce.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * OrderItem Repository
 *
 * Handles database operations for OrderItem entity
 */

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
