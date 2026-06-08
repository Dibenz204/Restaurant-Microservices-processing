-- ================================================================
-- user_roles table: maps users to roles (with extra attributes)
-- ================================================================

CREATE TABLE IF NOT EXISTS user_roles (
    id          BIGSERIAL       PRIMARY KEY,
    user_id     BIGINT          NOT NULL REFERENCES users(user_id),
    role_id     BIGINT          NOT NULL REFERENCES roles(role_id),
    note TEXT
);
