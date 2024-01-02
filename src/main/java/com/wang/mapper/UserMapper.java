package com.wang.mapper;

import com.wang.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {


    User login(@Param("name") String name,@Param("password") String password);

    void register (User user);

    void insert(User user);
    void delete(User user);
    void update(User user);
    void select(User user);

}
