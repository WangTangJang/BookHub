package com.wang.controller.admin;

import com.wang.model.Books;
import com.wang.model.Comment;
import com.wang.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin/comment")
public class AdminCommentController {

    @Autowired
    private CommentsService commentsService;

    @GetMapping("/list")
    public String listBooks(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size,
                            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Comment> commentsPage = commentsService.findAllComment(pageable);
        model.addAttribute("comments", commentsPage.getContent());
        model.addAttribute("currentPage", commentsPage.getNumber());
        model.addAttribute("totalPages", commentsPage.getTotalPages());
        model.addAttribute("totalItems", commentsPage.getTotalElements());
        return "admin/book/commentList";
    }


    @GetMapping("/toEdit/{id}")
    public String toEdit(@PathVariable("id") Long commentId, Model model){

        Comment comment = commentsService.getCommentById(commentId);
        model.addAttribute("comment", comment);

        return "admin/book/commentEdit";
    }

    @PostMapping("/doEdit")
    public String doEdit(@ModelAttribute("comment") Comment comment ){

        commentsService.update(comment);

        return "redirect: /admin/comment/list";
    }
    @GetMapping("doDel/{id}")
    public String doDel(@PathVariable("id") int commentId){
        commentsService.deleteComment(commentId);
        return "redirect: /admin/comment/list";
    }
}
