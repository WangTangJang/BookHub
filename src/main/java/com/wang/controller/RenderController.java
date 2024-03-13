package com.wang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/render")
public class RenderController {


    @RequestMapping("/bookDisplay")
    public String bookDisplay(@RequestBody Map<String, Object> map, Model model){

        model.addAttribute("books",map.get("books"));
        return "userDisplay/component/indexBody :: #aBook";
    }

    @RequestMapping("/header")
    public String Header(){
        return "userDisplay/component/Header";
    }
    @RequestMapping("/bookDetails")
    public String BookDetails(@RequestBody Map<String, Object> map, Model model){
        model.addAttribute("book",map.get("bookInfo"));
        model.addAttribute("userRating",map.get("userRating"));
        model.addAttribute("collected",map.get("collected"));
        return "userDisplay/component/BookDetails:: #bookInfoPage";
    }
    @RequestMapping("/commentsSection")
    public String commentsSection(@RequestBody Map<String, Object> map, Model model){
        model.addAttribute("rootComments",map.get("rootComments"));
        return "userDisplay/component/commentsSection";
    }
}
