package com.eticaret.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eticaret.entity.Product;
import com.eticaret.entity.Review;
import com.eticaret.entity.User;
import com.eticaret.repository.ProductRepository;
import com.eticaret.repository.ReviewRepository;
import com.eticaret.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReviewDomainService {
	private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ReviewDomainService(ReviewRepository reviewRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<Review> findByProductId(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    public Review saveReview(Long userId, Long productId, Review review) { // ✅ Metodu güncelledik!
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Kullanıcı bulunamadı: " + userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Ürün bulunamadı: " + productId));

        review.setUser(user);
        review.setProduct(product);

        return reviewRepository.save(review);
    }
}
