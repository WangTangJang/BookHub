package com.wang.bookHub.service;

import com.wang.bookHub.model.BookInfo;

import java.util.List;

public interface BookInfoService {
    List<BookInfo> getAllBooks();

    void insertBook(BookInfo bookInfo);
}
