package com.restaurant.menuservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class MenuItemCreateRequestDTO {

    @NotBlank(message = "Tên món ăn không được để trống")
    private String name;

    @NotNull(message = "Giá món ăn không được để trống")
    @Min(value = 0, message = "Giá món ăn phải lớn hơn hoặc bằng 0")
    private BigDecimal price;

    private String description;

    @NotNull(message = "Trạng thái hiển thị (available) phải có")
    private Boolean available;

    // Admin truyền lên danh sách ID danh mục mà món này thuộc về (ví dụ: [1, 3])
    @NotEmpty(message = "Món ăn phải thuộc ít nhất một danh mục")
    private List<Long> categoryIds;

    // Admin truyền lên danh sách chuỗi URL hình ảnh của món ăn
    private List<String> imageUrls;
}