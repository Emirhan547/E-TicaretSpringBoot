package com.eticaret.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.eticaret.dto.WishlistDTO;
import com.eticaret.entity.Wishlist;

@Mapper
public interface WishlistMapper {
	WishlistMapper INSTANCE = Mappers.getMapper(WishlistMapper.class); // ✅ Hata giderildi!

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "product.id", target = "productId")
    WishlistDTO toDTO(Wishlist wishlist);

}
