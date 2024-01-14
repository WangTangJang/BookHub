package com.wang.service;

import com.wang.model.Comments;

public interface CommentsService {

    /**
     * 添加评论
     * @param comments 评论实体
     */
    void addComment(Comments comments);

    /**
     * 通过id获取评论
     * @param id 评论的id
     * @return 一条评论
     */
    Comments getCommentById(Integer id);

    /**
     * 修改一个评论
     * @param commentId 评论的id
     * @param newContext 新的评论
     */
    void updateContext(Integer commentId, String newContext);

    void updateVote(Comments comments);
}
