package com.wang.mapper;

import com.wang.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     * 插入用户信息
     * @param user 用户信息
     */
    void insert(User user);

    /**
     * 删除用户
     * 通过实体内的信息找到需要删除的用户
     * @param id 用户id
     */
    void deleteOriginal(int id);

    void deleteDynamic(int id);
    /**
     * 更新用户
     * 假设用户的id是不可更改的,
     * 所以通过实体内的id确定更新的条目,
     * 然后将条目的数据修改为实体内的其他信息
     * @param user 用户实体
     */
    void update(User user);

    /**
     * 查找用户
     * @param id 通过id查找
     * @return 找到的一个用户
     */
    User select(long id);

    /**
     * 更广泛的查找用户,通过实体内的多个信息中的一个来查找
     * 如用户名,用户id等.
     * 似乎有了这个一个就不需要上面的那个了.
     * @param user 用户信息
     * @return 找到的一个用户
     */
    User selectPro(User user);

    /**
     * 通过用户名和密码登录
     * @param username 用户名称
     * @param password 用户密码
     */
    void login(@Param("username") String username,@Param("password") String password);

    void addComment(@Param("userId") String userId,@Param("bookId") String bookId,@Param("content") int content);

    void likeComment(@Param("userId") String userId,@Param("commentId") String commentId);
    void disLikeComment(@Param("userId") String userId,@Param("commentId") String commentId);


}
