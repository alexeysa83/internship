# DATABASE CREATION
DROP DATABASE IF EXISTS scheduler;
CREATE DATABASE IF NOT EXISTS scheduler;
USE scheduler;

# USER

DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id       INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    login    VARCHAR(24) NOT NULL,
    password VARCHAR(24) NOT NULL,
    role     VARCHAR(12) NOT NUll,
    CONSTRAINT user_login_uindex UNIQUE (login)
);

INSERT INTO user (login, password, role)
VALUES ('admin', 'admin', 'ADMIN'),
       ('user', 'user', 'USER');

# EVENT

DROP TABLE IF EXISTS event;
CREATE TABLE event
(
    id          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    user_id     INT UNSIGNED NOT NULL,
    name        VARCHAR(64)  NOT NULL,
    description VARCHAR(512),
    start_date  DATE         NOT NULL,
    start_time  TIME         NOT NULL,
    end_time    TIME,
    CONSTRAINT event_user_id FOREIGN KEY (user_id) REFERENCES user (id)
);

INSERT INTO event (user_id, name, description, start_date, start_time, end_time)
VALUES (1, 'TestEvent', 'TestDescription','2020-02-27', '16:00', '17:00')