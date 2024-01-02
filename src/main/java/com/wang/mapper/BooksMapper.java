package com.wang.mapper;

import com.wang.model.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BooksMapper {
    void insert(Books books);
    void delete(Books books);
    void update(Books books);
    Books select(int id);
    List<Books> selectAll(Books books);
    List<Books> search(@Param("keyword") String keyword);
    int count();
    List<Books> selectPage(@Param("start") int start,@Param("size") int size);
}
