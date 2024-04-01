package com.wang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/render")
public class RenderController {


    @RequestMapping("/bookDisplay")
    public String bookDisplay(@RequestBody Map<String, Object> map, Model model){
        model.addAttribute("books",map.get("books"));
        return "userDisplay/component/indexBody :: #aBook";
    }
    @RequestMapping("/searchResult")
    public String searchResult(@RequestBody Map<String, Object> map, Model model){
        model.addAttribute("books",map.get("books"));
        return "userDisplay/component/indexBody";
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
        model.addAttribute("bookId",map.get("bookId"));
        model.addAttribute("reviewsCount",map.get("reviewsCount"));
        return "userDisplay/component/commentsSection:: #commentsSectionPage";
    }
    @RequestMapping("/reader")
    public String reader(@RequestBody Map<String, Object> map,Model model){
        model.addAttribute("bookUrl",map.get("bookUrl"));
        return "userDisplay/reader:: #bookReader";
    }
    @RequestMapping("/readers")
    public String readers(Model model){
        model.addAttribute("bookUrl","http://localhost:8081/bookFiles/%E9%98%BF%E7%91%9F%C2%B7%E5%85%8B%E6%8B%89%E5%85%8B%E8%87%B3%E9%AB%98%E7%A7%91%E5%B9%BB%E7%BB%8F%E5%85%B8%EF%BC%88%E5%A5%97%E8%A3%85%E5%85%B15%E5%86%8C%EF%BC%89%EF%BC%88%E6%80%AA%E4%B8%8D%E5%BE%97%E6%98%AF%E5%88%98%E6%85%88%E6%AC%A3%E7%9A%84%E5%81%B6%E5%83%8F%EF%BC%81%E9%98%BF%E7%91%9F%C2%B7%E5%85%8B%E6%8B%89%E5%85%8B%EF%BC%8C%E4%BC%9F%E5%A4%A7%E7%9A%84%E5%A4%AA%E7%A9%BA%E9%A2%84%E8%A8%80%E5%AE%B6%EF%BC%81%E4%BB%96%E6%98%AF%E2%80%9C%E7%A7%91%E5%B9%BB%E4%B8%89%E5%B7%A8%E5%A4%B4%E2%80%9D%E4%B9%8B%E4%B8%80%EF%BC%8C%E6%AF%94%E8%82%A9%E9%98%BF%E8%A5%BF%E8%8E%AB%E5%A4%AB%EF%BC%8C%E6%98%AF%E7%9C%9F%E6%AD%A3%E7%9A%84%E7%A7%91%E5%B9%BB%E5%A4%A7%E5%B8%88%EF%BC%81%EF%BC%89%20-%20%E9%98%BF%E7%91%9F%C2%B7%E5%85%8B%E6%8B%89%E5%85%8B.epub");
        return "userDisplay/reader";
    }
}
