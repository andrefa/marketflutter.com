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