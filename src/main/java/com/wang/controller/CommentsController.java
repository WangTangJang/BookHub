package com.wang.controller;

import com.wang.model.Comment;
import com.wang.model.result.CommentResult;
import com.wang.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentService;

    @RequestMapping("/get/{id}")
    public ResponseEntity<?> getComments(@PathVariable int id){
        // 存放的每条根评论及其对应的ID
        Map<Integer,CommentResult> rootComments = commentService.getCommentByBookId(id);
        Map<String,Object> result = new HashMap<>();
        result.put("rootComments",rootComments);
        result.put("message","success");
        return ResponseEntity.ok(result);
    }
}
