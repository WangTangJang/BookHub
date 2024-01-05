package com.wang.service.Impl;

import com.wang.model.Books;
import com.wang.model.Bookshelf;
import com.wang.model.User;
import com.wang.service.BooksService;
import com.wang.service.BookshelfService;
import com.wang.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BookshelfServiceImplTest {

    @Resource
    BookshelfService service;

    @Resource
    BooksService booksService;

    @Resource
    UserService userService;

    @Test
    public void addToBookshelf(){
        Bookshelf bookshelf = new Bookshelf();

        bookshelf.setUserId(1);
        bookshelf.setBookId(1);
        service.addToBookshelf(bookshelf);
    }

    @Test
    public void lookOver() {
        Bookshelf bookshelf = new Bookshelf();
        User user = new User();
        bookshelf.setUserId(1);
        user.setId(bookshelf.getUserId());
        String username = userService.selectById(1).getUsername();
        List<Bookshelf> bookshelves = service.lookOver(bookshelf);
        List<Long> bookIdList = new ArrayList<>();
        for(Bookshelf bookshelf1:bookshelves){
            bookIdList.add(bookshelf1.getBookId());
        }

        List<Books> books = booksService.selectByList(bookIdList);
        System.out.println(username+"收藏了如下书籍");
        for (Books b:books){
            System.out.println(b.getTitle());
        }
    }

    @Test
    public void delFormBookshelf() {
        Bookshelf bookshelf = new Bookshelf();
        bookshelf.setUserId(1);
        bookshelf.setBookId(1);
        service.delFormBookshelf(bookshelf);
    }
}