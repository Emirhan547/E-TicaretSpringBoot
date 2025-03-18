package com.eticaret.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.eticaret.domain.ProductDomainService;
import com.eticaret.dto.ProductDTO;
import com.eticaret.entity.Product;
import com.eticaret.mapper.ProductMapper;
import com.eticaret.service.CategoryService;
import com.eticaret.service.ProductService;
import com.eticaret.entity.Category;

@Service
public class ProductServiceImpl implements ProductService{
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductDomainService productDomainService;
    private final CategoryService categoryService; // ✅ Kategori servisi eklendi!

    public ProductServiceImpl(ProductDomainService productDomainService, CategoryService categoryService) {
        this.productDomainService = productDomainService;
        this.categoryService = categoryService; // ✅ Inject edildi!
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO, Long categoryId) {
        logger.info("Ürün oluşturuluyor: {}", productDTO.getName());

        Category category = categoryService.getCategoryEntityById(categoryId); // ✅ Artık CategoryDTO değil, Category döndürüyor!
        Product product = ProductMapper.INSTANCE.toEntity(productDTO);
        product.setCategory(category);

        product = productDomainService.saveProduct(product);
        logger.info("Ürün başarıyla oluşturuldu. ID: {}", product.getId());
        return ProductMapper.INSTANCE.toDTO(product);
    }

    @Override
    @Cacheable(value = "products", key = "#id")
    public ProductDTO getProductById(Long id) {
        logger.info("Ürün getiriliyor. ID: {}", id);
        Product product = productDomainService.findById(id);
        return ProductMapper.INSTANCE.toDTO(product);
    }

    @Override
    @Cacheable(value = "products")
    public List<ProductDTO> getAllProducts() {
        logger.info("Tüm ürünler getiriliyor...");
        return productDomainService.getAllProducts().stream()
                .map(ProductMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @CachePut(value = "products", key = "#id")
    public ProductDTO updateProduct(Long id, ProductDTO productDTO, Long categoryId) {
        logger.info("Ürün güncelleniyor. ID: {}", id);
        Product product = productDomainService.findById(id);

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());

        if (categoryId != null) {
            Category category = categoryService.getCategoryEntityById(categoryId);
            product.setCategory(category);
        }

        product = productDomainService.saveProduct(product);
        logger.info("Ürün başarıyla güncellendi. ID: {}", product.getId());
        return ProductMapper.INSTANCE.toDTO(product);
    }


    @Override
    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(Long id) {
        logger.info("Ürün siliniyor! ID: {}", id);
        productDomainService.deleteProduct(id);
        logger.info("Ürün başarıyla silindi. ID: {}", id);
    }

    @Override
    public List<ProductDTO> getProductsByCategory(Long categoryId) {
        logger.info("Belirtilen kategoriye ait ürünler getiriliyor. Category ID: {}", categoryId);
        return productDomainService.getProductsByCategory(categoryId).stream()
                .map(ProductMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }
}
