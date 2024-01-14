package com.wang.service.Impl;

import com.wang.mapper.CommentVotesMapper;
import com.wang.model.CommentVotes;
import com.wang.model.Comments;
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

        if(mapper.select(userId,commentId) != null){
            // 如果已经投票过了，将原来的投票取消
            commentVoteService.cancelVote(userId, commentId);
        }

        CommentVotes commentVotes = new CommentVotes();
        commentVotes.setUserId(userId);
        commentVotes.setCommentId(commentId);
        commentVotes.setVoteType("like");
        mapper.insert(commentVotes);
        Comments comments = commentsService.getCommentById((int) commentId);
        comments.setLikes(comments.getLikes() + 1); // 给评论点赞量+1
        commentsService.updateVote(comments);
    }

    @Override
    public void dislikeComment(int userId ,int commentId) {
        if(mapper.select(userId,commentId) != null){
            // 如果已经投票过了，将原来的投票取消
            commentVoteService.cancelVote(userId, commentId);
        }

        CommentVotes commentVotes = new CommentVotes();
        commentVotes.setUserId(userId);
        commentVotes.setCommentId(commentId);
        commentVotes.setVoteType("dislike");
        mapper.insert(commentVotes);

        Comments comments = commentsService.getCommentById(commentId);
        comments.setDislikes(comments.getDislikes() + 1); // 给评论点踩量+1
        commentsService.updateVote(comments);
    }


    // 取消投票
    @Override
    public void cancelVote(long userId, long commentId) {

        CommentVotes commentVotes = mapper.select(userId, commentId);
        Comments comments = commentsService.getCommentById((int) commentId);

        if (commentVotes.getVoteType().equals("like")) {
            comments.setLikes(comments.getLikes() - 1); // 给评论点赞量-1
        }else {
            comments.setDislikes(comments.getDislikes() - 1); // 给评论点踩量-1
        }

        commentsService.updateVote(comments);
        mapper.delete(userId, commentId);
    }


}
