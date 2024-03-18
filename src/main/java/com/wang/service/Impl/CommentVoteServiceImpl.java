package com.wang.service.Impl;

import com.wang.mapper.CommentVotesMapper;
import com.wang.model.Comment;
import com.wang.model.CommentVotes;
import com.wang.service.CommentVoteService;
import com.wang.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentVoteServiceImpl implements CommentVoteService {

    @Autowired
    CommentVotesMapper mapper;

    @Autowired
    CommentsService commentsService;

    @Autowired
    CommentVoteService commentVoteService;

    @Override
    public void likeComment(long userId , long commentId) {
        // 调用投票方法
        voteComment(userId, commentId, "like");
    }

    @Override
    public void dislikeComment(long userId , long commentId) {
        // 调用投票方法
        voteComment(userId, commentId, "dislike");
    }

    // 投票方法, voteType为like或dislike, 用于判断是点赞还是点踩, 以便修改评论的点赞量或点踩量
    public void voteComment(long userId , long commentId, String voteType) {
        CommentVotes oldCommentVotes = mapper.select(userId,commentId);
        // 原来是否投过票
        if (oldCommentVotes!=null){
            // 如果原来投的票与现在投的票相同则抛出
            if (oldCommentVotes.getVoteType().equals(voteType)){
                throw new IllegalArgumentException("您已经投过相同类型的票！");
            }else {
                commentVoteService.cancelVote(userId, commentId);
            }
        }
        CommentVotes commentVotes = new CommentVotes();
        commentVotes.setUserId(userId);
        commentVotes.setCommentId(commentId);
        commentVotes.setVoteType(voteType);
        mapper.insert(commentVotes);
        Comment comment = commentsService.getCommentById((int) commentId);
        if (voteType.equals("like")) {
            comment.setLikes(comment.getLikes() + 1); // 给评论点赞量+1
        }else {
            comment.setDislikes(comment.getDislikes() + 1); // 给评论点踩量+1
        }
        commentsService.updateVote(comment);
    }

    // 取消投票
    @Override
    public void cancelVote(long userId, long commentId) {

        CommentVotes commentVotes = mapper.select(userId, commentId);
        Comment comment = commentsService.getCommentById(commentId);
        if (commentVotes.getVoteType().equals("like")) {
            comment.setLikes(comment.getLikes() - 1); // 给评论点赞量-1
        }else {
            comment.setDislikes(comment.getDislikes() - 1); // 给评论点踩量-1
        }

        commentsService.updateVote(comment);
        mapper.delete(userId, commentId);
    }
}
