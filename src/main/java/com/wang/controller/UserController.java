package com.wang.controller;


import com.wang.model.Books;
import com.wang.model.User;
import com.wang.service.BookRatingsService;
import com.wang.service.BooksService;
import com.wang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
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

    /**
     * 注册
     * @return 注册结果
     */
    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody Map<String, Object> data) {
        User user = new User();
        user.setUsername(data.get("username").toString());
        user.setPassword(data.get("password").toString());
        user.setEmail(data.get("email").toString());
        String result = userService.UserRegister(user);
        if (result.equals("注册成功")) {
            return ResponseEntity.ok(Map.of("message", result));
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
    /**
     * 登录
     * @param data 用户名和密码
     * @param session 会话
     * @param model 模型
     * @return 登录结果
     */
    @PostMapping( value = "/login")
    public  ResponseEntity<?> login(@RequestBody Map<String, String> data, HttpSession session, Model model){
        String username = data.get("username");
        String password = data.get("password");
        String result = userService.login(username, password); // 登录结果

        if(result.equals("登录成功")){ // 登录成功
            User user = userService.selectByUsername(username); // 查询用户
            session.setAttribute("user",user); // 保存用户
            return ResponseEntity.ok(Map.of("message",result));
        }else {
            return ResponseEntity.badRequest().body(result);
        }
    }
    @PostMapping (value = "/logout")
    public ResponseEntity<?>  logout(HttpSession session){
        if (session.getAttribute("user")!=null){
            session.removeAttribute("user");
            return ResponseEntity.ok(Map.of("message", "退出成功"));
        }else {
            return ResponseEntity.badRequest().body("退出失败");
        }
    }

    @PostMapping("saveRating")
    public String saveRating(@RequestBody Map<String,Integer> data, HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        if (user != null) {
            int bookId = data.get("bookId");
            int rating = data.get("rating");
            bookRatingsService.rateBook(user.getId(),bookId, rating);
            model.addAttribute("userRating",rating);
        }
        Books books = booksService.selectById(data.get("bookId"));
        model.addAttribute("book",books);
        return "userDisplay/component/BookDetails";
    }
}