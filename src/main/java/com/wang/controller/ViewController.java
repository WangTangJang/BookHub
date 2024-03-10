package com.wang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {
    @RequestMapping("/Header")
    public String Header(){
        return "userDisplay/component/Header";
    }
    @RequestMapping("BookDetails")
    public String BookDetails(){
        return "userDisplay/component/BookDetails";
    }
}
