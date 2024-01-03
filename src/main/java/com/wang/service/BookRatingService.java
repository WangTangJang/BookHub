package com.wang.service;

public interface BookRatingService {

    /**
     * 用户对书籍评分
     * @param userId 用户id
     * @param bookId 书籍id
     * @param rating 用户的评分
     * @return 返回"ok" 表示成功打分,返回"existed" 表示打分已存在
     * 通过前端的控制应该可以让用户无法在已经打分的情况下继续打分.
     */
    String rateBook(long userId,long bookId, int rating);
    int selectBookRating();

    void updateBookRating();
    void deleteBookRating();
}
