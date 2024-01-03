package com.wang.model;


public class Bookshelf {

  private long id;
  private long userId;
  private long bookId;
  private String status;
  private java.sql.Date joinDate;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getBookId() {
    return bookId;
  }

  public void setBookId(long bookId) {
    this.bookId = bookId;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public java.sql.Date getJoinDate() {
    return joinDate;
  }

  public void setJoinDate(java.sql.Date joinDate) {
    this.joinDate = joinDate;
  }

}
