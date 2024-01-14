package com.wang.service.Impl;

import com.wang.model.Books;
import com.wang.model.User;
import com.wang.service.BooksService;
import com.wang.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BooksServiceImplTest {

    @Resource
    BooksService service;

    @Resource
    UserService userService;

    @Test
    public void test(){

        Books books = new Books();
        books.setTitle("水浒传");
        books.setAuthor("施耐庵");
        books.setIsbn("3237482324123480");
        books.setFormat("EPUB");
        books.setFilePath("C://");
        books.setFileSize(10);
        service.adminUpload(books);
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
        // 统计书籍数量
        List<Books> book1 = service.selectAll();
        for (Books b:book1){
            // 输出b的所有属性
            System.out.println(b.getTitle()+"评论量为"+b.getReviewsCount());
        }

        List<Books> books = service.search("三");
        for (Books b:books){
            System.out.println(b.getTitle()+"评论量为"+b.getReviewsCount());
        }

        Books book2 = service.selectById(1);
        System.out.println(book2.getTitle()+"的评分为"+book2.getAverageRating());
    }
    @Test
    public void userUpload(){
        Books book = new Books();
        book.setTitle("水浒传");
        book.setAuthor("施耐庵");
        book.setIsbn("3237482324123487");
        book.setFormat("EPUB");
        book.setFilePath("C://");
        book.setFileSize(10);
        User user = userService.selectById(3);
        System.out.println(service.userUpload(book,user.getUsername()));
    }
    @Test
    public void getBookByStatus(){
        List<Books> books = service.searchPendingBooks();
        for (Books b:books){
            System.out.println(b.getTitle());
        }

        List<Books> books1 = service.searchApprovedBooks();
        for (Books b:books1){
            System.out.println(b.getStatus());
        }
    }
}