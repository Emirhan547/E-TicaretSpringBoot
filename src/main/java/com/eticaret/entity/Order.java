package com.eticaret.entity;

import java.time.LocalDateTime;

import com.eticaret.converter.OrderStateConverter;
import com.eticaret.state.OrderState;
import com.eticaret.state.impl.PendingState;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;


@Entity
@Table(name = "orders")
public class Order {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Convert(converter = OrderStateConverter.class)
    private OrderState orderState;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Double totalPrice;

    public Order() {
        this.orderDate = LocalDateTime.now();
        this.orderState = new PendingState();
        this.status = OrderStatus.PENDING;
    }

    public void nextState() {
        orderState.next(this);
    }

    public void cancelOrder() {
        orderState.cancel(this);
    }

    @PostLoad
    private void restoreState() {
        if (this.orderState == null) {
            this.orderState = new PendingState();
        }
    }

    // Getter ve Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public OrderState getOrderState() { return orderState; }
    public void setOrderState(OrderState orderState) { this.orderState = orderState; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }
}
