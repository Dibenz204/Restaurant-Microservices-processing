package com.restaurant.menuservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItemImageResponseDTO {
    private Long menuItemImageId;
    private Long menuItemId;
    private String menuItemName;
    private String urlImg;
    private String note;
    private Integer status;
}
