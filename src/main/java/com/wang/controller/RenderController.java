package com.wang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/render")
public class RenderController {
    @RequestMapping("/header")
    public String Header(){
        return "userDisplay/component/Header";
    }
    @RequestMapping("/bookDetails")
    public String BookDetails(@RequestBody Map<String, Object> map, Model model){
        model.addAttribute("book",map.get("bookInfo"));
        model.addAttribute("userRating",map.get("userRating"));
        model.addAttribute("collected",map.get("collected"));
        return "userDisplay/component/BookDetails";
    }
    @RequestMapping("/commentsSection")
    public String commentsSection(){
        return "userDisplay/component/commentsSection";
    }
}
