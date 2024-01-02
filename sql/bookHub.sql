DROP DATABASE bookHub;
CREATE DATABASE bookHub;

use bookHub;
-- 书籍表
# DROP TABLE books;
CREATE TABLE books
(
    id     INT PRIMARY KEY AUTO_INCREMENT,
    -- 书籍自身信息
    title  VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    isbn varchar(100) UNIQUE,
    -- 电子版信息
    format VARCHAR(255),
    file_path varchar(100),
    average_rating decimal(3,2),
    total_reviews int,
    file_size float,
    -- 有关书架的信息
    added int
);

-- 用户表
# DROP TABLE user;
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
);
-- 书架表
create table bookshelf(
    id int primary key auto_increment,
    user_id int,
    book_id int,
    status varchar(100),
    join_date date,
    foreign key(user_id) references user(id),
    foreign key (book_id) references books(id)
);

-- 评论表
create table comments(
    id int primary key auto_increment,
    user_id int,
    book_id int,
    parent_comment_id int,
    context text not null ,
    likes int ,
    dislikes int ,
    creation_date TIMESTAMP default CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) references user(id),
    foreign key (book_id) references books(id),
    foreign key (parent_comment_id) references comments(id)
);

-- 用户给评论点赞或踩
create table userCommentVotes (
    use_id int,
    comment_id int,
    vote_type enum ('like','dislike') not null ,
    vote_date TIMESTAMP default current_timestamp,
    primary key (use_id,comment_id),
    foreign key (use_id) references user(id),
    foreign key (comment_id) references comments(id)
);

-- 用户给书籍打分表
create table userBookRatings(
    use_id int,
    book_id int,
    rating int not null ,
    primary key (use_id,book_id),
    foreign key (use_id) references user(id),
    foreign key (book_id) references books(id)
);