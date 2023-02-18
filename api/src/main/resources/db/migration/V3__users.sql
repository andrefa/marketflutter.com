insert into users (id, first_name, last_name, email, password)
values (1, 'Andre', 'Almeida', 'almeida.andref@gmail.com', '$2a$10$bSr91wtiesj.vWW5pazMAeo4QMj/PXc5k5SpoZIrBqKFDfta6JH1O');

insert into user_configs (user_id, binance_api_key, binance_secret_key, ntfy_topic_name)
values (1, 'oxob8Fwc8Zg83hKw4VremmX4ZC5czDwifjoDicVGtw96OQuuRYRO6hWP57lDn6yP', 'hXIu6KRBZOKKHKglsHFjhshlsudEbOLF4P8q9FCO1aTOxqGRni0YV5aXnrvOK8gU', '0542785B-3771-476A-976D-1D7ACDB02E52');

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (1, 3, 25000.0, 0.05, 0.001, 0.06, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (1, 1, 1, 1, 'DEFAULT'); -- BTC

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (2, 3, 1700, 0.05, 0.01, 0.06, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (2, 1, 2, 1, 'DEFAULT'); -- ETH

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (3, 3, 316, 0.05, 0.1, 0.06, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (3, 1, 4, 1, 'DEFAULT'); -- BNB

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (4, 3, 0.39, 0.1, 100, 0.11, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (4, 1, 5, 1, 'DEFAULT'); -- XRP

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (5, 3, 0.4, 0.1, 100, 0.11, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (5, 1, 6, 1, 'DEFAULT'); -- ADA

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (6, 3, 1.54, 0.1, 30, 0.11, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (6, 1, 7, 1, 'DEFAULT'); -- MATIC

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (7, 3, 0.088, 0.20, 100, 0.25, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (7, 1, 9, 1, 'DEFAULT'); -- DOGE

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (8, 3, 23.41, 0.05, 2, 0.07, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (8, 1, 10, 1, 'DEFAULT'); -- SOL

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (9, 3, 7.27, 0.05, 5, 0.07, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (9, 1, 11, 1, 'DEFAULT'); -- DOT

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (10, 3, 0.00001332, 0.25, 100000, 0.30, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (10, 1, 12, 1, 'DEFAULT'); -- SHIB
