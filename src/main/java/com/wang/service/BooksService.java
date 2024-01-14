package com.wang.service;

import com.wang.model.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BooksService {
    /**
     * 插入书籍
     * @param book 书籍的信息
     */
    String adminUpload(Books book);

    /**
     * 删除书籍
     * @param id 书籍id
     */
    void deleteById(int id);

    /**
     * 更新书籍
     * @param books 书籍的信息
     */
    void update(Books books);

    /**
     * 通过id查找书籍
     * @param id 书籍的id
     * @return 只能返回单个书籍
     */
    Books selectById(long id);

    /**
     * 查找全部书籍
     * @return 全部信息
     */
    List<Books> selectAll();

    /**
     * 通过关键字查找 书籍
     * @param keyword 关键字
     * @return 符合关键字的所有书籍
     */
    List<Books> search(@Param("keyword") String keyword);

    /**
     * 统计书籍数量
     * @return 书籍的数量
     */
    int count();

    /**
     * 分页查找书籍
     * @param page 第几页
     * @param size 每页的数量
     * @return 范围内的书籍
     */
    List<Books> selectPage(@Param("start") int page,@Param("size") int size);

    /**
     * 通过多个书籍id查找
     * @param bookIds 多个id
     * @return 符合的全部书籍
     */
    List<Books> selectByList(List<Long> bookIds);

    /**
     * 更新书籍的平均分
     * @param id 书籍id
     */
    void updateAverageRating(long id);

    /**
     * 更新一本书被添加到了多少的书籍
     * @param bookId 书籍id
     */
    void updateAdded(long bookId);

    /**
     * 更新一本书被多少人评分
     * @param bookId 书籍id
     */
    void updateRatingCount(long bookId);

    /**
     * 用户上传书籍
     * @param book 书籍的信息
     * @param username 上传者的id
     */
    String userUpload(Books book,String username);

    /**
     * 查找待审核的书籍
     * @return 待审核的书籍
     */
    List<Books> searchPendingBooks();

    /**
     * 查找审核通过的书籍
     * @return 审核通过的书籍
     */
    List<Books> searchApprovedBooks();

    /**
     * 查找审核驳回的书籍
     * @return 审核驳回的书籍
     */
    List<Books> searchRejectedBooks();
}
