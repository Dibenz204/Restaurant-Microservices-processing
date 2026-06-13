package com.restaurant.menuservice.service;

import com.restaurant.menuservice.dto.CategoryBasicDTO;
import com.restaurant.menuservice.dto.CategoryCreateRequestDTO;
import com.restaurant.menuservice.dto.CategoryDetailResponseDTO;
import com.restaurant.menuservice.entity.Category;
import com.restaurant.menuservice.exception.CategoryNotAvailableException;
import com.restaurant.menuservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public void createCategory(CategoryCreateRequestDTO dto) {
        Category category = new Category(
                dto.getName(),
                dto.getNote(),
                dto.getStatus()
        );
        categoryRepository.save(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDetailResponseDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::mapToDetailResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDetailResponseDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotAvailableException("Category not found with id: " + id));
        return mapToDetailResponse(category);
    }

    @Override
    @Transactional
    public void updateCategory(Long id, CategoryCreateRequestDTO dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotAvailableException("Category not found with id: " + id));

        category.setName(dto.getName());
        category.setNote(dto.getNote());
        category.setStatus(dto.getStatus());

        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotAvailableException("Category not found with id: " + id));
        categoryRepository.delete(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryBasicDTO> getBasicCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::mapToBasicDTO)
                .collect(Collectors.toList());
    }

    // ==================== Private Helpers ====================

    private CategoryDetailResponseDTO mapToDetailResponse(Category category) {
        CategoryDetailResponseDTO dto = new CategoryDetailResponseDTO();
        dto.setCategoryId(category.getCategoryId());
        dto.setName(category.getName());
        dto.setNote(category.getNote());
        dto.setStatus(category.getStatus());
//        dto.setMenuItemCount(
//                category.getMenuItemCategories() != null
//                        ? category.getMenuItemCategories().size()
//                        : 0
//        );
        return dto;
    }

    private CategoryBasicDTO mapToBasicDTO(Category category) {
        CategoryBasicDTO dto = new CategoryBasicDTO();
        dto.setCategoryId(category.getCategoryId());
        dto.setName(category.getName());
        dto.setStatus(category.getStatus());
        return dto;
    }
}
