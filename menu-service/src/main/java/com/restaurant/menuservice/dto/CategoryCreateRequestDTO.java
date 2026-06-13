package com.restaurant.menuservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryCreateRequestDTO {

    @NotBlank(message = "Tên danh mục không được để trống")
    private String name;

    private String note;

    @NotNull(message = "Trạng thái danh mục phải có")
    private Integer status;
}
