package com.eticaret.dto;

import java.time.LocalDateTime;

import com.eticaret.entity.OrderStatus;

public class OrderDTO {
	private Long id;
    private Long userId; // ✅ user.id alanı eklendi
    private LocalDateTime orderDate;
    private Double totalPrice;
    private OrderStatus status;

    // Constructor
    public OrderDTO(Long id, Long userId, LocalDateTime orderDate, Double totalPrice, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    // Getter ve Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
}
