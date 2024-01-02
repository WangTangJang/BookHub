package com.wang.service.Impl;

import com.wang.model.Books;
import com.wang.service.BooksService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BooksServiceImplTest {

    @Resource
    BooksService service;
    @Test
    public void test(){

        Books books = new Books();
        books.setAuthor("xxx");
        books.setTitle("xxx");
        books.setFilePath("xxx");
        service.insert(books);
    }


}