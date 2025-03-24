package com.eticaret.dto;

import java.math.BigDecimal;

public class ProductDTO {
	private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int stockQuantity;
    private Long categoryId; // ✅ Kategori ID eklendi!

    public ProductDTO() {}

    public ProductDTO(Long id, String name, String description, BigDecimal price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }


    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    public Long getCategoryId() { return categoryId; } // ✅ Yeni getter
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; } // ✅ Yeni setter
}
