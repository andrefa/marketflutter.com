-- USER 1
insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (1, 3, 25000.0, 0.05, 0.001, 0.06, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (1, 1, 1, 1, 'DEFAULT'); -- BTC

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (2, 3, 1700, 0.05, 0.01, 0.06, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (2, 1, 2, 2, 'DEFAULT'); -- ETH

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (3, 3, 316, 0.05, 0.1, 0.06, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (3, 1, 4, 3, 'DEFAULT'); -- BNB

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (4, 3, 0.39, 0.1, 100, 0.11, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (4, 1, 5, 4, 'DEFAULT'); -- XRP

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (5, 3, 0.4, 0.1, 100, 0.11, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (5, 1, 6, 5, 'DEFAULT'); -- ADA

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (6, 3, 1.54, 0.1, 30, 0.11, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (6, 1, 7, 6, 'DEFAULT'); -- MATIC

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (7, 3, 0.088, 0.20, 100, 0.25, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (7, 1, 9, 7, 'DEFAULT'); -- DOGE

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (8, 3, 23.41, 0.05, 2, 0.07, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (8, 1, 10, 8, 'DEFAULT'); -- SOL

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (9, 3, 7.27, 0.05, 5, 0.07, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (9, 1, 11, 9, 'DEFAULT'); -- DOT

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (10, 3, 0.00001332, 0.25, 100000, 0.30, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (10, 1, 12, 10, 'DEFAULT'); -- SHIB


-- USER 2
insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (11, 3, 25000.0, 0.05, 0.001, 0.06, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (11, 2, 1, 11, 'DEFAULT'); -- BTC

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (12, 3, 1700, 0.05, 0.01, 0.06, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (12, 2, 2, 12, 'DEFAULT'); -- ETH

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (13, 3, 316, 0.05, 0.1, 0.06, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (13, 2, 4, 13, 'DEFAULT'); -- BNB

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (14, 3, 0.39, 0.1, 100, 0.11, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (14, 2, 5, 14, 'DEFAULT'); -- XRP

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (15, 3, 0.4, 0.1, 100, 0.11, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (15, 2, 6, 15, 'DEFAULT'); -- ADA

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (16, 3, 1.54, 0.1, 30, 0.11, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (16, 2, 7, 16, 'DEFAULT'); -- MATIC

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (17, 3, 0.088, 0.20, 100, 0.25, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (17, 2, 9, 17, 'DEFAULT'); -- DOGE

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (18, 3, 23.41, 0.05, 2, 0.07, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (18, 2, 10, 18, 'DEFAULT'); -- SOL

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (19, 3, 7.27, 0.05, 5, 0.07, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (19, 2, 11, 19, 'DEFAULT'); -- DOT

insert into asset_configs (id, exchange_coin_id, base_price, buying_percentage_threshold, buying_amount, selling_percentage_threshold, enable_buy, enable_sell, max_holding_transactions)
values (20, 3, 0.00001332, 0.25, 100000, 0.30, 1, 1, 3);
insert into assets (id, user_id, coin_id, asset_config_id, trade_strategy)
values (20, 2, 12, 20, 'DEFAULT'); -- SHIB