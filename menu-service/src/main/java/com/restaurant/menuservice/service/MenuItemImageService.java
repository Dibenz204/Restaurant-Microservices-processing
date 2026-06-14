package com.restaurant.menuservice.service;

import com.restaurant.menuservice.dto.MenuItemImageRequestDTO;
import com.restaurant.menuservice.dto.MenuItemImageResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MenuItemImageService {

    void createImage(MenuItemImageRequestDTO dto, MultipartFile file);

    List<MenuItemImageResponseDTO> getAllImages();

    MenuItemImageResponseDTO getImageById(Long id);

    void updateImage(Long id, MenuItemImageRequestDTO dto, MultipartFile file);

    void deleteImage(Long id);

    List<MenuItemImageResponseDTO> getImagesByMenuItemId(Long menuItemId);
}
