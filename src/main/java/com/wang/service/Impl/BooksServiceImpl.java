package com.wang.service.Impl;

import com.wang.mapper.BooksMapper;
import com.wang.model.Books;
import com.wang.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    BooksMapper mapper;

    @Override
    public void insert(Books books) {
        mapper.insert(books);
    }

    @Override
    public void delete(Books books) {
        mapper.delete(books);
    }

    @Override
    public void update(Books books) {
        mapper.update(books);
    }

    @Override
    public Books selectById(long id) {
        return mapper.select(id);
    }

    @Override
    public List<Books> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public List<Books> search(String keyword) {
        return mapper.search(keyword);
    }

    @Override
    public int count() {
        return mapper.count();
    }

    @Override
    public List<Books> selectPage(int page, int size) {
        int start = (page-1)*size;
        return mapper.selectPage(start, size);
    }

    @Override
    public List<Books> selectByList(List<Long> bookIds) {

        return mapper.selectByListId(bookIds);
    }
}
