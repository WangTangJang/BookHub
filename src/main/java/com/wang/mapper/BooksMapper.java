package com.wang.mapper;

import com.wang.model.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BooksMapper {
    /**
     * 插入书籍
     * @param books 一个书籍的信息
     */
    void insert(Books books);

    /**
     * 删除书籍通过单个书籍的信息
     * 这个似乎只能删除一个书籍.
     * @param books 单个书籍的信息
     */
    void delete(Books books);

    /**
     * 更新书籍
     * 假设书籍的id时不可更改的.所以只需要传入一个实体,
     * id依旧是原来的.然后根据id来更新之后的信息
     * @param books 更新后的信息
     */
    void update(Books books);

    /**
     * 通过id查找书籍
     * @param id 书籍的id
     * @return 单个书籍
     */
    Books select(long id);

    /**
     * 查找全部书籍
     * @return 全部书籍
     */
    List<Books> selectAll();

    /**
     * 通过关键字搜素书籍
     * @param keyword 关键字
     * @return 符合条件的书籍
     */
    List<Books> search(@Param("keyword") String keyword);

    /**
     * 统计书籍数量
     * @return 书籍的数量
     */
    int count();

    /**
     * 通过分页查找书籍
     * @param start 第几个开始
     * @param size 要查几个
     * @return 范围内的书籍
     */
    List<Books> selectPage(@Param("start") int start,@Param("size") int size);

    List<Books> selectByListId(List<Long> bookIds);

}
