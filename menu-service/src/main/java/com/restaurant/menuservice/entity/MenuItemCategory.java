package com.restaurant.menuservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "menu_item_categories")
public class MenuItemCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;

    @Column(nullable = false)
    private Integer status;

    // ==================== Constructors ====================

    public MenuItemCategory(Category category, MenuItem menuItem, Integer status) {
        this.category = category;
        this.menuItem = menuItem;
        this.status = status;
    }

    // ==================== toString ====================

    @Override
    public String toString() {
        return "MenuItemCategory{" +
                "id=" + id +
                ", categoryId=" + (category != null ? category.getCategoryId() : null) +
                ", menuItemId=" + (menuItem != null ? menuItem.getMenuItemId() : null) +
                ", status=" + status +
                '}';
    }
}
