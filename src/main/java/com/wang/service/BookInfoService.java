package com.wang.service;

import com.wang.model.BookInfo;

import java.util.List;

public interface BookInfoService {
    List<BookInfo> getAllBooks();

    void insertBook(BookInfo bookInfo);
}
