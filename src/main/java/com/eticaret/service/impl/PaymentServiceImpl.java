package com.eticaret.service.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.eticaret.dto.PaymentDTO;
import com.eticaret.entity.Payment;
import com.eticaret.mapper.PaymentMapper;
import com.eticaret.repository.PaymentRepository;
import com.eticaret.service.PaymentService;

import jakarta.transaction.Transactional;

@Service
public class PaymentServiceImpl implements 	PaymentService{
	private final PaymentRepository paymentRepository;
    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Async // ✅ Ödeme işlemi arka planda çalışacak
    @Transactional
    public PaymentDTO processPayment(PaymentDTO paymentDTO) {
        logger.info("Ödeme işleniyor. Sipariş ID: {}", paymentDTO.getOrderId());

        if (!validatePayment(paymentDTO.getOrderId())) {
            logger.error("Ödeme doğrulaması başarısız! Sipariş ID: {}", paymentDTO.getOrderId());
            throw new IllegalStateException("Ödeme doğrulaması başarısız!");
        }

        try {
            Thread.sleep(5000); // 🔥 Simüle edilmiş gecikme (Gerçek sistemde API çağrısı olabilir)
        } catch (InterruptedException e) {
            logger.error("Ödeme sürecinde hata oluştu: {}", e.getMessage());
        }

        Payment payment = PaymentMapper.INSTANCE.toEntity(paymentDTO);
        payment.setSuccess(true);
        payment.setPaymentDate(LocalDateTime.now());

        payment = paymentRepository.save(payment);
        logger.info("Ödeme başarıyla işlendi. Sipariş ID: {}", paymentDTO.getOrderId());

        return PaymentMapper.INSTANCE.toDTO(payment);
    }

    @Override
    public boolean validatePayment(Long orderId) {
        logger.info("Ödeme doğrulanıyor. Sipariş ID: {}", orderId);
        // Gerçek banka API doğrulaması buraya entegre edilecek
        return true;
    }
}
