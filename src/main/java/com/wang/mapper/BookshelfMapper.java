package com.wang.mapper;

import com.wang.model.Bookshelf;

import java.util.List;

public interface BookshelfMapper {
    void insert(Bookshelf bookshelf);
    void delete(Bookshelf bookshelf);
    void update(Bookshelf bookshelf);
    List<Bookshelf> select(Bookshelf bookshelf);
}
