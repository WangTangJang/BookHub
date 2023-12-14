package com.wang.bookHub.service.Impl;

import com.wang.bookHub.mapper.BookInfoMapper;
import com.wang.bookHub.model.BookInfo;
import com.wang.bookHub.service.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookInfoServiceImpl implements BookInfoService {

    @Autowired
    private BookInfoMapper mapper;

    public List<BookInfo> getAllBooks() {
        return mapper.getAllBooks();
    }

    public void insertBook(BookInfo bookInfo) {
        mapper.insertBook(bookInfo);
    }
}
