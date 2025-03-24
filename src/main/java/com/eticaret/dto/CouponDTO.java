package com.eticaret.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.eticaret.entity.DiscountType;

public class CouponDTO {
	private Long id;
    private String code;
    private BigDecimal discountAmount;
    private DiscountType discountType;
    private LocalDate expirationDate;
    private BigDecimal minPurchaseAmount;

    public CouponDTO() {}

    public CouponDTO(Long id, String code, BigDecimal discountAmount, DiscountType discountType, LocalDate expirationDate, BigDecimal minPurchaseAmount) {
        this.id = id;
        this.code = code;
        this.discountAmount = discountAmount;
        this.discountType = discountType;
        this.expirationDate = expirationDate;
        this.minPurchaseAmount = minPurchaseAmount;
    }

    // Getter & Setter
    public Long getId() { return id; }
    public String getCode() { return code; }
    public BigDecimal getDiscountAmount() { return discountAmount; }
    public DiscountType getDiscountType() { return discountType; }
    public LocalDate getExpirationDate() { return expirationDate; }
    public BigDecimal getMinPurchaseAmount() { return minPurchaseAmount; }
}
