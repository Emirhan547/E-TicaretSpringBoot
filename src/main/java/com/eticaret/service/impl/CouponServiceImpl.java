package com.eticaret.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.eticaret.domain.CouponDomainService;
import com.eticaret.dto.CouponDTO;
import com.eticaret.entity.Coupon;
import com.eticaret.mapper.CouponMapper;
import com.eticaret.service.CouponService;


@Service
public class CouponServiceImpl implements CouponService {
	private static final Logger logger = LoggerFactory.getLogger(CouponServiceImpl.class);

    private final CouponDomainService couponDomainService;

    public CouponServiceImpl(CouponDomainService couponDomainService) {
        this.couponDomainService = couponDomainService;
    }

    @Override
    @CacheEvict(value = "coupons", allEntries = true)
    public CouponDTO createCoupon(CouponDTO couponDTO) {
        logger.info("Yeni kupon oluşturuluyor: {}", couponDTO.getCode());
        Coupon coupon = CouponMapper.INSTANCE.toEntity(couponDTO);
        Coupon savedCoupon = couponDomainService.saveCoupon(coupon); // ✅ Güncellendi!
        return CouponMapper.INSTANCE.toDto(savedCoupon);
    }

    @Override
    @Cacheable(value = "coupons", key = "#code")
    public CouponDTO getCouponByCode(String code) {
        logger.info("Kupon aranıyor: {}", code);
        return CouponMapper.INSTANCE.toDto(couponDomainService.findByCode(code)); // ✅ Güncellendi!
    }

    @Override
    @Cacheable(value = "coupons")
    public List<CouponDTO> getAllCoupons() {
        logger.info("Tüm kuponlar getiriliyor...");
        return couponDomainService.getAllCoupons().stream() // ✅ Güncellendi!
                .map(CouponMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value = "coupons", key = "#id")
    public void deleteCoupon(Long id) {
        logger.warn("Kupon siliniyor: {}", id);
        couponDomainService.deleteCoupon(id); // ✅ Güncellendi!
        logger.info("Kupon başarıyla silindi: {}", id);
    }
}
