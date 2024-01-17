package com.wang.service;

/**
 * 评论投票服务
 * 应该有的功能：
 * 1. 点赞评论
 * 2. 点踩评论
 * 3. 取消投票
 */
public interface CommentVoteService {


    /**
     * 点赞评论
     * @param userId 用户id
     * @param commentId 评论id
     */
    void likeComment(long userId , long commentId);

    /**
     * 点踩评论
     * @param userId 用户id
     * @param commentId 评论id
     */
    void dislikeComment(long userId , long commentId);

    /**
     * 取消投票
     * @param userId 用户id
     * @param commentId 评论id
     */
    void cancelVote(long userId , long commentId);

}
