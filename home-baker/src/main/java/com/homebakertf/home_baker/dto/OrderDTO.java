package com.homebakertf.home_baker.dto;

import com.homebakertf.home_baker.model.Order;
import java.time.LocalDateTime;

public class OrderDTO {
    private Long id;
    private ProductDTO product;
    private UserDTO vendor;
    private UserDTO customer;
    private int quantity;
    private Order.Status status;
    private LocalDateTime createdAt;

    public OrderDTO(Long id, ProductDTO product, UserDTO vendor, UserDTO customer,
                    int quantity, Order.Status status, LocalDateTime createdAt) {
        this.id = id;
        this.product = product;
        this.vendor = vendor;
        this.customer = customer;
        this.quantity = quantity;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public ProductDTO getProduct() { return product; }
    public UserDTO getVendor() { return vendor; }
    public UserDTO getCustomer() { return customer; }
    public int getQuantity() { return quantity; }
    public Order.Status getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
