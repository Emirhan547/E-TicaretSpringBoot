package com.eticaret.controller;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eticaret.dto.OrderTrackingDTO;
import com.eticaret.service.OrderTrackingService;

@RestController
@RequestMapping("/api/order-tracking")
public class OrderTrackingController {
	private final OrderTrackingService orderTrackingService;

    public OrderTrackingController(OrderTrackingService orderTrackingService) {
        this.orderTrackingService = orderTrackingService;
    }

    @PostMapping
    public ResponseEntity<OrderTrackingDTO> createOrderTracking(@RequestBody OrderTrackingDTO orderTrackingDTO) {
        return ResponseEntity.ok(orderTrackingService.createOrderTracking(orderTrackingDTO));
    }

    @GetMapping("/{orderNumber}")
    public ResponseEntity<OrderTrackingDTO> getOrderTrackingByOrderNumber(@PathVariable String orderNumber) {
        return orderTrackingService.getOrderTrackingByOrderNumber(orderNumber)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // ✅ Optional olduğu için böyle değiştirdim
    }

    @GetMapping
    public ResponseEntity<List<OrderTrackingDTO>> getAllOrderTracking() {
        return ResponseEntity.ok(orderTrackingService.getAllOrderTracking());
    }

    @PutMapping("/{orderNumber}/status")
    public ResponseEntity<OrderTrackingDTO> updateOrderStatus(@PathVariable String orderNumber, @RequestParam String status) {
        return ResponseEntity.ok(orderTrackingService.updateOrderStatus(orderNumber, status));
    }
}
