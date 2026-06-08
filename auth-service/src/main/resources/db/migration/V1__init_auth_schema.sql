-- ================================================================
-- users table: stores authentication and user profile information
-- ================================================================

CREATE TABLE IF NOT EXISTS users (
    user_id     BIGSERIAL       PRIMARY KEY,
    full_name   VARCHAR(255),
    address     TEXT,
    username    VARCHAR(50),
    password    TEXT,
    note        TEXT,
    status      INTEGER         NOT NULL
);

CREATE TABLE IF NOT EXISTS user_contact (
    user_contact_id  BIGSERIAL    PRIMARY KEY,
    user_id          BIGINT       NOT NULL REFERENCES users(user_id),
    contact_type     VARCHAR(255),
    contact_value    VARCHAR(255),
    is_primary       BOOLEAN      NOT NULL
    );

CREATE TABLE IF NOT EXISTS roles (
    role_id     BIGSERIAL       PRIMARY KEY,
    name        VARCHAR(255)    NOT NULL UNIQUE,
    description TEXT
    );

CREATE TABLE IF NOT EXISTS user_roles (
    id          BIGSERIAL       PRIMARY KEY,
    user_id     BIGINT          NOT NULL REFERENCES users(user_id),
    role_id     BIGINT          NOT NULL REFERENCES roles(role_id),
    note TEXT
    );

CREATE TABLE IF NOT EXISTS refresh_tokens (
    id           BIGSERIAL        PRIMARY KEY,
    user_id      BIGINT           NOT NULL REFERENCES users(user_id),
    token        VARCHAR(500),
    expiry_date  TIMESTAMP        NOT NULL
    );
