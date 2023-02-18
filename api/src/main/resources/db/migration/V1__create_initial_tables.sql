CREATE TABLE users 
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY `uk_users_email` (`email`)
) charset = UTF8MB4;

CREATE TABLE user_configs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    binance_api_key VARCHAR(255) NULL,
    binance_secret_key VARCHAR(255) NULL,
    ntfy_topic_name VARCHAR(255) NULL,
    enable_execute_transactions TINYINT NOT NULL DEFAULT 0,
    create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    UNIQUE KEY `uk_user_configs_user` (`user_id`)
) charset = UTF8MB4;

CREATE TABLE coins 
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    crypto_name VARCHAR(255) NOT NULL,
    crypto_code VARCHAR(255) NOT NULL,
    interest_level varchar(30) NOT NULL DEFAULT 'LOW',
    create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY `uk_assets_crypto_code` (`crypto_code`)
) charset = UTF8MB4;

CREATE TABLE asset_configs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    exchange_coin_id BIGINT NOT NULL,
    base_price DECIMAL(64,30) NOT NULL,
    last_seen_price DECIMAL(64,30) NULL,
    buying_percentage_threshold DECIMAL(10,5) NOT NULL,
    buying_amount DECIMAL(64,30) NOT NULL,
    selling_percentage_threshold DECIMAL(10,5) NOT NULL,
    enable_buy TINYINT NOT NULL DEFAULT 0,
    enable_sell TINYINT NOT NULL DEFAULT 0,
    max_holding_transactions TINYINT NOT NULL DEFAULT 1,
    create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (exchange_coin_id) REFERENCES coins(id)
) charset = UTF8MB4;

CREATE TABLE assets 
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    coin_id BIGINT NOT NULL,
    asset_config_id BIGINT NOT NULL,
    trade_strategy VARCHAR(255) NOT NULL,
    create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY `uk_user_coin_assets` (`user_id`, `coin_id`),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (coin_id) REFERENCES coins(id),
    FOREIGN KEY (asset_config_id) REFERENCES asset_configs(id)
) charset = UTF8MB4;

CREATE TABLE asset_transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    asset_id BIGINT NOT NULL,
    coin_in_id BIGINT NOT NULL,
    coin_out_id BIGINT NOT NULL,
    asset_config_id BIGINT NULL,
    transaction_type VARCHAR(20) NOT NULL,
    purchase_price DECIMAL(64, 30) NOT NULL,
    amount DECIMAL(64,30) NOT NULL,
    transaction_status VARCHAR(20) NOT NULL,
    create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (asset_config_id) REFERENCES asset_configs(id),
    FOREIGN KEY (coin_in_id) REFERENCES coins(id),
    FOREIGN KEY (coin_out_id) REFERENCES coins(id),
    FOREIGN KEY (asset_id) REFERENCES assets(id)
) charset = UTF8MB4;
