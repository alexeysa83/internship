CREATE DATABASE db_jdbc;

USE db_jdbc;

CREATE TABLE movie
(
    id      INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(64) NOT NULL,
    genre   VARCHAR(64) NOT NULL,
    is_cool BOOLEAN     NOT NULL
);