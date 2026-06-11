package com.restaurant.orderservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(nullable = true)
    private Long customerId;

    @Column(nullable = true)
    private Long createdBy;

    @Column(nullable = true)
    private Long approvedBy;

    @Column(name = "order_code", columnDefinition = "TEXT", unique = true, nullable = false)
    private String orderCode;

    @Column(name = "table_number")
    private String tableNumber;

    @Column(precision = 17, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Column(nullable = false)
    private String status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    // ==================== Constructors =====================

    public Order(Long customerId, Long createdBy, Long approvedBy, String orderCode,
                 String tableNumber, BigDecimal totalAmount, String paymentMethod,
                 String paymentStatus, String note, String status) {
        this.customerId = customerId;
        this.createdBy = createdBy;
        this.approvedBy = approvedBy;
        this.orderCode = orderCode;
        this.tableNumber = tableNumber;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.note = note;
        this.status = status;
    }

    // ==================== Lifecycle Callbacks ====================

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // ==================== toString ====================

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderCode='" + orderCode + '\'' +
                ", tableNumber='" + tableNumber + '\'' +
                ", totalAmount=" + totalAmount +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
