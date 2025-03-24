package com.eticaret.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eticaret.entity.Wishlist;

@Repository
public interface WishlistRepository  extends JpaRepository<Wishlist, Long>{
	List<Wishlist> findByUserId(Long userId);

	void deleteByUserIdAndProductId(Long userId, Long productId);

}
