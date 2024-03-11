package com.wang.service;

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
    List<Bookshelf> lookOver(Bookshelf bookshelf);

    boolean isCollected(long userid, long bookid);
}
