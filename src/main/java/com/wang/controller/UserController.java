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

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody Map<String,String> data, HttpSession session, Model model){
        String result = userService.login(data.get("username"), data.get("password"));
        User user = userService.selectByUsername(data.get("username"));
        if(result.equals("ok")){
            session.setAttribute("user",user);
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }else {
            model.addAttribute("errorMessage",result);
            return new ResponseEntity<>("error", HttpStatus.OK);
        }
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session,HttpServletRequest request){
        String referer = request.getHeader("Referer");
        session.removeAttribute("user");
        if (referer != null){
            return "redirect:"+referer;
        }
        return "redirect:/toIndex";
    }

    @PostMapping("saveRating")
    @ResponseBody
    public ResponseEntity<Double> saveRating(@RequestBody Map<String,Integer> data, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user != null) {

            int bookId = data.get("bookId");
            int rating = data.get("rating");

            bookRatingsService.rateBook(user.getId(),bookId, rating);
            double AverageRating =  booksService.selectById(bookId).getAverageRating();
            // 返回成功的响应
            return new ResponseEntity<>(AverageRating, HttpStatus.OK);
        } else {
            // 如果用户未登录，返回未授权的响应
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}