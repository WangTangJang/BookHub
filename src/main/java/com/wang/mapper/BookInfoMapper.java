package com.wang.mapper;

import com.wang.model.BookInfo;

import java.util.List;

public interface BookInfoMapper {
    List<BookInfo> selectAll();

    BookInfo selectById(int id);

    void insertBook(BookInfo bookInfo);

    void deleteBook(BookInfo bookInfo);

    void modifyBook(BookInfo bookInfo);


}
