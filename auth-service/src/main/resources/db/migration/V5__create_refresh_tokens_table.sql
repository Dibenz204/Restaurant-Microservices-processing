-- ================================================================
-- refresh_tokens table: stores JWT refresh tokens for users
-- ================================================================

CREATE TABLE IF NOT EXISTS refresh_tokens (
    id           BIGSERIAL        PRIMARY KEY,
    user_id      BIGINT           NOT NULL REFERENCES users(user_id),
    token        VARCHAR(500),
    expiry_date  TIMESTAMP        NOT NULL
);
