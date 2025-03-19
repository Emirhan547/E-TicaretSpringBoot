package com.eticaret.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.eticaret.dto.CartDTO;
import com.eticaret.entity.Cart;
import com.eticaret.mapper.CartMapper;
import com.eticaret.repository.CartRepository;
import com.eticaret.service.CartService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CartServiceImpl extends BaseServiceImpl<Cart, Long> implements CartService {
	private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        super(cartRepository); // ✅ BaseServiceImpl çağrıldı!
        this.cartRepository = cartRepository;
    }

    @Override
    @Cacheable(value = "carts", key = "#userId")
    public CartDTO getCartByUserId(Long userId) {
        logger.info("Kullanıcının sepeti getiriliyor. User ID: {}", userId);
        return cartRepository.findByUserId(userId)
                .map(CartMapper.INSTANCE::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Sepet bulunamadı! Kullanıcı ID: " + userId));
    }

    @Override
    @CacheEvict(value = "carts", key = "#userId")
    public void clearCart(Long userId) {
        logger.warn("Sepet temizleniyor. User ID: {}", userId);
        cartRepository.findByUserId(userId).ifPresent(cartRepository::delete);
        logger.info("Sepet başarıyla temizlendi. User ID: {}", userId);
    }
}
