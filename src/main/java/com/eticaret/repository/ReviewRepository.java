package com.eticaret.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eticaret.dto.ReviewDTO;
import com.eticaret.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	List<Review> findByProductId(Long productId);
	@Query("SELECT new com.eticaret.dto.ReviewDTO(r.id, r.rating, r.comment, r.product.id, r.user.id) FROM Review r WHERE r.product.id = :productId")
    List<ReviewDTO> getReviewsByProduct(Long productId); // ✅ DTO Projection kullanıldı

}
