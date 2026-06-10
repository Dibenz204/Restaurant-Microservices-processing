package com.restaurant.sharemodules.dto; // Thuộc module shared ngoài cùng

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class SimpleMenuItemDTO {
    private Long menuItemId;
    private String name;
    private BigDecimal price;
    private Boolean available;
    private Integer status;
}