DROP DATABASE bookHub;
CREATE DATABASE bookHub;

use bookHub;
-- 书籍
DROP TABLE books;
CREATE TABLE books
(
    id     INT PRIMARY KEY AUTO_INCREMENT,
    title  VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    format VARCHAR(255)
);


DROP TABLE user;
CREATE TABLE user
(
    id                    INT PRIMARY KEY AUTO_INCREMENT,
    -- 账号信息
    username              VARCHAR(255) NOT NULL,
    password              VARCHAR(255) NOT NULL,
    security_question     VARCHAR(100),
    last_login_ip_address VARCHAR(100),
    last_login_date       DATE,
    account_status        VARCHAR(100),
    create_date           DATE,
    level                 INT,
    experience            INT,
    roles                 VARCHAR(10),
    -- 个人信息
    email                 VARCHAR(100),
    phone                 VARCHAR(100),
    gender                VARCHAR(10),
    country               VARCHAR(10),
    data_of_birth         DATE,
    social_media_links    VARCHAR(10),
    profile_picture       BLOB,
    -- 会员信息
    membership_status     VARCHAR(20),
    membership_start_date date,
    membership_end_date   date
)

