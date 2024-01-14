package com.wang.mapper;

import com.wang.model.BookRatings;

public interface BookRatingsMapper {

    /**
     * 插入
     * @param bookRantings 评分实体
     */
    void insert(BookRatings bookRantings);

    /**
     * 删除
     * @param bookRantings 评分实体
     */
    void delete(BookRatings bookRantings);

    /**
     * 更新
     * @param bookRantings 评分实体
     */
    void update(BookRatings bookRantings);

    /**
     * 查找
     * @param bookRatings 评分实体
     * @return 找到的评分
     */
    BookRatings select(BookRatings bookRatings);
}
