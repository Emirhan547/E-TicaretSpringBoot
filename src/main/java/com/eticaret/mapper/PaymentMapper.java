package com.eticaret.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.eticaret.dto.PaymentDTO;
import com.eticaret.entity.Payment;

@Mapper
public interface PaymentMapper {
	PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    PaymentDTO toDTO(Payment payment);
    Payment toEntity(PaymentDTO paymentDTO);

}
