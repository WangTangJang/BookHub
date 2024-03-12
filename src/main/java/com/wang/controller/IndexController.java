package com.wang.controller;

import com.wang.model.Books;
import com.wang.model.User;
import com.wang.model.request.UserRegisterRequest;
import com.wang.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private BooksService service;

    // 跳转到首页
    @RequestMapping("/index")
    public String toIndex(Model model,HttpSession session){
        List<Books> books = service.selectPage(1, 12);

        model.addAttribute("books", books);
        session.setAttribute("currentLocation", "index");
        return "userDisplay/index";
    }

    @ResponseBody
    @RequestMapping("/getMoreBooks/{page}")
    public ResponseEntity<?> getMoreBooks(@PathVariable Integer page){
        List<Books> books = service.selectPage(page, 12);
        Map<String,Object> result = new HashMap<>();
        result.put("books",books);
        result.put("message","success");
        result.put("page",page);
        return ResponseEntity.ok(result);
    }
}
