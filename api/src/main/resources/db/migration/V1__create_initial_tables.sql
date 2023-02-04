CREATE TABLE users 
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    create_date DATETIME default CURRENT_TIMESTAMP null,
    update_date DATETIME default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    UNIQUE KEY `uk_users_email` (`email`)
) charset = UTF8MB4;

CREATE TABLE user_configs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    binance_api_key VARCHAR(255) NULL,
    binance_secret_key VARCHAR(255) NULL,
    ntfy_topic_name VARCHAR(255) NULL,
    enable_execute_transactions TINYINT NOT NULL DEFAULT 0,
    create_date DATETIME default CURRENT_TIMESTAMP null,
    update_date DATETIME default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    UNIQUE KEY `uk_user_configs_user` (`user_id`)
) charset = UTF8MB4;

CREATE TABLE asset_configs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    base_price DECIMAL(20, 20) NOT NULL,
    buying_threshold DECIMAL(4,3) NOT NULL,
    selling_threshold DECIMAL(4,3) NOT NULL,
    enable_buy TINYINT NOT NULL DEFAULT 0,
    enable_sell TINYINT NOT NULL DEFAULT 0,
    dips_before_buying TINYINT NOT NULL,
    create_date DATETIME default CURRENT_TIMESTAMP null,
    update_date DATETIME default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
) charset = UTF8MB4;

CREATE TABLE assets 
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    asset_config_id BIGINT NOT NULL,
    crypto_name VARCHAR(255) NOT NULL,
    create_date DATETIME default CURRENT_TIMESTAMP null,
    update_date DATETIME default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    UNIQUE KEY `uk_assets_crypto_name` (`crypto_name`),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (asset_config_id) REFERENCES asset_configs(id)
) charset = UTF8MB4;

CREATE TABLE asset_transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    asset_id BIGINT NOT NULL,
    asset_config_id BIGINT NULL,
    transaction_type VARCHAR(20) NOT NULL,
    purchase_price DECIMAL(20, 20) NOT NULL,
    amount DECIMAL(20, 20) NOT NULL,
    create_date DATETIME default CURRENT_TIMESTAMP null,
    update_date DATETIME default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
) charset = UTF8MB4;