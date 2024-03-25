package com.wang.controller.admin;

import com.wang.model.Books;
import com.wang.service.BooksService;
import com.wang.service.CommentsService;
import com.wang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Book;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    BooksService booksService;
    @Autowired
    CommentsService commentsService;

    @GetMapping("/toLogin")
    public String toLogin(){
        return "admin/book/loginPage";
    }

    @PostMapping("/doLogin")
    public String doLogin(@RequestParam("username") String username,
                          @RequestParam("password") String password, Model model){
        String result = userService.adminLogin(username, password);
        if (!result.equals("登录成功")){
            model.addAttribute("result",result);
            return "admin/book/loginPage";
        }else{
            return "redirect: /admin/index";
        }
    }
    @RequestMapping("index")
    public String index(Model model){
        int bookCount = booksService.count();

        int userCount = userService.count();
        int userNewAdd = userService.countYesterday();

        int commentCount = commentsService.count();
        int commentCountYesterday = commentsService.countYesterday();

        model.addAttribute("bookCount",bookCount);
        model.addAttribute("userCount",userCount);
        model.addAttribute("userNewAdd",userNewAdd);
        model.addAttribute("commentCount",commentCount);
        model.addAttribute("commentCountYesterday",commentCountYesterday);

        return "admin/book/index";
    }
}
