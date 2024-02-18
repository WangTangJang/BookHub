package com.wang.controller;

import com.wang.model.Comment;
import com.wang.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CommentsController {

    @Autowired
    private CommentsService commentService;

    @RequestMapping("/viewComments/{id}")
    public String getComments( Model model, @PathVariable int id){
        List<Comment> comments = commentService.getCommentByBookId(id);
        model.addAttribute("comments", comments);
        return "userDisplay/component/CommentsSection";
    }
}
