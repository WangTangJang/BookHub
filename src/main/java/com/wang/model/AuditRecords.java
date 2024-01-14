package com.wang.model;


public class AuditRecords {

  private long id;
  private long bookId;
  private String result;
  private java.sql.Timestamp auditTime;
  private long auditorId;
  private long submitterId;
  private String comments;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getBookId() {
    return bookId;
  }

  public void setBookId(long bookId) {
    this.bookId = bookId;
  }


  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }


  public java.sql.Timestamp getAuditTime() {
    return auditTime;
  }

  public void setAuditTime(java.sql.Timestamp auditTime) {
    this.auditTime = auditTime;
  }


  public long getAuditorId() {
    return auditorId;
  }

  public void setAuditorId(long auditorId) {
    this.auditorId = auditorId;
  }


  public long getSubmitterId() {
    return submitterId;
  }

  public void setSubmitterId(long submitterId) {
    this.submitterId = submitterId;
  }


  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

}
