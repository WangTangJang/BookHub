package com.wang.controller;


import com.wang.model.BookInfo;
import com.wang.service.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookInfoController {

    @Autowired
    private BookInfoService service;

    @RequestMapping("/list")
    public String listBooks(Model model){
        List<BookInfo> books = service.getAllBooks();
        model.addAttribute("books", books);
        // 用于告知前端当前页面
        model.addAttribute("currentPage" ,"list");
        return "book/list";
    }
    @RequestMapping("toAddView")
    public String toAddView(Model model){
        model.addAttribute("books",new BookInfo());
        model.addAttribute("currentPage" ,"add");
        return "book/add";
    }

    @RequestMapping("doAdd")
    public String doAdd(@ModelAttribute("books") BookInfo bookInfo){
        service.insertBook(bookInfo);
        return "book/add";
    }
}
