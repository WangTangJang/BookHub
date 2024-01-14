package com.wang.model;


public class CommentVotes {

  private long userId;
  private long commentId;
  private String voteType;
  private java.sql.Timestamp voteDate;


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getCommentId() {
    return commentId;
  }

  public void setCommentId(long commentId) {
    this.commentId = commentId;
  }


  public String getVoteType() {
    return voteType;
  }

  public void setVoteType(String voteType) {
    this.voteType = voteType;
  }


  public java.sql.Timestamp getVoteDate() {
    return voteDate;
  }

  public void setVoteDate(java.sql.Timestamp voteDate) {
    this.voteDate = voteDate;
  }

}
