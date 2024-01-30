package com.wang.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface BookCategoryMapper {
    void insert(Map<String, Object> map);
    void delete(int id);
    void update(Map<String, Object> map);
    void select(Map<String, Object> map);
    // 书籍与分类的映射表
    void insertMapper(@Param("bookId") long bookId,@Param("categoryId") long categoryId);
    void deleteMapper(@Param("bookId") long bookId,@Param("categoryId") long categoryId);
    void deleteMapperByBookId(@Param("bookId") long bookId);
    void updateMapper(@Param("bookId") long bookId,@Param("categoryId") long categoryId);
    void selectMapper(@Param("bookId") long bookId,@Param("categoryId") long categoryId);
}
