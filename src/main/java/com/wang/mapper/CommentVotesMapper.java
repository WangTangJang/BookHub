package com.wang.mapper;

import com.wang.model.CommentVotes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentVotesMapper {

    int insert(CommentVotes record);


    int update(CommentVotes record);

    int delete(@Param("userId") long userId, @Param("commentId") long commentId);

    CommentVotes select(@Param("userId") long userId, @Param("commentId") long commentId);
}
