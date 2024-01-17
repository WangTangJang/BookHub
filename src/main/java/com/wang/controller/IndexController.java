package com.wang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {

    // 跳转到首页
    @RequestMapping("/toIndex")
    public String toIndex(){
        return "index";
    }
}
