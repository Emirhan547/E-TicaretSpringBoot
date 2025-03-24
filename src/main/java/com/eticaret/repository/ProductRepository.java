package com.eticaret.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eticaret.dto.ProductDTO;
import com.eticaret.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByCategoryId(Long categoryId);

	@Query("SELECT new com.eticaret.dto.ProductDTO(p.id, p.name, p.description, p.price, p.stockQuantity) FROM Product p")
    List<ProductDTO> getAllProductDTOs();

    @Query("SELECT new com.eticaret.dto.ProductDTO(p.id, p.name, p.description, p.price, p.stockQuantity) FROM Product p WHERE p.id = :id")
    Optional<ProductDTO> getProductDtoById(Long id);
}
