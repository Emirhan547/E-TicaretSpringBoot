package com.eticaret.dto;

public class WishlistDTO {
	private Long id;
    private Long userId;
    private Long productId;

    public WishlistDTO() {}

    public WishlistDTO(Long id, Long userId, Long productId) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public Long getProductId() { return productId; }

    public void setId(Long id) { this.id = id; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setProductId(Long productId) { this.productId = productId; }

}
