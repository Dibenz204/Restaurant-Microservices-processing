package com.restaurant.menuservice.service;

import com.restaurant.menuservice.dto.CategoryBasicDTO;
import com.restaurant.menuservice.dto.CategoryCreateRequestDTO;
import com.restaurant.menuservice.dto.CategoryDetailResponseDTO;

import java.util.List;

public interface CategoryService {

    void createCategory(CategoryCreateRequestDTO dto);

    List<CategoryDetailResponseDTO> getAllCategories();

    CategoryDetailResponseDTO getCategoryById(Long id);

    void updateCategory(Long id, CategoryCreateRequestDTO dto);

    void deleteCategory(Long id);

    /**
     * Returns a lightweight list of categories for dropdown selectors.
     */
    List<CategoryBasicDTO> getBasicCategories();
}
