package com.wang.model.result;

import com.wang.model.Books;
import lombok.Data;

@Data
public class BookDetailResult {
    private Books bookInfo;
    private int userRating;
    private boolean collected;
    private String message;
}
