package com.eticaret.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.eticaret.dto.OrderTrackingDTO;
import com.eticaret.entity.OrderTracking;
import com.eticaret.mapper.OrderTrackingMapper;
import com.eticaret.repository.OrderTrackingRepository;
import com.eticaret.service.OrderTrackingService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderTrackingServiceImpl implements OrderTrackingService {
	private final OrderTrackingRepository orderTrackingRepository;
    private static final Logger logger = LoggerFactory.getLogger(OrderTrackingServiceImpl.class);

    public OrderTrackingServiceImpl(OrderTrackingRepository orderTrackingRepository) {
        this.orderTrackingRepository = orderTrackingRepository;
    }

    @Override
    @CachePut(value = "orderTracking", key = "#orderTrackingDTO.orderNumber")
    public OrderTrackingDTO createOrderTracking(OrderTrackingDTO orderTrackingDTO) {
        logger.info("Yeni sipariş takibi oluşturuluyor. Sipariş Numarası: {}", orderTrackingDTO.getOrderNumber());
        OrderTracking orderTracking = OrderTrackingMapper.INSTANCE.toEntity(orderTrackingDTO);
        orderTracking.setUpdatedAt(LocalDateTime.now());
        OrderTracking savedOrder = orderTrackingRepository.save(orderTracking);
        return OrderTrackingMapper.INSTANCE.toDto(savedOrder);
    }

    @Override
    @Cacheable(value = "orderTracking", key = "#orderNumber")
    public Optional<OrderTrackingDTO> getOrderTrackingByOrderNumber(String orderNumber) {
        logger.info("Sipariş takibi getiriliyor. Sipariş Numarası: {}", orderNumber);
        return orderTrackingRepository.findByOrderNumber(orderNumber)
                .map(OrderTrackingMapper.INSTANCE::toDto);
    }

    @Override
    @Cacheable(value = "orderTracking")
    public List<OrderTrackingDTO> getAllOrderTracking() {
        logger.info("Tüm sipariş takipleri getiriliyor...");
        return orderTrackingRepository.findAll().stream()
                .map(OrderTrackingMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @CachePut(value = "orderTracking", key = "#orderNumber")
    public OrderTrackingDTO updateOrderStatus(String orderNumber, String status) {
        logger.info("Sipariş takibi güncelleniyor. Sipariş Numarası: {}, Yeni Durum: {}", orderNumber, status);
        OrderTracking orderTracking = orderTrackingRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> {
                    logger.error("Sipariş takibi bulunamadı! Sipariş Numarası: {}", orderNumber);
                    return new EntityNotFoundException("Sipariş takibi bulunamadı: " + orderNumber);
                });

        orderTracking.setStatus(status);
        orderTracking.setUpdatedAt(LocalDateTime.now());
        OrderTracking updatedOrder = orderTrackingRepository.save(orderTracking);
        logger.info("Sipariş takibi başarıyla güncellendi. Sipariş Numarası: {}", orderNumber);
        return OrderTrackingMapper.INSTANCE.toDto(updatedOrder);
    }
}
