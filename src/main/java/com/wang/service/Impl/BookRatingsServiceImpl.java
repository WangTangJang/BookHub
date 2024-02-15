package com.wang.service.Impl;

import com.wang.mapper.BookRatingsMapper;
import com.wang.model.BookRatings;
import com.wang.model.Books;
import com.wang.service.BookRatingsService;
import com.wang.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;

@Service
public class BookRatingsServiceImpl implements BookRatingsService {

    @Autowired
    BookRatingsMapper mapper;

    @Autowired
    BooksService booksService;

    @Override
    public String rateBook(long userId ,long bookId ,int rating) {

        BookRatings bookRatings = mapper.select(new BookRatings(userId,bookId));
        if (bookRatings == null){
            mapper.insert(new BookRatings(userId,bookId,rating));
            booksService.updateAverageRating(bookId);
            booksService.updateRatingCount(bookId);
            return "ok";
        }else {
            return "existed";
        }

    }

    // 判断用户是否已经对书籍评分
    public boolean isRated(long userId ,long bookId){
        BookRatings bookRatings = mapper.select(new BookRatings(userId,bookId));
        return bookRatings != null;
    }

    @Override
    public int selectBookRating(long userId ,long bookId) {
        BookRatings bookRatings = mapper.select(new BookRatings(userId,bookId));
        return bookRatings.getRating();
    }

    @Override
    public void updateBookRating(long userId ,long bookId ,int rating) {
        mapper.update(new BookRatings(userId,bookId,rating));
        booksService.updateAverageRating(bookId);
        booksService.updateRatingCount(bookId);
    }

    @Override
    public void deleteBookRating(long userId ,long bookId) {
        mapper.delete(new BookRatings(userId,bookId));
        booksService.updateAverageRating(bookId);
        booksService.updateRatingCount(bookId);
    }
}
