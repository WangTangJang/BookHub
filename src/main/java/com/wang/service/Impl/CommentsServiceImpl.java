package com.wang.service.Impl;

import com.wang.mapper.CommentsMapper;
import com.wang.model.Comment;
import com.wang.model.User;
import com.wang.model.result.CommentResult;
import com.wang.service.CommentsService;
import com.wang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsMapper mapper;

    @Autowired
    private CommentsService service;

    @Autowired
    private UserService userService;

    @Override
    public void addComment(Comment comment) {
        validate(comment.getContext());

        // 在业务逻辑中设置初始值
        comment.setLikes(0);
        comment.setDislikes(0);
        mapper.insertSelective(comment);
    }

    @Override
    public Comment getCommentById(long id) {
        return mapper.selectByPrimaryKey((int) id);
    }

    @Override
    public void updateContext(Integer commentId, String newContext) {

        validate(newContext);
        Comment comment = service.getCommentById(commentId);;
        comment.setContext(newContext);
        mapper.updateByPrimaryKeySelective(comment);
    }

    @Override
    public void deleteComment(Integer commentId) {
        mapper.deleteByPrimaryKey(commentId);
    }

    @Override
    public void updateVote(Comment comment) {
        mapper.updateByPrimaryKey(comment);
    }

    @Override
    public List<CommentResult> getCommentByBookId(int bookId) {

        return organizeCommentsIntoTree(mapper.selectByBookId(bookId));
    }

    /* 这才是该写的东西！！！ */
    // 评论的树形结构
    private List<CommentResult> organizeCommentsIntoTree(List<Comment> comments) {

        // 评论id和评论的映射
        Map<Integer, CommentResult> commentResultMap = new HashMap<>();
        for (Comment comment : comments){
            User user = userService.selectById(comment.getUserId());
            CommentResult commentResult = new CommentResult(comment);
            commentResult.setUserName(user.getUsername());
            commentResult.setUserAvatar(user.getCountry());
            commentResult.setUserLevel(user.getLevel());
            commentResultMap.put(comment.getId(), commentResult);
        }

        // 评论的树形结构
        List<CommentResult> commentResults = new ArrayList<>();
        // 遍历所有评论
        for (Comment comment : comments){
            // 如果是一级评论
            if (comment.getParentCommentId() == null){
                // 添加到树形结构中
                commentResults.add(commentResultMap.get(comment.getId()));
            } else {
                // 如果是二级评论
                // 获取父评论
                CommentResult parentCommentResult = commentResultMap.get(comment.getParentCommentId());
                if (parentCommentResult == null){
                    throw new RuntimeException("父评论不存在");
                }else {
                    // 添加到父评论的子评论中
                    if (parentCommentResult.getReplies() == null){
                        parentCommentResult.setReplies(new ArrayList<>());
                    }
                    parentCommentResult.getReplies().add(commentResultMap.get(comment.getId()));
                }
            }
        }
        return commentResults;
    }

    private void validate(String context) {
        if (context == null || context.trim().isEmpty()) {
            throw new IllegalArgumentException("评论内容不能为空");
        }
    }
}
