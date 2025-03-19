package com.eticaret.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eticaret.dto.PaymentDTO;
import com.eticaret.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
	private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/process")
    public ResponseEntity<PaymentDTO> processPayment(@RequestBody PaymentDTO paymentDTO) {
        PaymentDTO processedPayment = paymentService.processPayment(paymentDTO);
        return ResponseEntity.ok(processedPayment);
    }


}
