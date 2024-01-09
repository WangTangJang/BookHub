package com.wang.service.Impl;

import com.wang.mapper.BooksMapper;
import com.wang.model.Books;
import com.wang.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    BooksMapper mapper;

    @Override
    public String adminUpload(Books book) {
        if(mapper.checkDuplicateISBN(book.getIsbn())>0){
            return "isbn重复";
        }else{
            book.setUploadedBy("管理员");
            book.setStatus("审核通过");
            mapper.insert(book);
            return "ok";
        }

    }

    @Override
    public void deleteById(int id) {

        mapper.deleteById(id);
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
    public String userUpload(Books books, String username) {
        if (mapper.checkDuplicateISBN(books.getIsbn())>0){
            return "isbn 重复";
        }else {
            books.setStatus("未审核");
            books.setUploadedBy(username);
            mapper.insert(books);
            return "ok";
        }
    }

    @Override
    public List<Books> searchPendingBooks() {
        return mapper.selectByStatus("未审核");
    }

    @Override
    public List<Books> searchApprovedBooks() {
        return mapper.selectByStatus("审核通过");
    }

    @Override
    public List<Books> searchRejectedBooks() {
        return mapper.selectByStatus("审核驳回");
    }


}
