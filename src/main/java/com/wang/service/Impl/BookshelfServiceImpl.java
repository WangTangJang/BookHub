package com.wang.service.Impl;

import com.wang.mapper.BookshelfMapper;
import com.wang.model.Bookshelf;
import com.wang.service.BookshelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class BookshelfServiceImpl implements BookshelfService {

    @Autowired
    BookshelfMapper mapper;


    @Override
    public void addToBookshelf(Bookshelf bookshelf) {


        LocalDate localDate = LocalDate.now();

        bookshelf.setStatus("unread");
        bookshelf.setJoinDate(Date.valueOf(localDate));


        mapper.insert(bookshelf);

    }

    @Override
    public void delFormBookshelf(Bookshelf bookshelf) {
        mapper.delete(bookshelf);
    }

    @Override
    public List<Bookshelf> lookOver(Bookshelf bookshelf) {

        return  mapper.select(bookshelf);
    }
}
