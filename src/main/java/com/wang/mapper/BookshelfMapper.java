package com.wang.mapper;

import com.wang.model.Bookshelf;

import java.util.List;

public interface BookshelfMapper {
    /**
     * 插入
     * @param bookshelf 书架实体
     */
    void insert(Bookshelf bookshelf);

    /**
     * 删除
     * @param bookshelf 书架实体
     */
    void delete(Bookshelf bookshelf);

    /**
     * 更新
     * @param bookshelf 书架实体
     */
    void update(Bookshelf bookshelf);

    /**
     * 查找
     * @param bookshelf 书架实体
     * @return 找到的书架
     */
    List<Bookshelf> select(Bookshelf bookshelf);
}
