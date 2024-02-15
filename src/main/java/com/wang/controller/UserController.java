package com.wang.controller;


import com.wang.model.User;
import com.wang.service.BookRatingsService;
import com.wang.service.BooksService;
import com.wang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private BookRatingsService bookRatingsService;

    @Autowired
    private BooksService booksService;

    @RequestMapping("/register")
    public String register(User user){
        userService.UserRegister(user);
        return "redirect:/toIndex";
    }

    /**
     * 登录
     * @param data 用户名和密码
     * @param session 会话
     * @param model 模型
     * @return 登录结果
     */
    @PostMapping("/login")
    public String login(@RequestBody Map<String,String> data, HttpSession session, Model model){
        String result = userService.login(data.get("username"), data.get("password"));
        User user = userService.selectByUsername(data.get("username"));
        if(result.equals("ok")){
            session.setAttribute("user",user);
        }else {
            model.addAttribute("errorMessage",result);
        }
        return "userDisplay/component/Header :: userCenter";
    }
    @PostMapping ("/logout")
    public String  logout(HttpSession session){
        if (session.getAttribute("user")!=null){
            session.removeAttribute("user");
        }else {
        }
        return "userDisplay/component/Header :: userCenter";

    }

    @PostMapping("saveRating")
    public String saveRating(@RequestBody Map<String,Integer> data, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user != null) {
            int bookId = data.get("bookId");
            int rating = data.get("rating");
            bookRatingsService.rateBook(user.getId(),bookId, rating);
            double AverageRating =  booksService.selectById(bookId).getAverageRating();
            // 返回成功的响应
        }
        return "userDisplay/component/BookDetails :: bookDetails";
    }
}