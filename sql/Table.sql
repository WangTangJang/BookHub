DROP DATABASE bookHub;
CREATE DATABASE bookHub;

use bookHub;
-- 书籍表
DROP TABLE book_original_info;
CREATE TABLE book_original_info
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    -- 书籍自身信息
    title     VARCHAR(255) NOT NULL,
    author    VARCHAR(255),
    isbn      varchar(100) UNIQUE,
    cover     varchar(100),
    -- 电子版信息
    format    VARCHAR(255),
    file_path varchar(100),
    file_size float,
    uploaded_by varchar(50),
    status varchar(20)
);
-- 书籍的动态信息表
drop table book_dynamic_info;
create table book_dynamic_info
(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    book_id        int,
    average_rating decimal(3, 2),
    reviews_count  int,
    shelf_count    int,
    rating_count   int,
    foreign key (book_id) references book_original_info (id) ON DELETE CASCADE
);
-- 书籍分类表
drop table book_categories;
create table book_categories
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE
);
-- 书籍与分类的关联表
drop table book_category_mapping;
create table book_category_mapping
(
    book_id     int,
    category_id int,
    primary key (book_id, category_id),
    foreign key (book_id) references book_original_info (id) ON DELETE CASCADE,
    foreign key (category_id) references book_categories (id) ON DELETE CASCADE
);

-- q 向数据库插入书籍分类的时候要同时向两张表插入吗


-- 用户表
DROP TABLE user_original_info;
CREATE TABLE user_original_info
(
    id                    INT PRIMARY KEY AUTO_INCREMENT,
    -- 账号信息
    username              VARCHAR(255) NOT NULL UNIQUE,
    password              VARCHAR(255) NOT NULL,
    security_question     VARCHAR(100),
    account_status        VARCHAR(100) not null ,
    create_date           TIMESTAMP default CURRENT_TIMESTAMP,
    roles                 VARCHAR(10) not null,
    -- 个人信息
    email                 VARCHAR(100),
    phone                 VARCHAR(100),
    gender                VARCHAR(10),
    country               VARCHAR(100),
    data_of_birth         DATE,
    social_media_links    VARCHAR(10),
    profile_picture       BLOB
);
-- 用户的动态信息
drop table user_dynamic_info;
create table user_dynamic_info
(
    id                    int primary key auto_increment,
    user_id               int,
    level                 INT,
    experience            INT,
    last_login_ip_address VARCHAR(100),
    last_login_date       DATE ,
    -- 会员信息
    membership_status     VARCHAR(20),
    membership_start_date date,
    membership_end_date   date,
    foreign key (user_id) references user_original_info(id) on DELETE CASCADE
);

-- 书籍添加的审核表
drop table audit_records;
create table audit_records(
    id int primary key auto_increment,
    book_id int ,
    result varchar(50),
    audit_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    auditor_id int ,
    submitter_id int ,
    comments TEXT,
    foreign key (book_id) references book_original_info(id) on DELETE CASCADE ,
    foreign key (auditor_id) references user_original_info (id),
    foreign key (submitter_id) references user_original_info (id)
);

-- 用户给书籍打分表
drop table Book_ratings;
create table book_ratings
(
    user_id int,
    book_id int,
    rating  int not null,
    primary key (user_id, book_id),
    foreign key (user_id) references user_original_info (id),
    foreign key (book_id) references book_original_info (id)
);

-- 书架表
drop table bookshelf;
create table bookshelf
(
    id        int primary key auto_increment,
    user_id   int,
    book_id   int,
    status    varchar(100),
    join_date date,
    foreign key (user_id) references user_original_info (id),
    foreign key (book_id) references book_original_info (id)
);

-- 评论表
DROP table comments;
create table comments
(
    id                int primary key auto_increment,
    user_id           int,
    book_id           int,
    parent_comment_id int,
    context           text not null,
    -- 这两个应该是归属到用户给评论投票的表上去.
    -- 到时候查询的时候,直接去统计那张关联表.
    -- 而不是在绑定到这张表里
    -- 但是 .....
    likes             int,
    dislikes          int,
    creation_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time       DATETIME DEFAULT null,
    FOREIGN KEY (user_id) references user_original_info (id),
    foreign key (book_id) references book_original_info (id),
    foreign key (parent_comment_id) references comments (id)
);

-- 用户给评论点赞或踩
drop table user_comment_votes;
create table user_comment_votes
(
    user_id    int,
    comment_id int,
    vote_type  enum ('like','dislike') not null,
    vote_date  TIMESTAMP default current_timestamp,
    primary key (user_id, comment_id),
    foreign key (user_id) references user_original_info (id),
    foreign key (comment_id) references comments (id)
);

