package com.eticaret.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.eticaret.dto.OrderDTO;
import com.eticaret.entity.Order;

@Mapper
public interface OrderMapper {
	OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO toDTO(Order order);
    Order toEntity(OrderDTO orderDTO);;

}
