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
