package com.wang.mapper;

import com.wang.model.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentsMapper {
    int deleteByPrimaryKey(Integer id);

    List<Comment> selectAll();

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> selectByBookId(int bookId);


    List<Comment> selectPage(@Param("start") int start, @Param("size") int size);

    int count();
}
