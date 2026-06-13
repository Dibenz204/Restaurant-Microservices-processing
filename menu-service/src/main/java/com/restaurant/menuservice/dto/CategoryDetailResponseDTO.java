package com.restaurant.menuservice.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryDetailResponseDTO {
    private Long categoryId;
    private String name;
    private String note;
    private Integer status;
}
