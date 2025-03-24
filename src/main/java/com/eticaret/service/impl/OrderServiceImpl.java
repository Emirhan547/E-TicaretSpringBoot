package com.eticaret.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eticaret.domain.OrderDomainService;
import com.eticaret.dto.OrderDTO;
import com.eticaret.entity.Order;
import com.eticaret.mapper.OrderMapper;
import com.eticaret.service.EmailService;
import com.eticaret.service.OrderService;



@Service
public class OrderServiceImpl implements OrderService {
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderDomainService orderDomainService;
    private final EmailService emailService;

    public OrderServiceImpl(OrderDomainService orderDomainService, EmailService emailService) {
        this.orderDomainService = orderDomainService;
        this.emailService = emailService;
    }

    @Override
    @Async // ✅ Sipariş oluşturma işlemi arka planda çalışacak
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        logger.info("Yeni sipariş oluşturuluyor...");
        Order order = OrderMapper.INSTANCE.toEntity(orderDTO);
        order = orderDomainService.saveOrder(order); // ✅ Güncellendi!
        logger.info("Sipariş başarıyla oluşturuldu. ID: {}", order.getId());

        // Sipariş onay e-postasını arka planda gönderelim.
        sendOrderConfirmationEmail(order.getUser().getEmail(), order.getId());

        return OrderMapper.INSTANCE.toDTO(order);
    }

    @Async // ✅ E-posta işlemi arka planda çalışacak
    private void sendOrderConfirmationEmail(String email, Long orderId) {
        logger.info("Sipariş onay e-postası gönderiliyor...");
        String subject = "Sipariş Onaylandı!";
        String body = "Merhaba,\n\nSiparişiniz başarıyla oluşturuldu! Sipariş Numaranız: " + orderId;
        emailService.sendEmail(email, subject, body);
    }

    @Override
    @Cacheable(value = "orders", key = "#id")
    public OrderDTO getOrderById(Long id) {
        logger.info("Sipariş getiriliyor. ID: {}", id);
        Order order = orderDomainService.findById(id);
        return OrderMapper.INSTANCE.toDTO(order);
    }

    @Override
    @Cacheable(value = "orders")
    public List<OrderDTO> getAllOrders() {
        logger.info("Tüm siparişler getiriliyor...");
        return orderDomainService.findAllOrders().stream()
                .map(OrderMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value = "orders", key = "#id")
    public void deleteOrder(Long id) {
        logger.warn("Sipariş siliniyor! ID: {}", id);
        orderDomainService.deleteOrder(id);
        logger.info("Sipariş başarıyla silindi. ID: {}", id);
    }

    @Override
    @CachePut(value = "orders", key = "#id")
    public void updateOrderStatus(Long id) {
        logger.info("Sipariş durumu güncelleniyor. ID: {}", id);
        Order order = orderDomainService.findById(id);
        order.nextState();
        orderDomainService.saveOrder(order);
        logger.info("Sipariş durumu başarıyla güncellendi. ID: {}", id);
    }

    @Override
    @CacheEvict(value = "orders", key = "#id")
    public void cancelOrder(Long id) {
        logger.warn("Sipariş iptal ediliyor! ID: {}", id);
        Order order = orderDomainService.findById(id);
        order.cancelOrder();
        orderDomainService.saveOrder(order);
        logger.info("Sipariş başarıyla iptal edildi. ID: {}", id);
    }
}