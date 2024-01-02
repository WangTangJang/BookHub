package com.wang.model;


public class Books {

  private long id;
  private String title;
  private String author;
  private String isbn;
  private String format;
  private String filePath;
  private double averageRating;
  private long totalReviews;
  private double fileSize;
  private long added;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }


  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }


  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }


  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }


  public double getAverageRating() {
    return averageRating;
  }

  public void setAverageRating(double averageRating) {
    this.averageRating = averageRating;
  }


  public long getTotalReviews() {
    return totalReviews;
  }

  public void setTotalReviews(long totalReviews) {
    this.totalReviews = totalReviews;
  }


  public double getFileSize() {
    return fileSize;
  }

  public void setFileSize(double fileSize) {
    this.fileSize = fileSize;
  }


  public long getAdded() {
    return added;
  }

  public void setAdded(long added) {
    this.added = added;
  }

}
