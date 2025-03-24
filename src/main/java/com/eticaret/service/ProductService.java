package com.eticaret.service;

import java.util.List;

import com.eticaret.dto.ProductDTO;

public interface ProductService {
	ProductDTO createProduct(ProductDTO productDTO, Long categoryId); // ✅ Kategori ID eklendi!
    ProductDTO getProductById(Long id);
    List<ProductDTO> getAllProducts();
    ProductDTO updateProduct(Long id, ProductDTO productDTO, Long categoryId); // ✅ Kategori ID eklendi!
    void deleteProduct(Long id);
    List<ProductDTO> getProductsByCategory(Long categoryId); // ✅ Yeni metod

}
