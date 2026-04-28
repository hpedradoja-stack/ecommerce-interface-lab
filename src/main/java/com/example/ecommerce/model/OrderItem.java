package com.example.ecommerce.model;

import jakarta.persistence.*;

/**
 * OrderItem Entity
 *
 * Many OrderItems belong to One Order
 * Relationship:
 * OrderItem (Many) ---> (1) CustomerOrder
 */

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private int quantity;
    private double price;

    /**
     * Many order items belong to one order
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private CustomerOrder customerOrder;

    // Constructors

    public OrderItem() {
    }

    public OrderItem(Long id, String productName,
                     int quantity, double price,
                     CustomerOrder customerOrder) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.customerOrder = customerOrder;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }
}
