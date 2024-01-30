package com.wang.service;

import java.util.Map;

public interface BookCategoryService {
    void insert(Map<String, Object> map);
    void delete(int id);
    void update(Map<String, Object> map);
    void select(Map<String, Object> map);

    // 书籍与分类的映射表
    void addBookCategory(long bookId,long categoryId);
    void deleteBookCategory(long bookId,long categoryId);
    void deleteByBookId(long bookId);
    void updateBookCategory(long bookId,long categoryId);
    void selectBookCategory(long bookId,long categoryId);
}
