package com.wang.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.mapper.BooksMapper;
import com.wang.model.Books;
import com.wang.service.BookCategoryService;
import com.wang.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    BooksMapper mapper;
    @Autowired
    BookCategoryService bookCategoryService;


    // 先是向书籍的信息表中插入一条数据，然后再向书籍分类表中插入一条数据
    // 正好可以试试事务，当向另一张表中插入时，如果失败，那么之前的插入也会回滚
    // 经测试，需要在applicationContext.xml中配置事务管理器，然后在方法上加上@Transactional注解
    @Override
    @Transactional
    public String adminUpload(Books book) {
        if(mapper.checkDuplicateISBN(book.getIsbn())){
            return "isbn重复";
        }else{
            book.setUploadedBy("管理员");
            book.setStatus("审核通过");
            mapper.insert(book);
            bookCategoryService.addBookCategory(book.getId(),book.getCategoryId());
            return "ok";
        }
    }
    @Override
    public void deleteById(int id) {
        mapper.deleteById(id);
        // 多余操作，因为在删除书籍时，会删除书籍分类表中的数据
        // 分类映射表中的书籍id是外键，当删除书籍时，会自动删除分类映射表中的数据
        //bookCategoryService.deleteByBookId(id);
    }

    @Override
    public void update(Books books) {
        mapper.update(books);
    }

    @Override
    public Books selectById(long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<Books> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public List<Books> search(String keyword) {
        return mapper.search(keyword);
    }

    @Override
    public int count() {
        return mapper.count();
    }

    @Override
    public List<Books> selectPage(int page, int size) {
        int start = (page-1)*size;
        return mapper.selectPage(start, size);
    }

    @Override
    public List<Books> selectByList(List<Long> bookIds) {

        return mapper.selectByListId(bookIds);
    }

    @Override
    public void updateAverageRating(long id) {
        mapper.updateAverageRating(id);
    }

    @Override
    public void updateAdded(long bookId) {
        mapper.updateAdded(bookId);
    }

    @Override
    public void updateRatingCount(long bookId) {
        mapper.updateRatingCount(bookId);
    }

    @Override
    public void updateReviewsCount(long bookId) {
        mapper.updateReviewsCount(bookId);
    }

    @Override
    public String uploadCover(MultipartFile file) {
        try {
            // 文件存放服务端的位置
            String rootPath = "C:\\Users\\Administrator\\Desktop\\ProxyZerl\\WebAppBuild\\nginx-1.25.4\\html\\img";
            File dir = new File(rootPath + File.separator + "cover");
            if (!dir.exists())
                dir.mkdirs();
            // 写文件到服务器
            File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
            file.transferTo(serverFile);
            // 你就说存没存上去吧。。。
            return "http://localhost:8081/img/cover/" +  file.getOriginalFilename();
        } catch (Exception e) {
            return "You failed to upload " +  file.getOriginalFilename() + " => " + e.getMessage();
        }
    }

    @Override
    public String userUpload(Books book, String username) {
        // 检查isbn是否重复
        if (mapper.checkDuplicateISBN(book.getIsbn())){
            return "isbn 重复";
        }else {
            book.setStatus("尚未审核");
            book.setUploadedBy(username);
            mapper.insert(book);
            return "ok";
        }
    }

    @Override
    public List<Books> searchPendingBooks() {
        return mapper.selectByStatus("尚未审核");
    }

    @Override
    public List<Books> searchApprovedBooks() {
        return mapper.selectByStatus("审核通过");
    }

    @Override
    public List<Books> searchRejectedBooks() {
        return mapper.selectByStatus("审核驳回");
    }

    @Override
    public Page<Books> findAllBooks(Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        List<Books> books = mapper.selectPage(offset, pageable.getPageSize());
        // 获取数据总量
        int total = mapper.count();

        // 创建PageImpl对象
        return new PageImpl<>(books, pageable, total);
    }

}
