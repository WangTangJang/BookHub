package com.wang.service.Impl;

import com.wang.mapper.CommentsMapper;
import com.wang.model.Comment;
import com.wang.service.CommentsService;
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
    public List<Comment> getCommentByBookId(int bookId) {

        return organizeCommentsIntoTree(mapper.selectByBookId(bookId));
    }
    /* 这才是该写的东西！！！ */
    // 评论的树形结构
    private List<Comment> organizeCommentsIntoTree(List<Comment> comments) {
        Map<Integer, Comment> commentsMap = new HashMap<>();
        for (Comment comment : comments) {
            commentsMap.put(comment.getId(),comment);
        }
        // 遍历评论列表，将回复评论添加到对应的父评论的 replies 列表中
        List<Comment> treeComments = new ArrayList<>();
        // 循环遍历从数据库中查询到某书籍下的评论列表
        for (Comment comment : comments){
            // 如果某条评论有父级
            if (comment.getParentCommentId() != null ){
                // 就获取到他的父级评论
                Comment parentComment = commentsMap.get(comment.getParentCommentId());
                if (parentComment != null){
                    // 如果父级评论的回复列表为空，就创建一个新的列表
                    if (parentComment.getReplies() == null){
                        parentComment.setReplies(new ArrayList<>());
                    }
                    // 将这条评论加入到它的父级中
                    parentComment.getReplies().add(comment);
                }
            }else {
                // 如果没有父级，就直接加入到树形评论列表中
                treeComments.add(comment);
            }
        }
        return treeComments;
    }

    private void validate(String context) {
        if (context == null || context.trim().isEmpty()) {
            throw new IllegalArgumentException("评论内容不能为空");
        }
    }
}
