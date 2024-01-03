package com.wang.service;

import com.wang.model.Bookshelf;

import java.util.List;

public interface BookshelfService {
    /**
     * 用户向书架中添加书籍
     * @param bookshelf 关联信息
     */
    void addToBookshelf(Bookshelf bookshelf);

    /**
     * 从书架上删除信息
     * @param bookshelf 书架信息
     */
    void delFormBookshelf(Bookshelf bookshelf);

    /**
     * 用户检查书架中有哪些信息
     * @param bookshelf 关联信息
     * @return 此用户的信息
     */
    List<Bookshelf> lookOver(Bookshelf bookshelf);

}
