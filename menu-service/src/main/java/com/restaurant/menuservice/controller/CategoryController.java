package com.restaurant.menuservice.controller;

import com.restaurant.menuservice.dto.CategoryBasicDTO;
import com.restaurant.menuservice.dto.CategoryCreateRequestDTO;
import com.restaurant.menuservice.dto.CategoryDetailResponseDTO;
import com.restaurant.menuservice.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Void> createCategory(@Valid @RequestBody CategoryCreateRequestDTO dto) {
        categoryService.createCategory(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryDetailResponseDTO>> getAllCategories() {
        List<CategoryDetailResponseDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDetailResponseDTO> getCategoryById(@PathVariable Long id) {
        CategoryDetailResponseDTO category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable Long id,
                                               @Valid @RequestBody CategoryCreateRequestDTO dto) {
        categoryService.updateCategory(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/basic")
    public ResponseEntity<List<CategoryBasicDTO>> getBasicCategories() {
        List<CategoryBasicDTO> categories = categoryService.getBasicCategories();
        return ResponseEntity.ok(categories);
    }
}
