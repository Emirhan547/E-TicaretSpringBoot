package com.eticaret.dto;

import java.time.LocalDateTime;

public class NotificationDTO {
	private String recipient;
    private String message;
    private LocalDateTime createdAt;

    public NotificationDTO() {}

    public NotificationDTO(String recipient, String message, LocalDateTime createdAt) {
        this.recipient = recipient;
        this.message = message;
        this.createdAt = createdAt;
    }

    // Getter & Setter
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setRecipient(String recipient) { this.recipient = recipient; }
    public void setMessage(String message) { this.message = message; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }



}
