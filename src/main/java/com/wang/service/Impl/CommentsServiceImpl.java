package com.wang.service.Impl;

import com.wang.mapper.CommentsMapper;
import com.wang.model.Comments;
import com.wang.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsMapper mapper;

    @Autowired
    private CommentsService service;

    @Override
    public void addComment(Comments comments) {
        validate(comments.getContext());

        // 在业务逻辑中设置初始值
        comments.setLikes(0);
        comments.setDislikes(0);
        mapper.insertSelective(comments);
    }

    @Override
    public Comments getCommentById(long id) {
        return mapper.selectByPrimaryKey((int) id);
    }

    @Override
    public void updateContext(Integer commentId, String newContext) {

        validate(newContext);
        Comments comments = service.getCommentById(commentId);;
        comments.setContext(newContext);
        mapper.updateByPrimaryKeySelective(comments);
    }

    @Override
    public void deleteComment(Integer commentId) {
        mapper.deleteByPrimaryKey(commentId);
    }

    @Override
    public void updateVote(Comments comments) {
        mapper.updateByPrimaryKey(comments);
    }

    private void validate(String context) {
        if (context == null || context.trim().isEmpty()) {
            throw new IllegalArgumentException("评论内容不能为空");
        }
    }
}
