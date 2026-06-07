package com.restaurant.menuservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private String category;
    private Boolean available = true;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    // Constructors
    public MenuItem() {}

    public MenuItem(String name, String description, Double price, String category, Boolean available) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.available = true;
        this.createdAt = LocalDateTime.now();
    }
}