package com.restaurant.menuservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItemImageRequestDTO {

    @NotNull(message = "Menu item ID không được để trống")
    private Long menuItemId;

//    @NotBlank(message = "URL hình ảnh không được để trống")
//    private String urlImg;

    private String note;

    @NotNull(message = "Trạng thái ảnh phải có")
    private Integer status;
}
