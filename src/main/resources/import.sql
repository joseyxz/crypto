INSERT INTO User_Info (id, user_name) VALUES (1, 'USER1');

INSERT INTO Wallet (id, user_id, currency_id, balance) VALUES (1, 1, 1, 50000);

INSERT INTO Transaction_History (id, user_id, trans_date, trans_type, currency_id, amount) VALUES (1, 1, CURRENT_TIMESTAMP(), 'BUY', 1, 50000);

INSERT INTO Currency (id, symbol, bid_price, sell_price) VALUES (1, 'USDT', 1, 1);
INSERT INTO Currency (id, symbol, bid_price, sell_price) VALUES (2, 'ETHUSDT', 0, 0);
INSERT INTO Currency (id, symbol, bid_price, sell_price) VALUES (3, 'BTCUSDT', 0, 0);