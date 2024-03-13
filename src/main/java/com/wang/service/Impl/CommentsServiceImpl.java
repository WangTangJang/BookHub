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
    public Map<Integer,CommentResult> getCommentByBookId(int bookId) {

        return organizeCommentsIntoTree(mapper.selectByBookId(bookId));
    }

    /* 这才是该写的东西！！！ */
    // 评论的树形结构
    private Map<Integer,CommentResult> organizeCommentsIntoTree(List<Comment> comments) {
        // 评论id和评论的映射
        Map<Integer, CommentResult> commentResultMap = new HashMap<>();
        for (Comment comment : comments) {
            User user = userService.selectById(comment.getUserId());
            CommentResult commentResult = new CommentResult(comment);
            commentResult.setUserName(user.getUsername());
            commentResult.setUserAvatar(user.getCountry());
            commentResult.setUserLevel(user.getLevel());
            commentResultMap.put(comment.getId(), commentResult);
        }

        // 遍历评论，判断哪些是根评论，哪些是子评论
        Map<Integer,CommentResult> rootCommentResults = new HashMap<>();
        List<CommentResult> childCommentResults = new ArrayList<>();
        for (CommentResult commentResult : commentResultMap.values()) {
            if (commentResult.getParentCommentId() == null) {
                rootCommentResults.put(commentResult.getId(),commentResult);
            } else {
                childCommentResults.add(commentResult);
            }
        }
        // 将子评论放到对应的根评论下
        for (CommentResult childCommentResult : childCommentResults) {
            String parentUsername = getParentUsername(childCommentResult);
            childCommentResult.setParentUsername(parentUsername);
            Integer rootId = getRootComment(childCommentResult);
            if (rootCommentResults.get(rootId).getReplies() == null){
                rootCommentResults.get(rootId).setReplies(new ArrayList<>());
            }
            rootCommentResults.get(rootId).getReplies().add(childCommentResult);
        }
        return rootCommentResults;
    }

    // 获取一个评论的父评论主人
    private String getParentUsername(CommentResult commentResult){
        Comment comment = service.getCommentById(commentResult.getParentCommentId());
        User user = userService.selectById(comment.getUserId());
        return user.getUsername();
    }

    // 获取一个评论的根评论
    private Integer getRootComment(CommentResult commentResult) {
        while(commentResult.getParentCommentId() != null) {
            Comment comment = mapper.selectByPrimaryKey(commentResult.getParentCommentId());
            commentResult = new CommentResult(comment);
        }
        return commentResult.getId();
    }


    private void validate(String context) {
        if (context == null || context.trim().isEmpty()) {
            throw new IllegalArgumentException("评论内容不能为空");
        }
    }
}
