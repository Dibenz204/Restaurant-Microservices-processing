package com.restaurant.menuservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_id")
    private Long menuItemId;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(precision = 17, scale = 2)
    private BigDecimal price;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Boolean available;

    @Column(nullable = false)
    private Integer status;

    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MenuItemImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MenuItemCategory> menuItemCategories = new ArrayList<>();

    // ==================== Constructors ====================

    public MenuItem(String name, BigDecimal price, String description, Boolean available, Integer status) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.available = available;
        this.status = status;
    }

    // ==================== toString ====================

    @Override
    public String toString() {
        return "MenuItem{" +
                "menuItemId=" + menuItemId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", available=" + available +
                ", status=" + status +
                '}';
    }
}