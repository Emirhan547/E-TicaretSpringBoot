package com.eticaret.service;

import com.eticaret.dto.PaymentDTO;

public interface PaymentService {
	PaymentDTO processPayment(PaymentDTO paymentDTO); // ✅ DTO dönüşümü ile uyumlu

    boolean validatePayment(Long orderId);
}
