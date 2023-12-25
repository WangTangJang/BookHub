package com.wang.mapper;

import com.wang.model.BookInfo;

import java.util.List;

public interface BookInfoMapper {
    List<BookInfo> selectAll();

    void insertBook(BookInfo bookInfo);
}
