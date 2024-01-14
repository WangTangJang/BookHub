-- 用触发器设置初始值
-- 书籍表中的数据被插入的时候自动创建一条动态数据
delimiter //
create trigger after_insert_book_original_info
    after insert on book_original_info for each row
    begin
        insert into book_dynamic_info(book_id, average_rating, reviews_count, shelf_count, rating_count)
        value (NEW.id,0,0,0,0);
    end;
//

-- 用户表中新建用户是时，自动创建一个用户的动态数据
drop trigger after_insert_user_original_info;
create trigger after_insert_user_original_info
    after insert on user_original_info for each row
    begin
        insert into user_dynamic_info(user_id, level, experience, membership_status)
        VALUE (NEW.id,1,0,'non-member');
    end;
//
-- 触发器，当用户更新评论时候，自动更新评论的更新时间
CREATE TRIGGER before_update_comments
    BEFORE UPDATE ON comments
    FOR EACH ROW
    SET NEW.update_time = IFNULL(NEW.update_time, CURRENT_TIMESTAMP);
//
delimiter ;