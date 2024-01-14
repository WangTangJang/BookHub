package com.wang.service;

public interface CommentVoteService {

    void likeComment(long userId , long commentId);

    void dislikeComment(int userId ,int commentId);

    void cancelVote(long userId , long commentId);

}
