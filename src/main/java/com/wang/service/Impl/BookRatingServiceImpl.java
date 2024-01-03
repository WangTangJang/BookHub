package com.wang.service.Impl;

import com.wang.mapper.BookRatingMapper;
import com.wang.model.BookRatings;
import com.wang.service.BookRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookRatingServiceImpl implements BookRatingService {

    @Autowired
    BookRatingMapper mapper;

    @Override
    public String rateBook(long userId ,long bookId ,int rating) {

        BookRatings bookRatings = mapper.select(new BookRatings(userId,bookId));
        if (bookRatings != null){
            mapper.insert(new BookRatings(userId,bookId,rating));
            return "ok";
        }else {
            return "existed";
        }

    }

    @Override
    public int selectBookRating() {
        return 0;
    }

    @Override
    public void updateBookRating() {

    }

    @Override
    public void deleteBookRating() {

    }
}
