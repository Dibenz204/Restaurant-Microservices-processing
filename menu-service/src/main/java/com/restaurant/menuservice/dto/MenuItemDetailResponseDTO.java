package com.restaurant.menuservice.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class MenuItemDetailResponseDTO {
    private Long menuItemId;
    private String name;
    private BigDecimal price;
    private String description;
    private Boolean available;
    private Integer status;

    // Trả về danh sách ảnh đã lọc gọn (chỉ lấy ID và URL)
    private List<ItemImageDTO> images;

    // Trả về danh sách thông tin danh mục (chỉ lấy ID và tên để hiển thị tag)
    private List<ItemCategoryDTO> categories;

    // --- Các Class DTO con lồng bên trong để định dạng dữ liệu trả về cho đẹp ---
    @Getter @Setter
    public static class ItemImageDTO {
        private Long imageId;
        private String urlImg;
    }

    @Getter @Setter
    public static class ItemCategoryDTO {
        private Long categoryId;
        private String name;
    }
}