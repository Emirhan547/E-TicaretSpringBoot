package com.eticaret.service;

import java.util.List;

import com.eticaret.dto.CouponDTO;

public interface CouponService {
	CouponDTO createCoupon(CouponDTO couponDTO);
    CouponDTO getCouponByCode(String code);
    List<CouponDTO> getAllCoupons();
    void deleteCoupon(Long id);

}
