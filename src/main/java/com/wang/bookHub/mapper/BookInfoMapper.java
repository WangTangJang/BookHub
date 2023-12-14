package com.wang.bookHub.mapper;

import com.wang.bookHub.model.BookInfo;

import java.util.List;

public interface BookInfoMapper {
    List<BookInfo> getAllBooks();

    void insertBook(BookInfo bookInfo);
}
