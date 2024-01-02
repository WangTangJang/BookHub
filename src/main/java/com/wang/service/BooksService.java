package com.wang.service;

import com.wang.model.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BooksService {
    void insert(Books books);
    void delete(Books books);
    void update(Books books);
    Books select(int id);

    List<Books> selectAll(Books books);
    List<Books> search(@Param("keyword") String keyword);
    int count();
    List<Books> selectPage(@Param("start") int page,@Param("size") int size);
}
