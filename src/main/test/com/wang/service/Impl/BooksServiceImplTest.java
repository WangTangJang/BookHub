package com.wang.service.Impl;

import com.wang.model.Books;
import com.wang.service.BooksService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BooksServiceImplTest {

    @Resource
    BooksService service;
    @Test
    public void test(){

        Books books = new Books();
        books.setTitle("水浒传");
        books.setAuthor("施耐庵");
        books.setIsbn("3237482324123487");
        books.setFormat("EPUB");
        books.setFilePath("C://");
        books.setFileSize(10);
        service.insert(books);
    }
    @Test
    public void delete(){
        service.deleteById(4);
    }
    @Test
    public void update(){
        Books books = new Books();
        books.setId(5);
        books.setFilePath("D://");
        service.update(books);
    }
    @Test
    public void select(){
        List<Books> book1 = service.selectAll();
        for (Books b:book1){
            System.out.println(b.getTitle()+"评论量为"+b.getReviewsCount());
        }

        List<Books> books = service.search("三");
        for (Books b:books){
            System.out.println(b.getTitle()+"评论量为"+b.getReviewsCount());
        }
    }
}