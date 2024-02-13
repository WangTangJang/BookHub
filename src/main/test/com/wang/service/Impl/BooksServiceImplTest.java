package com.wang.service.Impl;

import com.wang.model.AuditRecords;
import com.wang.model.Books;
import com.wang.model.User;
import com.wang.service.AuditRecordsService;
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

    @Resource
    AuditRecordsService auditRecordsService;

    @Test
    public void test(){
        Books books = new Books();
        books.setTitle("三国演义");
        books.setAuthor("罗贯中");
        books.setIsbn("32374823241221230");
        books.setFormat("EPUB");
        books.setFilePath("C://");
        books.setFileSize(10);
        books.setCover("/static/images/00003.jpeg");
        books.setCategoryId(2);
        System.out.println(service.adminUpload(books));
    }

    @Test
    public void delete(){
        service.deleteById(9);
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
    public void getBookByStatus(){

        List<Books> books = service.searchPendingBooks();
        for (Books b:books){
            User user = new User();
            System.out.println(b.getTitle()+":"+b.getStatus()+"--提交者:"+b.getUploadedBy());
            AuditRecords auditRecords = new AuditRecords();
            auditRecords.setAuditorId(2);
            auditRecords.setResult("审核通过");
            auditRecords.setComments("恭喜，审核通过");
            auditRecords.setBookId(b.getId());
            auditRecords.setSubmitterId(userService.selectByUsername(b.getUploadedBy()).getId());
            auditRecordsService.insert(auditRecords);
            b.setStatus(auditRecords.getResult());
            service.update(b);
        }
    }
}