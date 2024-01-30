package com.wang.service.Impl;

import com.wang.mapper.BookCategoryMapper;
import com.wang.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BookCategoryServiceImpl implements BookCategoryService {

    @Autowired
    BookCategoryMapper mapper;


    @Override
    public void insert(Map<String, Object> map) {
        mapper.insert(map);
    }

    @Override
    public void delete(int id) {
        mapper.delete(id);
    }

    @Override
    public void update(Map<String, Object> map) {
        mapper.update(map);
    }

    @Override
    public void select(Map<String, Object> map) {
        mapper.select(map);
    }

    @Override
    public void addBookCategory(long bookId, long categoryId) {
        mapper.insertMapper(bookId,categoryId);
    }

    @Override
    public void deleteBookCategory(long bookId, long categoryId) {
        mapper.deleteMapper(bookId,categoryId);
    }

    @Override
    public void deleteByBookId(long bookId) {
        mapper.deleteMapperByBookId(bookId);
    }

    @Override
    public void updateBookCategory(long bookId, long categoryId) {
        mapper.updateMapper(bookId,categoryId);
    }

    @Override
    public void selectBookCategory(long bookId, long categoryId) {
        mapper.selectMapper(bookId,categoryId);
    }
}
