package com.wang.mapper;

import com.wang.model.BookInfo;

import java.util.List;

public interface BookInfoMapper {
    List<BookInfo> getAllBooks();

    void insertBook(BookInfo bookInfo);
}
