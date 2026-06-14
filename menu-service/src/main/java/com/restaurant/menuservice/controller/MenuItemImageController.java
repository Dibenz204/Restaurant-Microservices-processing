package com.restaurant.menuservice.controller;

import com.restaurant.menuservice.dto.MenuItemImageRequestDTO;
import com.restaurant.menuservice.dto.MenuItemImageResponseDTO;
import com.restaurant.menuservice.service.MenuItemImageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/menu-item-images")
public class MenuItemImageController {

    private final MenuItemImageService menuItemImageService;

    public MenuItemImageController(MenuItemImageService menuItemImageService) {
        this.menuItemImageService = menuItemImageService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createImage(
            @Valid @RequestPart("info") MenuItemImageRequestDTO dto,
            @RequestPart("file") MultipartFile file) {

        menuItemImageService.createImage(dto, file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<MenuItemImageResponseDTO>> getAllImages() {
        List<MenuItemImageResponseDTO> images = menuItemImageService.getAllImages();
        return ResponseEntity.ok(images);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemImageResponseDTO> getImageById(@PathVariable Long id) {
        MenuItemImageResponseDTO image = menuItemImageService.getImageById(id);
        return ResponseEntity.ok(image);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateImage(
            @PathVariable Long id,
            @Valid @RequestPart("info") MenuItemImageRequestDTO dto,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        menuItemImageService.updateImage(id, dto, file);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        menuItemImageService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-menu-item/{menuItemId}")
    public ResponseEntity<List<MenuItemImageResponseDTO>> getImagesByMenuItemId(@PathVariable Long menuItemId) {
        List<MenuItemImageResponseDTO> images = menuItemImageService.getImagesByMenuItemId(menuItemId);
        return ResponseEntity.ok(images);
    }
}
