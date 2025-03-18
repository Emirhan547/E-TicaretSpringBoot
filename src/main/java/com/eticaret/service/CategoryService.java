package com.eticaret.service;

import java.util.List;

import com.eticaret.dto.CategoryDTO;
import com.eticaret.entity.Category;

public interface CategoryService {
	CategoryDTO createCategory(CategoryDTO categoryDTO);
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(Long id);
    void deleteCategory(Long id);

    // ✅ Yeni Metot: Kategori Entity'sini Döndürür!
    Category getCategoryEntityById(Long id);

}
