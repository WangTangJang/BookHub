package com.wang.controller;

import com.wang.model.BookRatings;
import com.wang.model.Books;
import com.wang.model.User;
import com.wang.service.BookRatingsService;
import com.wang.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BooksService bookService;
    @Autowired
    private BookRatingsService bookRatingsService;

    @GetMapping("/toBookInfo/{id}")
    public String toBookInfo(@PathVariable Long id, Model model, HttpSession session){
        Books books = bookService.selectById(id);
        User user = (User) session.getAttribute("user");
        if (user!=null){
            model.addAttribute("userRating",bookRatingsService.selectBookRating(user.getId(),id));
        }
        model.addAttribute("book",books);
        return "userDisplay/bookInfo";
    }
}
