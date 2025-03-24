package com.eticaret.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.eticaret.dto.CouponDTO;
import com.eticaret.entity.Coupon;

@Mapper
public interface CouponMapper {
	CouponMapper INSTANCE = Mappers.getMapper(CouponMapper.class);

    CouponDTO toDto(Coupon coupon);
    Coupon toEntity(CouponDTO couponDTO);
}
