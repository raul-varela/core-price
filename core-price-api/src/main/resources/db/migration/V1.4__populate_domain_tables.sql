INSERT INTO `PRODUCTS` (id,name) VALUES (1,'Product 1');
INSERT INTO `PRODUCTS` (id,name) VALUES (2,'Product 2');
INSERT INTO `PRODUCTS` (id,name) VALUES (35455,'Product 35455');


INSERT INTO `BRANDS` (id,name) VALUES (1,'ZARA');
INSERT INTO `BRANDS` (id,name) VALUES (2,'BRANDS 2');
INSERT INTO `BRANDS` (id,name) VALUES (3,'BRANDS 3');

INSERT  INTO `PRICE_LISTS` (id, brand_id, product_id, start_date, end_date, priority, price, curr)
        VALUES (1, 1, 35455, '2020-06-14 00.00.00', '2020-12-31 23.59.59', 0, 35.50 ,'EUR');

INSERT  INTO `PRICE_LISTS` (id, brand_id, product_id, start_date, end_date, priority, price, curr)
        VALUES (2, 1, 35455, '2020-06-14 15.00.00', '2020-06-14 18.30.00', 1, 25.45, 'EUR');

INSERT  INTO `PRICE_LISTS` (id, brand_id, product_id, start_date, end_date, priority, price, curr)
        VALUES (3, 1, 35455, '2020-06-15 00.00.00', '2020-06-15 11.00.00', 1, 30.50, 'EUR');

INSERT  INTO `PRICE_LISTS` (id, brand_id, product_id, start_date, end_date, priority, price, curr)
        VALUES (4, 1, 35455, '2020-06-15 16.00.00', '2020-12-31 23.59.59', 1, 38.95, 'EUR');