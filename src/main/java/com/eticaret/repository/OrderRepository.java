package com.eticaret.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eticaret.dto.OrderDTO;
import com.eticaret.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query("SELECT new com.eticaret.dto.OrderDTO(o.id, o.user.id, o.orderDate, o.totalPrice, o.status) FROM Order o")
    List<OrderDTO> getAllOrderDTOs(); // ✅ DTO Projection kullanıldı

	@Query("SELECT new com.eticaret.dto.OrderDTO(o.id, o.user.id, o.orderDate, o.totalPrice, o.status) " +
		       "FROM Order o WHERE o.id = :id")
		Optional<OrderDTO> getOrderDtoById(@Param("id") Long id);
}
