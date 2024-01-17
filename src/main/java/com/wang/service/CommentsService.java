package com.wang.service;

import com.wang.model.Comments;

/**
 * 评论服务
 * 应该有的功能：
 * 1. 添加评论
 * 2. 通过id获取评论
 * 3. 修改评论
 * 4. 更新评论的点赞数和点踩数
 * 5. 删除评论
 */

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
    Comments getCommentById(long id);

    /**
     * 修改一个评论
     * @param commentId 评论的id
     * @param newContext 新的评论
     */
    void updateContext(Integer commentId, String newContext);

    /**
     * 删除评论
     * @param commentId 评论的id
     */
    void deleteComment(Integer commentId);

    /**
     * 更新评论的点赞数和点踩数
     * @param comments 评论实体
     */
    void updateVote(Comments comments);
}
