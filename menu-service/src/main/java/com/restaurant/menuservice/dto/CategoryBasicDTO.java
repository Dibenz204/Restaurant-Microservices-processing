package com.restaurant.menuservice.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Lightweight DTO for basic category info (e.g., dropdown list).
 */
@Getter
@Setter
public class CategoryBasicDTO {
    private Long categoryId;
    private String name;
    private Integer status;
}
