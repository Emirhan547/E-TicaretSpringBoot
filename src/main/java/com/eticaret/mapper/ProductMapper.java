package com.eticaret.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.eticaret.dto.ProductDTO;
import com.eticaret.entity.Product;

@Mapper
public interface ProductMapper {
	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toDTO(Product product);
    Product toEntity(ProductDTO productDTO);
}
