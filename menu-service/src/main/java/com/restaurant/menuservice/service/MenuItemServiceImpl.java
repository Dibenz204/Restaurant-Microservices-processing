package com.restaurant.menuservice.service;

import com.restaurant.menuservice.dto.MenuItemCreateRequestDTO;
import com.restaurant.menuservice.dto.MenuItemDetailResponseDTO;
import com.restaurant.menuservice.entity.Category;
import com.restaurant.menuservice.entity.MenuItem;
import com.restaurant.menuservice.entity.MenuItemCategory;
import com.restaurant.menuservice.entity.MenuItemImage;
import com.restaurant.menuservice.exception.GlobalExceptionHandler;
import com.restaurant.menuservice.exception.MenuNotAvailableException;
import com.restaurant.menuservice.repository.CategoryRepository;
import com.restaurant.menuservice.repository.MenuItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final CategoryRepository categoryRepository;

    public MenuItemServiceImpl(MenuItemRepository menuItemRepository,
                               CategoryRepository categoryRepository) {
        this.menuItemRepository = menuItemRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public void createMenuItem(MenuItemCreateRequestDTO dto) {
        // 1. Map DTO -> Entity MenuItem
        MenuItem menuItem = new MenuItem(
                dto.getName(),
                dto.getPrice(),
                dto.getDescription(),
                dto.getAvailable(),
                1
        );

        // 2. Xử lý danh sách ảnh
        if (dto.getImageUrls() != null) {
            for (String url : dto.getImageUrls()) {
                MenuItemImage image = new MenuItemImage(menuItem, url, null, 1);
                menuItem.getImages().add(image);
            }
        }

        // 3. Xử lý danh sách danh mục
        if (dto.getCategoryIds() != null) {
            List<Category> categories = categoryRepository.findAllById(dto.getCategoryIds());
            for (Category category : categories) {
                MenuItemCategory mic = new MenuItemCategory(category, menuItem, 1);
                menuItem.getMenuItemCategories().add(mic);
            }
        }

        menuItemRepository.save(menuItem);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MenuItemDetailResponseDTO> getAllMenuItems() {
        List<MenuItem> menuItems = menuItemRepository.findAll();
        return menuItems.stream()
                .map(this::mapToDetailResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MenuItemDetailResponseDTO getMenuItemById(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new MenuNotAvailableException("Menu item not found with id: " + id));
        return mapToDetailResponse(menuItem);
    }

    @Override
    @Transactional
    public void updateMenuItem(Long id, MenuItemCreateRequestDTO dto) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new MenuNotAvailableException("Menu item not found with id: " + id));

        menuItem.setName(dto.getName());
        menuItem.setPrice(dto.getPrice());
        menuItem.setDescription(dto.getDescription());
        menuItem.setAvailable(dto.getAvailable());

        if (dto.getImageUrls() != null) {
            // 1. Xóa sạch bách danh sách ảnh cũ trong bộ nhớ tạm
            menuItem.getImages().clear();

            // 2. Nạp toàn bộ danh sách ảnh mới từ DTO vào
            for (String url : dto.getImageUrls()) {
                MenuItemImage image = new MenuItemImage(menuItem, url, null, 1);
                menuItem.getImages().add(image);
            }
        }

        if (dto.getCategoryIds() != null) {
            menuItem.getMenuItemCategories().clear();

            List<Category> categories = categoryRepository.findAllById(dto.getCategoryIds());
            for (Category category : categories) {
                MenuItemCategory mic = new MenuItemCategory(category, menuItem, 1);
                menuItem.getMenuItemCategories().add(mic);
            }
        }

        menuItemRepository.save(menuItem);
    }

    @Override
    @Transactional
    public void deleteMenuItem(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new MenuNotAvailableException("Menu item not found with id: " + id));
        menuItemRepository.delete(menuItem);
    }

    // ==================== Private Helper ====================

    private MenuItemDetailResponseDTO mapToDetailResponse(MenuItem menuItem) {
        MenuItemDetailResponseDTO dto = new MenuItemDetailResponseDTO();
        dto.setMenuItemId(menuItem.getMenuItemId());
        dto.setName(menuItem.getName());
        dto.setPrice(menuItem.getPrice());
        dto.setDescription(menuItem.getDescription());
        dto.setAvailable(menuItem.getAvailable());
        dto.setStatus(menuItem.getStatus());

        List<MenuItemDetailResponseDTO.ItemImageDTO> imageDTOs = menuItem.getImages().stream()
                .map(img -> {
                    MenuItemDetailResponseDTO.ItemImageDTO imgDTO = new MenuItemDetailResponseDTO.ItemImageDTO();
                    imgDTO.setImageId(img.getMenuItemImageId());
                    imgDTO.setUrlImg(img.getUrlImg());
                    return imgDTO;
                })
                .collect(Collectors.toList());
        dto.setImages(imageDTOs);

        List<MenuItemDetailResponseDTO.ItemCategoryDTO> categoryDTOs = menuItem.getMenuItemCategories().stream()
                .map(mic -> {
                    MenuItemDetailResponseDTO.ItemCategoryDTO catDTO = new MenuItemDetailResponseDTO.ItemCategoryDTO();
                    catDTO.setCategoryId(mic.getCategory().getCategoryId());
                    catDTO.setName(mic.getCategory().getName());
                    return catDTO;
                })
                .collect(Collectors.toList());
        dto.setCategories(categoryDTOs);

        return dto;
    }
}
