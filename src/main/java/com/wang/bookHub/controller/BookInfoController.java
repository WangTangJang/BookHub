package com.wang.bookHub.controller;


import com.wang.bookHub.model.BookInfo;
import com.wang.bookHub.service.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookInfoController {

//    @Autowired
//    private BookInfoService service;
//
//    @RequestMapping("/list")
//    public String listBooks(Model model){
//        List<BookInfo> books = service.getAllBooks();
//        model.addAttribute("books", books);
//        return "book/list";
//    }
}
