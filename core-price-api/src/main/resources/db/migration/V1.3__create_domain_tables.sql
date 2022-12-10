
CREATE TABLE IF NOT EXISTS `BRANDS`
(
    `id`   int auto_increment  primary key,
    `name` varchar(20) null
);

CREATE TABLE IF NOT EXISTS `PRODUCTS`
(
    `id`   int auto_increment  primary key,
    `name` varchar(20) null
    );


CREATE TABLE IF NOT EXISTS `PRICE_LISTS`
(
    `id`   int auto_increment  primary key,
    `brand_id` int    not null,
    `product_id` int    not null,
    `start_date` timestamp    not null,
    `end_date` timestamp    not null,
    `priority` int    not null,
    `price` double    not null,
    `curr` varchar(3)    not null,
     constraint fk_brand  foreign key (brand_id) references `BRANDS` (id),
     constraint fk_product  foreign key (product_id) references `PRODUCTS` (id)
);