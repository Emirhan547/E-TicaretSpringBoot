package com.eticaret.dto;

public class CartDTO {
	private Long id;
    private Long userId;

    public CartDTO() {}

    public CartDTO(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
    }

    // Getter & Setter
    public Long getId() { return id; }
    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }
}
