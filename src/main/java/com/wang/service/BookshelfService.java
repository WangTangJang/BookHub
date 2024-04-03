package com.wang.service;

import com.wang.model.Books;
import com.wang.model.Bookshelf;

import java.util.List;

public interface BookshelfService {
    /**
     * 用户向书架中添加书籍
     * @param userid 关联信息
     */
    void addToBookshelf(long userid, long bookId);

    /**
     * 从书架上删除信息
     *
     */
    void delFormBookshelf(long userid, long bookId);

    /**
     * 用户检查书架中有哪些信息
     * @param bookshelf 关联信息
     * @return 此用户的信息
     */
    List<Bookshelf> lookOver(Bookshelf bookshelf); // 这个方法似乎过于草率

    /**
     * 通过用户id查找书籍列表
     * @param userId 用户id
     * @return 书籍列表
     */
    List<Books> getBooksByUserId(long userId);

    /**
     * 查看某本书是否被收藏
     * @param userid 用户ID
     * @param bookid 书籍ID
     * @return 是否被收藏
     */
    boolean isCollected(long userid, long bookid);
}
