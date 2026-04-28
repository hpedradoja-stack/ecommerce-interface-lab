package com.example.ecommerce.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CustomerOrder Entity
 *
 * One Order can have many OrderItems
 * Relationship:
 * CustomerOrder (1) ---> (Many) OrderItem
 */

@Entity
@Table(name = "orders")
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    /**
     * One order has many order items
     */
    @OneToMany(mappedBy = "customerOrder",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    // Constructors

    public CustomerOrder() {
    }

    public CustomerOrder(Long id, String customerName,
                         List<OrderItem> orderItems) {
        this.id = id;
        this.customerName = customerName;
        this.orderItems = orderItems;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
