//package com.wang.service.Impl;
//
//import com.wang.mapper.BookInfoMapper;
//import com.wang.model.BookInfo;
//import com.wang.service.BookInfoService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.awt.print.Pageable;
//import java.util.List;
//
//@Service
//public class BookInfoServiceImpl implements BookInfoService {
//
//    @Autowired
//    private BookInfoMapper mapper;
//
//    public List<BookInfo> getAllBooks() {
//        return mapper.selectAll();
//    }
//
//    public BookInfo selectById(int id) {
//        return mapper.selectById(id);
//    }
//
//    public void insertBook(BookInfo bookInfo) {
//        mapper.insertBook(bookInfo);
//    }
//
//    public void deleteBook(BookInfo bookInfo) {
//        mapper.deleteBook(bookInfo);
//    }
//
//    public void modifyBook(BookInfo bookInfo) {
//        mapper.modifyBook(bookInfo);
//    }
//
//    public List<BookInfo> searchBooks(String keyword) {
//        return mapper.searchBooks(keyword);
//    }
//
//    public int count() {
//        return mapper.count();
//    }
//
//    public List<BookInfo> getByPage(int page, int size) {
//        int start = (page-1)*size;
//        return mapper.getByPage(start,size);
//    }
//
//}
