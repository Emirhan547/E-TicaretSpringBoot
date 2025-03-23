package com.eticaret.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.eticaret.domain.ReviewDomainService;
import com.eticaret.dto.ReviewDTO;
import com.eticaret.entity.Review;
import com.eticaret.mapper.ReviewMapper;
import com.eticaret.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
	private final ReviewDomainService reviewDomainService;
    private static final Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);

    public ReviewServiceImpl(ReviewDomainService reviewDomainService) {
        this.reviewDomainService = reviewDomainService;
    }

    @Override
    @CacheEvict(value = "reviews", key = "#productId") // ✅ Yeni inceleme eklendiğinde cache temizlenecek
    public ReviewDTO createReview(Long userId, Long productId, ReviewDTO reviewDTO) {
        logger.info("Yeni inceleme ekleniyor. Product ID: {}, User ID: {}", productId, userId);
        Review review = ReviewMapper.INSTANCE.toEntity(reviewDTO);
        review.setCreatedAt(LocalDateTime.now());
        
        review = reviewDomainService.saveReview(userId, productId, review);
        return ReviewMapper.INSTANCE.toDTO(review);
    }

    @Override
    @Cacheable(value = "reviews", key = "#productId") // ✅ İnceleme cache’e alınacak
    public List<ReviewDTO> getReviewsByProduct(Long productId) {
        logger.info("Ürün için incelemeler cache’ten getiriliyor. Product ID: {}", productId);
        return reviewDomainService.findByProductId(productId).stream()
                .map(ReviewMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }
}
