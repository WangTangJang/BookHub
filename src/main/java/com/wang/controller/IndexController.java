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

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private BooksService service;

    // 跳转到首页
    @RequestMapping("/index")
    public String toIndex(Model model,HttpSession session){
        List<Books> books = service.selectAll();
        // 将获取到的books随机打乱
        //for (int i = 0; i < books.size(); i++) {
        //    int randomIndex = (int) (Math.random() * books.size());
        //    Books temp = books.get(i);
        //    books.set(i, books.get(randomIndex));
        //    books.set(randomIndex, temp);
        //}
        model.addAttribute("books", books);
        session.setAttribute("currentLocation", "index");
        return "userDisplay/index";
    }
}
