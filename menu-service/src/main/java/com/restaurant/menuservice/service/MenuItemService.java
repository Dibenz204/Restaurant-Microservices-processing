package com.restaurant.menuservice.service;

import com.restaurant.menuservice.dto.MenuItemCreateRequestDTO;
import com.restaurant.menuservice.dto.MenuItemDetailResponseDTO;

import java.util.List;

public interface MenuItemService {
    void createMenuItem(MenuItemCreateRequestDTO dto);

    List<MenuItemDetailResponseDTO> getAllMenuItems();

    MenuItemDetailResponseDTO getMenuItemById(Long id);

    void updateMenuItem(Long id, MenuItemCreateRequestDTO dto);

    void deleteMenuItem(Long id);
}