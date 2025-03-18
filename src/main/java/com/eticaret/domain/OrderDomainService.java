package com.eticaret.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eticaret.entity.Order;
import com.eticaret.repository.OrderRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderDomainService {
	private final OrderRepository orderRepository;

    public OrderDomainService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sipariş bulunamadı: " + id));
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

}
