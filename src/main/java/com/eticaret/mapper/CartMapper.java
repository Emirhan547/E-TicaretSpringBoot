package com.eticaret.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.eticaret.dto.CartDTO;
import com.eticaret.entity.Cart;

@Mapper
public interface CartMapper {
	CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    @Mapping(source = "user.id", target = "userId")
    CartDTO toDto(Cart cart);

    @Mapping(source = "userId", target = "user.id")
    Cart toEntity(CartDTO cartDTO);
}
