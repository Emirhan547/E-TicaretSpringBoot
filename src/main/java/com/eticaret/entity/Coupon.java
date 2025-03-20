package com.eticaret.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "coupons")
public class Coupon {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private BigDecimal discountAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DiscountType discountType;

    @Column(nullable = false)
    private LocalDate expirationDate;

    @Column(nullable = false)
    private BigDecimal minPurchaseAmount;

    // Constructors
    public Coupon() {}

    public Coupon(String code, BigDecimal discountAmount, DiscountType discountType, LocalDate expirationDate, BigDecimal minPurchaseAmount) {
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

    public void setCode(String code) { this.code = code; }
    public void setDiscountAmount(BigDecimal discountAmount) { this.discountAmount = discountAmount; }
    public void setDiscountType(DiscountType discountType) { this.discountType = discountType; }
    public void setExpirationDate(LocalDate expirationDate) { this.expirationDate = expirationDate; }
    public void setMinPurchaseAmount(BigDecimal minPurchaseAmount) { this.minPurchaseAmount = minPurchaseAmount; }
}
