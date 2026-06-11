-- ----------------------------
-- 1. categories
-- ----------------------------
CREATE TABLE IF NOT EXISTS categories (
    category_id BIGSERIAL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL UNIQUE,
    note       TEXT,
    status     INTEGER NOT NULL
    );

-- ----------------------------
-- 2. menu_items
-- ----------------------------
CREATE TABLE IF NOT EXISTS menu_items (
    menu_item_id BIGSERIAL PRIMARY KEY,
    name         VARCHAR(255) NOT NULL UNIQUE,
    price        DECIMAL(17, 2),
    description  TEXT,
    available    BOOLEAN NOT NULL,
    status       INTEGER NOT NULL
    );

-- ----------------------------
-- 3. menu_item_categories
-- ----------------------------
CREATE TABLE IF NOT EXISTS menu_item_categories (
    id           BIGSERIAL PRIMARY KEY,
    category_id  BIGINT NOT NULL,
    menu_item_id BIGINT NOT NULL,
    status       INTEGER NOT NULL,
    CONSTRAINT fk_menu_item_categories_category
    FOREIGN KEY (category_id)
    REFERENCES categories (category_id)
    ON DELETE CASCADE,
    CONSTRAINT fk_menu_item_categories_menu_item
    FOREIGN KEY (menu_item_id)
    REFERENCES menu_items (menu_item_id)
    ON DELETE CASCADE
    );

-- ----------------------------
-- 4. menu_item_images
-- ----------------------------
CREATE TABLE IF NOT EXISTS menu_item_images (
    menu_item_image_id BIGSERIAL PRIMARY KEY,
    menu_item_id       BIGINT NOT NULL,
    url_img            TEXT UNIQUE,
    note               TEXT,
    status             INTEGER NOT NULL,
    CONSTRAINT fk_menu_item_images_menu_item
    FOREIGN KEY (menu_item_id)
    REFERENCES menu_items (menu_item_id)
    ON DELETE CASCADE
    );
