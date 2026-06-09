-- ========================================
-- V1: Initial schema for order-service
-- Tables: orders, order_items
-- ========================================

CREATE TABLE IF NOT EXISTS orders (
    order_id       BIGSERIAL PRIMARY KEY,
    customer_id    BIGINT,
    created_by     BIGINT,
    approved_by    BIGINT       NOT NULL,
    order_code     TEXT         NOT NULL UNIQUE,
    table_number   VARCHAR(255),
    total_amount   DECIMAL(17, 2),
    payment_method VARCHAR(255),
    payment_status VARCHAR(255),
    note           TEXT,
    status         VARCHAR(255) NOT NULL,
    created_at     TIMESTAMP    NOT NULL,
    updated_at     TIMESTAMP    NOT NULL
);

CREATE TABLE IF NOT EXISTS order_items (
    order_item_id BIGSERIAL PRIMARY KEY,
    order_id      BIGINT        NOT NULL,
    menu_item_id  BIGINT        NOT NULL,
    price         DECIMAL(17, 2),
    quantity      INTEGER       NOT NULL,
    subtotal      DECIMAL(17, 2),
    note          TEXT,
    CONSTRAINT fk_order_items_order
        FOREIGN KEY (order_id)
        REFERENCES orders (order_id)
        ON DELETE CASCADE
);
