package com.wang.model;

public class BookRatings {
    long userId;
    long bookId;
    int rating;

    public BookRatings() {
    }

    public BookRatings(long bookId) {
        this.bookId = bookId;
    }

    public BookRatings(long userId, long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public BookRatings(long userId, long bookId, int rating) {
        this.userId = userId;
        this.bookId = bookId;
        this.rating = rating;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
