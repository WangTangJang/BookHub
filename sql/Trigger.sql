## 用触发器设置初始值

-- 书籍表中的数据被插入的时候自动创建一条动态数据
delimiter //
DROP TRIGGER IF EXISTS after_insert_book_original_info;
create trigger  after_insert_book_original_info
    after insert on book_original_info for each row
    begin
        insert into book_dynamic_info(book_id, average_rating, reviews_count, shelf_count, rating_count)
        value (NEW.id,0,0,0,0);
    end;
//

-- 用户表中新建用户是时，自动创建一个用户的动态数据
drop trigger IF EXISTS after_insert_user_original_info;
create trigger after_insert_user_original_info
    after insert on user_original_info for each row
    begin
        insert into user_dynamic_info(user_id, level, experience, membership_status)
        VALUE (NEW.id,1,0,'non-member');
    end;
//
-- 新增评论时，检查父评论是否属于同一本书
drop trigger IF EXISTS check_parent_comment_before_insert;
CREATE TRIGGER check_parent_comment_before_insert BEFORE INSERT ON comments
    FOR EACH ROW
BEGIN
    DECLARE _parent_book_id INT;
    IF NEW.parent_comment_id IS NOT NULL THEN
        SELECT book_id INTO _parent_book_id FROM comments WHERE id = NEW.parent_comment_id;
        IF _parent_book_id != NEW.book_id THEN
            SET NEW.context = 'Error: Parent comment is not from the same book';
        END IF;
    END IF;
END;


-- 触发器，当用户更新评论时候，自动更新评论的更新时间
DROP TRIGGER IF EXISTS before_update_comments;
CREATE TRIGGER before_update_comments
    BEFORE UPDATE ON comments
    FOR EACH ROW
#     此语句有误，如果update_time不为空才更新
#     SET NEW.update_time = IFNULL(NEW.update_time, CURRENT_TIMESTAMP);
#     此方法表中任一数据更新都会触发，不可。
#     SET NEW.update_time = CURRENT_TIMESTAMP;
    BEGIN
        # 比较context是否发生变动
        IF NEW.context != OLD.context THEN
            SET NEW.update_time = CURRENT_TIMESTAMP;
        end if;
    end;
//
delimiter ;
