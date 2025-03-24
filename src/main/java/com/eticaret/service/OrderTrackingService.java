package com.eticaret.service;

import java.util.List;
import java.util.Optional;

import com.eticaret.dto.OrderTrackingDTO;

public interface OrderTrackingService {
	OrderTrackingDTO createOrderTracking(OrderTrackingDTO orderTrackingDTO);
    Optional<OrderTrackingDTO> getOrderTrackingByOrderNumber(String orderNumber);
    List<OrderTrackingDTO> getAllOrderTracking();
    OrderTrackingDTO updateOrderStatus(String orderNumber, String status);

}
