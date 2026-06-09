package com.restaurant.orderservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long orderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "menu_item_id", nullable = false)
    private Long menuItemId;

    @Column(precision = 17, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantity;

    @Column(precision = 17, scale = 2)
    private BigDecimal subtotal;

    @Column(columnDefinition = "TEXT")
    private String note;

    // ==================== Constructors ====================

    public OrderItem(Order order, Long menuItemId, BigDecimal price,
                     Integer quantity, BigDecimal subtotal, String note) {
        this.order = order;
        this.menuItemId = menuItemId;
        this.price = price;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.note = note;
    }

    // ==================== toString ====================

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", menuItemId=" + menuItemId +
                ", price=" + price +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                '}';
    }
}
