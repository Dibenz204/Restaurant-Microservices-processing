package com.restaurant.menuservice.controller;

import com.restaurant.menuservice.dto.MenuItemCreateRequestDTO;
import com.restaurant.menuservice.dto.MenuItemDetailResponseDTO;
import com.restaurant.menuservice.service.MenuItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @PostMapping
    public ResponseEntity<Void> createMenuItem(@Valid @RequestBody MenuItemCreateRequestDTO dto) {
        menuItemService.createMenuItem(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<MenuItemDetailResponseDTO>> getAllMenuItems() {
        List<MenuItemDetailResponseDTO> items = menuItemService.getAllMenuItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemDetailResponseDTO> getMenuItemById(@PathVariable Long id) {
        MenuItemDetailResponseDTO item = menuItemService.getMenuItemById(id);
        return ResponseEntity.ok(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMenuItem(@PathVariable Long id,
                                               @Valid @RequestBody MenuItemCreateRequestDTO dto) {
        menuItemService.updateMenuItem(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }
}
