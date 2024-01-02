package com.wang.service.Impl;

import com.wang.mapper.BooksMapper;
import com.wang.model.Books;
import com.wang.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;

public class BooksServiceImpl implements BooksService {

    @Autowired
    BooksMapper mapper;

    @Override
    public void insert(Books books) {
        mapper.insert(books);
    }
}
