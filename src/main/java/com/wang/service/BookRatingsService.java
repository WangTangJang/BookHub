package com.wang.service;

public interface BookRatingsService {

    /**
     * 用户对书籍评分
     * @param userId 用户id
     * @param bookId 书籍id
     * @param rating 用户的评分
     * @return 返回"ok" 表示成功打分,返回"existed" 表示打分已存在
     * 通过前端的控制应该可以让用户无法在已经打分的情况下继续打分.
     */
    String rateBook(long userId,long bookId, int rating);

    /**
     * 搜素用户对某一本书的评分
     * @param userId 用户id
     * @param bookId 书籍id
     * @return 评分
     */
    int selectBookRating(long userId ,long bookId);

    /**
     * 用户修改对书籍的评分
     * @param userId 用户id
     * @param bookId 书籍id
     * @param rating 修改后的评分
     */
    void updateBookRating(long userId ,long bookId ,int rating);

    /**
     * 删除用户对书籍的评分
     * @param userId 用户评分
     * @param bookId 书籍评分
     */
    void deleteBookRating(long userId ,long bookId );

}
