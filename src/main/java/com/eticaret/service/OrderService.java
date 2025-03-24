package com.eticaret.service;

import java.util.List;

import com.eticaret.dto.OrderDTO;

public interface OrderService {
	OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO getOrderById(Long id);
    List<OrderDTO> getAllOrders();
    void deleteOrder(Long id);
    void updateOrderStatus(Long id);
    void cancelOrder(Long id);
}
