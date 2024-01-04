package com.wang.mapper;

import com.wang.model.BookRatings;

public interface BookRatingsMapper {

    void insert(BookRatings bookRantings);
    void delete(BookRatings bookRantings);
    void update(BookRatings bookRantings);
    BookRatings select(BookRatings bookRatings);
}
