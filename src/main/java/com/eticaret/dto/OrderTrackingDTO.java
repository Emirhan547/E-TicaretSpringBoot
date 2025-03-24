package com.eticaret.dto;

import java.time.LocalDateTime;

public class OrderTrackingDTO {
	private Long id;
    private String orderNumber;
    private String status;
    private LocalDateTime updatedAt;

    // Parametresiz Constructor
    public OrderTrackingDTO() {}

    // Parametreli Constructor
    public OrderTrackingDTO(Long id, String orderNumber, String status, LocalDateTime updatedAt) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.status = status;
        this.updatedAt = updatedAt;
    }

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

}
