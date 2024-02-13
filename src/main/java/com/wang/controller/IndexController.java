package com.wang.controller;

import com.wang.model.Books;
import com.wang.model.request.UserRegisterRequest;
import com.wang.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private BooksService service;

    // 跳转到首页
    @RequestMapping("/toIndex")
    public String toIndex(Model model){
        List<Books> books = service.selectAll();
        model.addAttribute("books", books);
        return "userDisplay/index";
    }
}
