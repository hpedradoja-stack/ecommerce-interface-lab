package com.example.ecommerce.repository;

import com.example.ecommerce.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CustomerOrder Repository
 *
 * Handles database operations for CustomerOrder entity
 */

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

}
