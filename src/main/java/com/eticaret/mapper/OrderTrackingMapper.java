package com.eticaret.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.eticaret.dto.OrderTrackingDTO;
import com.eticaret.entity.OrderTracking;

@Mapper
public interface OrderTrackingMapper {
	OrderTrackingMapper INSTANCE = Mappers.getMapper(OrderTrackingMapper.class);

    OrderTrackingDTO toDto(OrderTracking orderTracking);
    OrderTracking toEntity(OrderTrackingDTO orderTrackingDTO);

}
