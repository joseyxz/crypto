INSERT INTO User_Info (user_name) VALUES ('USER1');

INSERT INTO Wallet (user_id, currency_id, balance) VALUES (1, 1, 50000);

INSERT INTO Transaction_History (user_id, trans_date, trans_type, currency_id, amount) VALUES (1, CURRENT_TIMESTAMP(), 'BUY', 1, 50000);

INSERT INTO Currency (symbol, bid_price, ask_price) VALUES ('USDT', 1, 1);