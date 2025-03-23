package com.eticaret.service;

import java.util.List;

import com.eticaret.dto.ReviewDTO;


public interface ReviewService {
	ReviewDTO createReview(Long userId, Long productId, ReviewDTO reviewDTO); // ✅ DTO dönüşümü ile uyumlu
	List<ReviewDTO> getReviewsByProduct(Long productId); // ✅ DTO dönüşümü ile uyumlu

}
