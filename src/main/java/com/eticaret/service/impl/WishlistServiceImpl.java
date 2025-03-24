package com.eticaret.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.eticaret.dto.WishlistDTO;
import com.eticaret.entity.Product;
import com.eticaret.entity.User;
import com.eticaret.entity.Wishlist;
import com.eticaret.mapper.WishlistMapper;
import com.eticaret.repository.ProductRepository;
import com.eticaret.repository.UserRepository;
import com.eticaret.repository.WishlistRepository;
import com.eticaret.service.WishlistService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class WishlistServiceImpl extends BaseServiceImpl<Wishlist, Long> implements WishlistService {
	private static final Logger logger = LoggerFactory.getLogger(WishlistServiceImpl.class);

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public WishlistServiceImpl(WishlistRepository wishlistRepository, UserRepository userRepository, ProductRepository productRepository) {
        super(wishlistRepository); // ✅ Super constructor çağrıldı!
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Cacheable(value = "wishlist", key = "#userId")
    public List<WishlistDTO> getUserWishlist(Long userId) {
        logger.info("Kullanıcının favori listesi getiriliyor. User ID: {}", userId);
        return wishlistRepository.findByUserId(userId).stream()
                .map(WishlistMapper.INSTANCE::toDTO) // ✅ Mapper kullanıldı!
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value = "wishlist", key = "#userId")
    public void removeFromWishlist(Long userId, Long productId) {
        logger.warn("Favori listesinden ürün kaldırılıyor. User ID: {}, Product ID: {}", userId, productId);
        wishlistRepository.deleteByUserIdAndProductId(userId, productId);
        logger.info("Favori listesinden ürün başarıyla kaldırıldı. User ID: {}, Product ID: {}", userId, productId);
    }

    @Override
    public WishlistDTO addToWishlist(Long userId, Long productId) {
        logger.info("Favorilere ürün ekleniyor. User ID: {}, Product ID: {}", userId, productId);
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Kullanıcı bulunamadı: " + userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Ürün bulunamadı: " + productId));

        Wishlist wishlist = new Wishlist(user, product);
        wishlist = wishlistRepository.save(wishlist);

        logger.info("Ürün favorilere eklendi. Wishlist ID: {}", wishlist.getId());
        return WishlistMapper.INSTANCE.toDTO(wishlist); // ✅ DTO dönüşümü merkezi hale getirildi!
    }
}
