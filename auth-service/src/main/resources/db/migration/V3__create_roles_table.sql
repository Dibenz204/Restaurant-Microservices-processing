-- ================================================================
-- roles table: stores user roles for authorization
-- ================================================================

CREATE TABLE IF NOT EXISTS roles (
    role_id     BIGSERIAL       PRIMARY KEY,
    name        VARCHAR(255)    NOT NULL UNIQUE,
    description TEXT
);
