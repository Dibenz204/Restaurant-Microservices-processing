

CREATE TABLE IF NOT EXISTS user_contact (
    user_contact_id  BIGSERIAL    PRIMARY KEY,
    user_id          BIGINT       NOT NULL REFERENCES users(user_id),
    contact_type     VARCHAR(255),
    contact_value    VARCHAR(255),
    is_primary       BOOLEAN      NOT NULL
);