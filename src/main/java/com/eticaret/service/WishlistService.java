package com.eticaret.service;

import java.util.List;

import com.eticaret.dto.WishlistDTO;
import com.eticaret.entity.Wishlist;

public interface WishlistService extends BaseService<Wishlist, Long> {
	List<WishlistDTO> getUserWishlist(Long userId);
    void removeFromWishlist(Long userId, Long productId);
    WishlistDTO addToWishlist(Long userId, Long productId);
}
