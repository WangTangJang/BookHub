package com.wang.controller;


import com.wang.model.User;
import com.wang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String register(User user){
        userService.UserRegister(user);
        return "redirect:/toIndex";
    }

    @RequestMapping("/login")
    public String login(User user , HttpSession session, Model model){
        String result = userService.login(user.getUsername(), user.getPassword());
        if(result.equals("ok")){
            session.setAttribute("username",user.getUsername());
            return "redirect:/toIndex";
        }else {
            model.addAttribute("message",result);
            return "redirect:/toLogin";
        }
    }
}
