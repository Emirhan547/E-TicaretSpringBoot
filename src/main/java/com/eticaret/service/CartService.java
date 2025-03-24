package com.eticaret.service;

import com.eticaret.dto.CartDTO;
import com.eticaret.entity.Cart;

public interface CartService extends BaseService<Cart, Long> {
	CartDTO getCartByUserId(Long userId);
    void clearCart(Long userId);

}
