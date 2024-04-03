package com.wang.service.Impl;

import com.wang.mapper.BookshelfMapper;
import com.wang.model.Books;
import com.wang.model.Bookshelf;
import com.wang.service.BooksService;
import com.wang.service.BookshelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookshelfServiceImpl implements BookshelfService {

    @Autowired
    BookshelfMapper mapper;

    @Autowired
    BooksService booksService;

    @Override
    public void addToBookshelf(long userid, long bookid) {
        Bookshelf bookshelf = new Bookshelf();
        bookshelf.setUserId(userid);
        bookshelf.setBookId(bookid);

        LocalDate localDate = LocalDate.now();

        bookshelf.setStatus("unread");
        bookshelf.setJoinDate(Date.valueOf(localDate));
        mapper.insert(bookshelf);
        booksService.updateAdded(bookshelf.getBookId());

    }
    @Override
    public void delFormBookshelf(long userid, long bookId) {
        Bookshelf bookshelf = new Bookshelf();
        bookshelf.setUserId(userid);
        bookshelf.setBookId(bookId);
        mapper.delete(bookshelf);
        booksService.updateAdded(bookshelf.getBookId());

    }

    @Override
    public List<Bookshelf> lookOver(Bookshelf bookshelf) {

        return  mapper.select(bookshelf);
    }

    @Override
    public List<Books> getBooksByUserId(long userId) {
        Bookshelf bookshelf  = new Bookshelf();
        bookshelf.setUserId(userId);
        List<Bookshelf> bookshelves =  mapper.select(bookshelf);
        if (bookshelves.size()==0){
            return new ArrayList<>();
        }
        List<Long> result = new ArrayList<>();
        for (Bookshelf b:bookshelves){
            result.add(b.getBookId());
        }
        return booksService.selectByList(result);
    }

    @Override
    public boolean isCollected(long userid, long bookid) {
        Bookshelf bookshelf = new Bookshelf();
        bookshelf.setUserId(userid);
        bookshelf.setBookId(bookid);
        return mapper.select(bookshelf).size() > 0;
    }
}
