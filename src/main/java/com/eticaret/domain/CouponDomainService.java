package com.eticaret.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eticaret.entity.Coupon;
import com.eticaret.repository.CouponRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CouponDomainService {
	private final CouponRepository couponRepository;

    public CouponDomainService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public Coupon findByCode(String code) {
        return couponRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Kupon bulunamadı: " + code));
    }

    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    public Coupon saveCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    public void deleteCoupon(Long id) {
        couponRepository.deleteById(id);
    }

}
