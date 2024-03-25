package com.wang.model.result;

import com.wang.model.Comment;
import com.wang.model.User;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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

    private String creationTime;

    private String updateTime;

    private String context;

    private List<CommentResult> replies; //存储回复评论的列表

    private String userName;

    private String userAvatar;

    private long userLevel;

    private String parentUsername;

    private static final long serialVersionUID = 1L;

    public CommentResult(Comment comment) {
        this.id = comment.getId();
        this.userId = comment.getUserId();
        this.bookId = comment.getBookId();
        this.parentCommentId = comment.getParentCommentId();
        this.likes = comment.getLikes();
        this.dislikes = comment.getDislikes();
        // 将Date类型转换为String类型
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        // 此处有误
        Date utilDate = new Date(comment.getCreationTime().getTime());
        Instant instant = utilDate.toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        creationTime = formatter.format(zonedDateTime);
        if (comment.getUpdateTime() != null){
            updateTime = formatter.format(comment.getUpdateTime().toInstant().atZone(ZoneId.systemDefault()));
        }
        this.context = comment.getContext();
    }
}
