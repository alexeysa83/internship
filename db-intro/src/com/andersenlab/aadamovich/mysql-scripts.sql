# Questions:
# 1) Need unsigned for primary?
# 2) Default usage?
# 3) Varchar vs char
# 4) Directories for postal codes, cities, countries?

# DATABASE CREATION
CREATE DATABASE grocery_sql;

# CATEGORY TABLE
CREATE TABLE category
(
    id                   INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    category_name        VARCHAR(128) NOT NULL,
    category_description VARCHAR(2012) DEFAULT ('Описание не добавлено')
);

INSERT INTO category (id, category_name, category_description)
VALUES (1, 'Консервы', 'Упаковка металлическа'),
       (2, 'Чипсы', 'Вредно для здоровья');

# PRODUCT TABLE
CREATE TABLE product
(
    id                  INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    category_id         INT UNSIGNED    NOT NULL,
    product_name        VARCHAR(128)    NOT NULL,
    product_description VARCHAR(2012) DEFAULT ('Описание не добавлено'),
    price               DOUBLE UNSIGNED NOT NULL,
    CONSTRAINT product_category_id FOREIGN KEY (category_id) REFERENCES category (id)
);

INSERT INTO product (id, category_id, product_name, product_description, price)
VALUES (1, 1, 'Тушенка', 'Есть мясо', 2.2),
       (2, 1, 'Плохая тушенка', 'Мяса нет', 1.1),
       (3, 2, 'Lays', 'Чипсы иностранные', 1.9),
       (4, 2, 'Онега', 'Чипсы отечественные', 0.97);

# ADDRESS TABLE
CREATE TABLE address
(
    id                INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    postal_code       VARCHAR(16) NOT NULL,
    country           VARCHAR(64) NOT NULL,
    city              VARCHAR(64) NOT NULL,
    street_house_flat VARCHAR(64) NOT NULL
);

INSERT INTO address (id, postal_code, country, city, street_house_flat)
VALUES (1, '220012', 'Беларусь', 'Минск', 'Правды 8 51'),
       (2, '220033', 'Беларусь', 'Минск', 'Пушкина 9 13');

# CUSTOMER TABLE
CREATE TABLE customer
(
    id                INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    login             VARCHAR(64) NOT NULL,
    password          VARCHAR(64) NOT NULL,
    first_name        VARCHAR(64) NOT NULL,
    last_name         VARCHAR(64) NOT NULL,
    registration_date DATE        NOT NULL,
    address_id        INT UNSIGNED,
    CONSTRAINT customer_address_id FOREIGN KEY (address_id) REFERENCES address (id)
);

INSERT INTO customer (id, login, password, first_name, last_name, registration_date, address_id)
VALUES (1, 'LikeChips', '123', 'Василий', 'Васильев', current_date, 1),
       (2, 'LikeMeat', '321', 'Иван', 'Иванов', '2020-02-15', null);

# _ORDER TABLE
CREATE TABLE _order
(
    id                  INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    customer_id         INT UNSIGNED NOT NULL,
    delivery_address_id INT UNSIGNED NOT NULL,
    order_date          DATETIME     NOT NULL,
    CONSTRAINT order_customer_id_fk FOREIGN KEY (customer_id) REFERENCES customer (id),
    CONSTRAINT order_delivery_address_id_fk FOREIGN KEY (delivery_address_id) REFERENCES address (id)
);

INSERT INTO _order (id, customer_id, delivery_address_id, order_date)
VALUES (1, 1, 1, '2020-02-20'),
       (2, 2, 2, '2020-02-20');

# ORDER_PRODUCT TABLE
CREATE TABLE order_product
(
    order_id   INT UNSIGNED NOT NULL,
    product_id INT UNSIGNED NOT NULL,
    quantity   INT UNSIGNED NOT NULL,
    PRIMARY KEY (order_id, product_id),
    CONSTRAINT order_product_order_id FOREIGN KEY (order_id) REFERENCES _order(id),
    CONSTRAINT order_product_product_id FOREIGN KEY (product_id) REFERENCES product (id)
);

INSERT INTO order_product (order_id, product_id, quantity)
VALUES (1, 3, 2),
       (1, 4, 3),
       (2, 1, 1),
       (2, 2, 1);