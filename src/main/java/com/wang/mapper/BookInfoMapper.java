//package com.wang.mapper;
//
//import com.wang.model.BookInfo;
//import org.apache.ibatis.annotations.Param;
//
//import java.awt.print.Book;
//import java.util.List;
//
//public interface BookInfoMapper {
//    List<BookInfo> selectAll();
//
//    BookInfo selectById(int id);
//
//    void insertBook(BookInfo bookInfo);
//
//    void deleteBook(BookInfo bookInfo);
//
//    void modifyBook(BookInfo bookInfo);
//
//    List<BookInfo> searchBooks(@Param("keyword")String keyword);
//
//    int count();
//
//    List<BookInfo> getByPage(@Param("start") int start,@Param("size") int size);
//
//}
