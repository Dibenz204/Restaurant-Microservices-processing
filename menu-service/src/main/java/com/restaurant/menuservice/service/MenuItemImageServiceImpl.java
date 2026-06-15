package com.restaurant.menuservice.service;

import com.restaurant.menuservice.dto.MenuItemImageRequestDTO;
import com.restaurant.menuservice.dto.MenuItemImageResponseDTO;
import com.restaurant.menuservice.entity.MenuItem;
import com.restaurant.menuservice.entity.MenuItemImage;
import com.restaurant.menuservice.exception.ImageNotAvailableException;
import com.restaurant.menuservice.exception.MenuNotAvailableException;
import com.restaurant.menuservice.repository.MenuItemImageRepository;
import com.restaurant.menuservice.repository.MenuItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuItemImageServiceImpl implements MenuItemImageService {

    private final MenuItemImageRepository menuItemImageRepository;
    private final MenuItemRepository menuItemRepository;
    private final AmazonS3Service amazonS3Service;

    public MenuItemImageServiceImpl(MenuItemImageRepository menuItemImageRepository,
                                    MenuItemRepository menuItemRepository, AmazonS3Service amazonS3Service) {
        this.menuItemImageRepository = menuItemImageRepository;
        this.menuItemRepository = menuItemRepository;
        this.amazonS3Service = amazonS3Service;
    }

    @Override
    @Transactional
    public void createImage(MenuItemImageRequestDTO dto, MultipartFile file) {
        MenuItem menuItem = menuItemRepository.findById(dto.getMenuItemId())
                .orElseThrow(() -> new MenuNotAvailableException("Menu item not found with id: " + dto.getMenuItemId()));

        // Đẩy dữ liệu lên AWS
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File ảnh không được để trống khi tạo mới");
        }
        String s3UrlGenerated = amazonS3Service.uploadFile(file);

        MenuItemImage image = new MenuItemImage(
                menuItem,
                s3UrlGenerated,
                dto.getNote(),
                dto.getStatus()
        );

        menuItemImageRepository.save(image);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MenuItemImageResponseDTO> getAllImages() {
        List<MenuItemImage> images = menuItemImageRepository.findAll();
        return images.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MenuItemImageResponseDTO getImageById(Long id) {
        MenuItemImage image = menuItemImageRepository.findById(id)
                .orElseThrow(() -> new ImageNotAvailableException("Image not found with id: " + id));
        return mapToResponse(image);
    }

    @Override
    @Transactional
    public void updateImage(Long id, MenuItemImageRequestDTO dto, MultipartFile file) {
        MenuItem menuItem = menuItemRepository.findById(dto.getMenuItemId())
            .orElseThrow(() -> new MenuNotAvailableException("Menu item not found with id: " + dto.getMenuItemId())); // validate menu item if changed

        MenuItemImage menuItemImage = menuItemImageRepository.findById(id)
                .orElseThrow(() -> new ImageNotAvailableException("Image not found with id: " + id));

        if (!menuItemImage.getMenuItem().getMenuItemId().equals(dto.getMenuItemId())) {
            menuItemImage.setMenuItem(menuItem);
        }

        if (file != null && !file.isEmpty()) {
            String newS3Url = amazonS3Service.uploadFile(file);
            menuItemImage.setUrlImg(newS3Url);
        }

        menuItemImage.setNote(dto.getNote());
        menuItemImage.setStatus(dto.getStatus());

        menuItemImageRepository.save(menuItemImage);
    }

    @Override
    @Transactional
    public void deleteImage(Long id) {
        MenuItemImage image = menuItemImageRepository.findById(id)
                .orElseThrow(() -> new ImageNotAvailableException("Image not found with id: " + id));
        menuItemImageRepository.delete(image);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MenuItemImageResponseDTO> getImagesByMenuItemId(Long menuItemId) {
        List<MenuItemImage> images = menuItemImageRepository.findByMenuItem_MenuItemId(menuItemId);
        return images.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private MenuItemImageResponseDTO mapToResponse(MenuItemImage image) {
        MenuItemImageResponseDTO dto = new MenuItemImageResponseDTO();
        dto.setMenuItemImageId(image.getMenuItemImageId());
        dto.setMenuItemId(image.getMenuItem().getMenuItemId());
        dto.setMenuItemName(image.getMenuItem().getName());
        dto.setUrlImg(image.getUrlImg());
        dto.setNote(image.getNote());
        dto.setStatus(image.getStatus());
        return dto;
    }
}
