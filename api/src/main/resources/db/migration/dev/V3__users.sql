-- password=f1a4821f-b53e-42a8-a1dc-451ace9f6e5b
insert into users (id, first_name, last_name, email, password)
values (1, 'Andre', 'Almeida', 'almeida.andref@gmail.com', '$2a$10$/1MejzLEmnWaHSGd7a3iE.stMMuQu99KA20CujTbWBGheXpx4UdKa');

insert into user_configs (user_id, binance_api_key, binance_secret_key, ntfy_topic_name, enable_execute_transactions)
values (1, 'e9kpXVa0r5xLkegzdmru8ukcwqPHOVr1eauLmp70WVfwTF9zmBL1SNzv1VldwpXJ', 'bjba3SIUDS4AxVUaxuAh3VCaq5liHAVpLL8wJxnw18VXa0oEzuEVg4joUbcit65B', '0542785B-3771-476A-976D-1D7ACDB02E52', 0);


-- password=566a9f4b-2a30-464f-8014-db7988ce1deb
insert into users (id, first_name, last_name, email, password)
values (2, 'Tiago', 'Cedrim', 'tiago.cedrim@gmail.com', '$2a$10$gbdGlAuMX9dD1CpPWpeQ8.CpCTprhETSnF7USLrHqdckgs4ZHlU5C');

insert into user_configs (user_id, binance_api_key, binance_secret_key, ntfy_topic_name, enable_execute_transactions)
values (2, 'e9kpXVa0r5xLkegzdmru8ukcwqPHOVr1eauLmp70WVfwTF9zmBL1SNzv1VldwpXJ', 'bjba3SIUDS4AxVUaxuAh3VCaq5liHAVpLL8wJxnw18VXa0oEzuEVg4joUbcit65B', '0542785B-3771-476A-976D-1D7ACDB02E52', 0);



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
