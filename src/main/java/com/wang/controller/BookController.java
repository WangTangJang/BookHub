package com.wang.controller;

import com.wang.model.Books;
import com.wang.model.Bookshelf;
import com.wang.model.User;
import com.wang.model.result.BookDetailResult;
import com.wang.service.BookRatingsService;
import com.wang.service.BooksService;
import com.wang.service.BookshelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BooksService bookService;
    @Autowired
    private BookRatingsService bookRatingsService;

    @Autowired
    private BookshelfService bookshelfService;

    @GetMapping("/getInfo/{bookId}")
    public ResponseEntity<?> toBookInfo(@PathVariable Long bookId, HttpSession session){
        BookDetailResult result = new BookDetailResult();
        Books books = bookService.selectById(bookId);
        result.setBookInfo(books);

        if (books !=null ){
            User user = (User) session.getAttribute("user");
            if (user!=null){
                if (bookRatingsService.isRated(user.getId(),bookId)){
                    // 如果用户已经登录，就查看这个用户对数据的评分
                    result.setUserRating(bookRatingsService.selectBookRating(user.getId(),bookId));
                }
                result.setCollected(bookshelfService.isCollected(user.getId(),bookId));
            }
            return ResponseEntity.ok(result);
        }
        else {
            return ResponseEntity.badRequest().body("Book not found");
        }
    }

    @PostMapping("saveRating")
    public ResponseEntity<?> saveRating(@RequestBody Map<String,Integer> data, HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        if (user != null) {
            bookRatingsService.rateBook(user.getId(),data.get("bookId"), data.get("rating"));
            // 更新书籍数据
            return ResponseEntity.ok("success");
        }
        else {
            return ResponseEntity.badRequest().body("User not found");
        }
    }

    @GetMapping("collect/{bookId}")
    public ResponseEntity<?> collect(@PathVariable Long bookId,HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        if (user != null) {
            bookshelfService.addToBookshelf(user.getId(),bookId);
            return ResponseEntity.ok("success");
        }
        else {
            return ResponseEntity.badRequest().body("User not found");
        }
    }
    @GetMapping("cancelCollect/{bookId}")
    public ResponseEntity<?> cancelCollect(@PathVariable Long bookId,HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        if (user != null) {
            bookshelfService.delFormBookshelf(user.getId(),bookId);
            return ResponseEntity.ok("success");
        }
        else {
            return ResponseEntity.badRequest().body("User not found");
        }
    }
}
