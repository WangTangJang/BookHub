package com.wang.service.Impl;

import com.wang.mapper.BookInfoMapper;
import com.wang.model.BookInfo;
import com.wang.service.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookInfoServiceImpl implements BookInfoService {

    @Autowired
    private BookInfoMapper mapper;

    public List<BookInfo> getAllBooks() {
        return mapper.selectAll();
    }

    public BookInfo selectById(int id) {
        return mapper.selectById(id);
    }

    public void insertBook(BookInfo bookInfo) {
        mapper.insertBook(bookInfo);
    }

    public void deleteBook(BookInfo bookInfo) {
        mapper.deleteBook(bookInfo);
    }

    public void modifyBook(BookInfo bookInfo) {
        mapper.modifyBook(bookInfo);
    }
}
