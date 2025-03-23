package com.eticaret.dto;

public class ReviewDTO {
	private Long id;
    private int rating;
    private String comment;
    private Long productId;
    private Long userId;

    public ReviewDTO() {}

    public ReviewDTO(Long id, int rating, String comment, Long productId, Long userId) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.productId = productId;
        this.userId = userId;
    }

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
