package com.wang.service.Impl;

import com.wang.model.Books;
import com.wang.model.User;
import com.wang.service.BookRatingsService;
import com.wang.service.BooksService;
import com.wang.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BookRatingsServiceImplTest {

    @Resource
    BookRatingsService service;
    @Resource
    UserService userService;

    @Resource
    BooksService booksService;

    @Test
    public void rateBook() {
        service.rateBook(1,3,9);
        service.rateBook(1,4,3);
        service.rateBook(1,5,4);
        service.rateBook(1,6,4);
        service.rateBook(1,7,5);
        service.rateBook(1,8,6);
        service.rateBook(1,9,6);
        service.rateBook(1,10,9);
        service.rateBook(2,1,8);
        service.rateBook(2,2,8);
        service.rateBook(2,3,7);
        service.rateBook(2,4,9);
        service.rateBook(2,5,6);
        service.rateBook(2,6,5);
        service.rateBook(2,7,4);
        service.rateBook(2,8,9);
    }
    @Test
    public void updateRate(){
        service.updateBookRating(1,3,1);
    }
    @Test
    public void deleteRate(){
        service.deleteBookRating(1,2);
    }
    @Test
    public void selectRate(){
        int userId = 1;
        int bookId = 2;
        User user = userService.selectById(userId);
        Books books = booksService.selectById(bookId);
        System.out.println(user.getUsername()+"给"+books.getTitle()+"打了"+service.selectBookRating(userId,bookId)+"分!");
    }
}