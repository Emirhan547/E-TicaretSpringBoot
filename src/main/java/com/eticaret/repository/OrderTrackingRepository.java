package com.eticaret.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eticaret.entity.OrderTracking;

@Repository
public interface OrderTrackingRepository extends JpaRepository<OrderTracking, Long> {
	Optional<OrderTracking> findByOrderNumber(String orderNumber);

}
