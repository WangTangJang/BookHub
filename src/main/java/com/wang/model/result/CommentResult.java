package com.wang.model.result;

import com.wang.model.Comment;
import com.wang.model.User;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class CommentResult implements Serializable {


    private Integer id;

    private Integer userId;

    private Integer bookId;

    private Integer parentCommentId;

    private Integer likes;

    private Integer dislikes;

    private Date creationTime;

    private Date updateTime;

    private String context;

    private List<CommentResult> replies; //存储回复评论的列表

    private String userName;

    private String userAvatar;

    private long userLevel;

    private static final long serialVersionUID = 1L;

    public CommentResult(Comment comment) {
        this.id = comment.getId();
        this.userId = comment.getUserId();
        this.bookId = comment.getBookId();
        this.parentCommentId = comment.getParentCommentId();
        this.likes = comment.getLikes();
        this.dislikes = comment.getDislikes();
        this.creationTime = comment.getCreationTime();
        this.updateTime = comment.getUpdateTime();
        this.context = comment.getContext();
    }
}
