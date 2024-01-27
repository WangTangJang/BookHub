package com.wang.model;


public class Books {

  private long id;
  private String title;
  private String author;
  private String isbn;
  private String format;
  private String filePath;
  private String uploadedBy;
  private String status;
  private String cover;
  private long categoryId;
  private double fileSize;

  private double averageRating;
  private long reviewsCount;
  private long ratingCount;
  private long shelfCount;

  public Books() {

  }

  public Books(double averageRating, long reviewsCount, long ratingCount, long shelfCount) {
    this.averageRating = averageRating;
    this.reviewsCount = reviewsCount;
    this.ratingCount = ratingCount;
    this.shelfCount = shelfCount;
  }

  public Books(long id, String title, String author, String isbn, String format, String filePath, double fileSize) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.isbn = isbn;
    this.format = format;
    this.filePath = filePath;
    this.fileSize = fileSize;
  }

  public long getId() {
    return id;
  }

  public String getCover() {
    return cover;
  }

  public void setCover(String cover) {
    this.cover = cover;
  }

  public long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
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

  public String getUploadedBy() {
    return uploadedBy;
  }

  public void setUploadedBy(String uploadedBy) {
    this.uploadedBy = uploadedBy;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
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

  public double getFileSize() {
    return fileSize;
  }

  public void setFileSize(double fileSize) {
    this.fileSize = fileSize;
  }

  public double getAverageRating() {
    return averageRating;
  }

  public void setAverageRating(double averageRating) {
    this.averageRating = averageRating;
  }

  public long getReviewsCount() {
    return reviewsCount;
  }

  public void setReviewsCount(long reviewsCount) {
    this.reviewsCount = reviewsCount;
  }

  public long getRatingCount() {
    return ratingCount;
  }

  public void setRatingCount(long ratingCount) {
    this.ratingCount = ratingCount;
  }

  public long getShelfCount() {
    return shelfCount;
  }

  public void setShelfCount(long shelfCount) {
    this.shelfCount = shelfCount;
  }
}
