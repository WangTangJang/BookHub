package com.wang.service;

import com.wang.model.BookInfo;

import java.util.List;

public interface BookInfoService {
    List<BookInfo> getAllBooks();

    BookInfo selectById(int id);

    void insertBook(BookInfo bookInfo);

    void deleteBook(BookInfo bookInfo);

    void modifyBook(BookInfo bookInfo);
}
