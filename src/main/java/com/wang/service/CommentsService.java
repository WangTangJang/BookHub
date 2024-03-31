package com.wang.service;

import com.wang.model.Comment;
import com.wang.model.result.CommentResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

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
     * @param comment 评论实体
     */
    void addComment(int bookId,int userId,String comment);

    void replyComment(int parentId,int userId,int bookId,String comment);

    /**
     * 通过id获取评论
     * @param id 评论的id
     * @return 一条评论
     */
    Comment getCommentById(long id);

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
     * @param comment 评论实体
     */
    void updateVote(Comment comment);

    List<CommentResult> getCommentByBookId(int bookId);

    List<CommentResult> getCommentByLike(int bookId);
    // 评论的数量
    int count();

    // 昨日新增评论数量
    int countYesterday();

    Page<Comment> findAllComment(Pageable pageable);

    void update(Comment comment);

}
