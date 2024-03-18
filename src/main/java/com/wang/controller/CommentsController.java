package com.wang.controller;

import com.wang.model.Comment;
import com.wang.model.User;
import com.wang.model.result.CommentResult;
import com.wang.service.BooksService;
import com.wang.service.CommentVoteService;
import com.wang.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.awt.print.Book;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentService;

    @Autowired
    private BooksService booksService;

    @Autowired
    private CommentVoteService commentVoteService;

    @RequestMapping("/get/{id}")
    public ResponseEntity<?> getComments(@PathVariable int id){
        // 存放的每条根评论及其对应的ID
        List<CommentResult> rootComments = commentService.getCommentByBookId(id);
        Map<String,Object> result = new HashMap<>();
        result.put("rootComments",rootComments);
        result.put("message","success");
        result.put("bookId",id);
        result.put("reviewsCount",booksService.selectById(id).getReviewsCount());

        return ResponseEntity.ok(result);
    }
    @PostMapping("/sendComment")
    public ResponseEntity<?> sendComment(@RequestBody Map<String,Object> map, HttpSession session){
        String comment = (String) map.get("commentContent");
        User user = (User) session.getAttribute("user");
        int userId = (int) user.getId();
        int bookId =Integer.parseInt((String) map.get("bookId")) ;
        commentService.addComment(bookId,userId,comment);
        return getComments(bookId);
    }
    @PostMapping("/like")
    public ResponseEntity<?> like(@RequestBody Map<String,Object> map, HttpSession  session){
        User user = (User) session.getAttribute("user");
        int bookId = Integer.parseInt((String) map.get("bookId"));
        int commentId = Integer.parseInt((String) map.get("commentId"));
        try {
            commentVoteService.likeComment(user.getId(),commentId);
            return getComments(bookId);
        }catch (Exception e){
            Map<String,Object> result = new HashMap<>();
            result.put("message",e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PostMapping("/dislike")
    public ResponseEntity<?> dislike(@RequestBody Map<String,Object> map, HttpSession  session){
        User user = (User) session.getAttribute("user");
        int bookId = Integer.parseInt((String) map.get("bookId"));
        int commentId = Integer.parseInt((String) map.get("commentId"));
        try {
            commentVoteService.dislikeComment(user.getId(),commentId);
            return getComments(bookId);
        }catch (Exception e){
            Map<String,Object> result = new HashMap<>();
            result.put("message",e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }
}
