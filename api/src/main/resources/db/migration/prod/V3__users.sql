insert into users (id, first_name, last_name, email, password)
values (1, 'Andre', 'Almeida', 'almeida.andref@gmail.com', '$2a$10$fjCmIp64sLfZyMwdM/q6yurwMdKrrzSLuWO1Q4Kr4SQGHyY92VTk6');

insert into user_configs (user_id, binance_api_key, binance_secret_key, ntfy_topic_name, enable_execute_transactions)
values (1, null, null, '0542785B-3771-476A-976D-1D7ACDB02E52', 0);


insert into users (id, first_name, last_name, email, password)
values (2, 'Tiago', 'Cedrim', 'tiago.cedrim@gmail.com', '$2a$10$e.w7Qur3zmi4QC/EV7uGL.3Ti7AjX/RN60zAgGXANySckRFla5Kk.');

insert into user_configs (user_id, binance_api_key, binance_secret_key, ntfy_topic_name, enable_execute_transactions)
values (2, null, null, null, 0);
