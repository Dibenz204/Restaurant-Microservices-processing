package com.restaurant.menuservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "menu_item_images")
public class MenuItemImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_image_id")
    private Long menuItemImageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;

    @Column(name = "url_img", columnDefinition = "TEXT", unique = true)
    private String urlImg;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Column(nullable = false)
    private Integer status;

    // ==================== Constructors ====================

    public MenuItemImage(MenuItem menuItem, String urlImg, String note, Integer status) {
        this.menuItem = menuItem;
        this.urlImg = urlImg;
        this.note = note;
        this.status = status;
    }

    // ==================== toString ====================

    @Override
    public String toString() {
        return "MenuItemImage{" +
                "menuItemImageId=" + menuItemImageId +
                ", menuItemId=" + (menuItem != null ? menuItem.getMenuItemId() : null) +
                ", urlImg='" + urlImg + '\'' +
                ", status=" + status +
                '}';
    }
}
