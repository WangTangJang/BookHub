package com.wang.mapper;

import com.wang.model.BookInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface BookInfoMapper {
    List<BookInfo> getAllBooks();

    void insertBook(BookInfo bookInfo);
}
