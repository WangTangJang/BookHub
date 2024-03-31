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

import java.awt.print.Book;
import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    BooksMapper mapper;
    @Autowired
    BookCategoryService bookCategoryService;
    @Autowired
    private Properties fileStorageConfig;

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
    public String userUpload(Books book, String username) {
        // 检查isbn是否重复
        if (mapper.checkDuplicateISBN(book.getIsbn())){
            return "isbn 重复";
        }else {
            book.setUploadedBy(username);
            mapper.userUpload(book);
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

    @Override
    public String uploadCover(MultipartFile file) {
        try {
            // 文件存放服务端的位置
            String rootPath = fileStorageConfig.getProperty("upload.root-path");
            String coverPath = fileStorageConfig.getProperty("coverPath");
            String serverPath = fileStorageConfig.getProperty("serverPath");
            File dir = new File(rootPath + File.separator + coverPath);
            if (!dir.exists())
                dir.mkdirs();
            // 写文件到服务器
            File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
            file.transferTo(serverFile);
            // 你就说存没存上去吧。。。
            return serverPath +File.separator+ coverPath +File.separator+  file.getOriginalFilename();
        } catch (Exception e) {
            return "You failed to upload " +  file.getOriginalFilename() + " => " + e.getMessage();
        }
    }

    @Override
    public String uploadFile(MultipartFile bookFile) {
        try {
            String rootPath = fileStorageConfig.getProperty("upload.root-path");
            String bookFilePath = fileStorageConfig.getProperty("bookFilePath");
            String serverPath = fileStorageConfig.getProperty("serverPath");
            File dir = new File(rootPath+File.separator+bookFilePath);
            if (!dir.exists())
                dir.mkdir();
            File serverFile = new File(dir.getAbsolutePath()+File.separator+bookFile.getOriginalFilename());
            bookFile.transferTo(serverFile);
            return serverPath+File.separator+bookFilePath+File.separator+bookFile.getOriginalFilename();
        } catch (Exception e){
            return "You failed to upload " +  bookFile.getOriginalFilename() + " => " + e.getMessage();
        }
    }

    @Override
    public int unauditedCount() {
        return getUnaudited().size();
    }

    @Override
    public List<Books> getUnaudited() {
        return mapper.getUnaudited();
    }

    @Override
    public Page<Books> getUnauditedByPage(Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        List<Books> books = mapper.selectUnauditedPage(offset, pageable.getPageSize());
        // 获取数据总量
        int total = unauditedCount();

        // 创建PageImpl对象
        return new PageImpl<>(books, pageable, total);
    }
    @Override
    public Books selectUnaudited(long bookId){
        return mapper.selectUnaudited(bookId);
    }

    @Override
    @Transactional
    public void pass(Long bookId) {
        Books books = mapper.selectUnaudited(bookId);
        // 将已经审核通过的书籍从原来的表中删除
        mapper.deleteUnaudited(Math.toIntExact(bookId));
        // 将已经审核通过的书籍插入到正式的表中
        mapper.insert(books);
    }

    @Override
    public void reject(Long bookId) {

    }
}
