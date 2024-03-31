select * from book_original_info;
# 删除外键
ALTER TABLE book_ratings DROP FOREIGN KEY book_ratings_ibfk_2;
# 修改外键
ALTER TABLE book_ratings ADD CONSTRAINT book_ratings_ibfk_2
    FOREIGN KEY (book_id) REFERENCES book_original_info(id)
        ON DELETE CASCADE;


# 删除外键
ALTER TABLE bookshelf DROP FOREIGN KEY bookshelf_ibfk_2;
# 修改外键
ALTER TABLE bookshelf ADD CONSTRAINT bookshelf_ibfk_2
    FOREIGN KEY (book_id) REFERENCES book_original_info(id)
        ON DELETE CASCADE;

# 删除外键
ALTER TABLE comments DROP FOREIGN KEY comments_ibfk_2;
# 修改外键
ALTER TABLE comments ADD CONSTRAINT comments_ibfk_2
    FOREIGN KEY (book_id) REFERENCES book_original_info(id)
        ON DELETE CASCADE;

# 修改外键
ALTER TABLE user_comment_votes DROP FOREIGN KEY user_comment_votes_ibfk_2;
ALTER TABLE user_comment_votes ADD CONSTRAINT user_comment_votes_ibfk_2
    FOREIGN KEY (comment_id) REFERENCES comments(id)
        ON DELETE CASCADE;

# 修改外键
ALTER TABLE comments DROP FOREIGN KEY comments_ibfk_3;
ALTER TABLE comments ADD CONSTRAINT comments_ibfk_3
    FOREIGN KEY (parent_comment_id) REFERENCES comments(id)
        ON DELETE CASCADE;

ALTER TABLE book_category_mapping DROP FOREIGN KEY book_category_mapping_ibfk_2;
ALTER TABLE book_category_mapping ADD CONSTRAINT book_category_mapping_ibfk_2
    FOREIGN KEY (category_id) REFERENCES book_categories(id)
        ON DELETE CASCADE;

# 查看一个表的结构
SHOW CREATE TABLE book_ratings;

SHOW CREATE TABLE bookshelf;

SELECT * FROM book_original_info
                  INNER JOIN book_dynamic_info
                             ON book_dynamic_info.book_id = book_original_info.id
LIMIT 0, 10;

SELECT CONSTRAINT_NAME, CONSTRAINT_TYPE
FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS
WHERE TABLE_NAME = 'book_original_info';

alter table book_original_info drop constraint isbn

update book_original_info set uploaded_by = 'Admin'

select * from comments where book_id =31;
