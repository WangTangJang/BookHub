package com.wang.controller;

import com.wang.model.Comment;
import com.wang.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentService;

    @RequestMapping("/get/{id}")
    public ResponseEntity<?> getComments(@PathVariable int id){
        List<Comment> comments = commentService.getCommentByBookId(id);
        return ResponseEntity.ok(comments);
    }
}
