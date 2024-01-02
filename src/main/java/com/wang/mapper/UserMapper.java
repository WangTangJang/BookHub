package com.wang.mapper;

import com.wang.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    void insert(User user);
    void delete();
    void update();
    User select(int id);

    User selectPro(User user);

    void login(@Param("username") String username,@Param("password") String password);

    void rateBook(@Param("userId") String userId,@Param("bookId") String bookId,@Param("rating") int rating);

    void addComment(@Param("userId") String userId,@Param("bookId") String bookId,@Param("content") int content);

    void likeComment(@Param("userId") String userId,@Param("commentId") String commentId);
    void disLikeComment(@Param("userId") String userId,@Param("commentId") String commentId);


}
