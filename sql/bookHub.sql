CREATE DATABASE bookHub;

use bookHub;

CREATE TABLE books (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       title VARCHAR(255) NOT NULL ,
                       author VARCHAR(255) ,
                       format VARCHAR(255)
);

RENAME TABLE books TO bookInfo;
