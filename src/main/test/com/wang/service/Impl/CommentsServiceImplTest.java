package com.wang.service.Impl;

import com.wang.model.Comments;
import com.wang.service.CommentsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CommentsServiceImplTest {

    @Resource
    CommentsService commentsService;

    @Test
    public void addComments() {
        Comments comments = new Comments();
        comments.setUserId(1);
        comments.setBookId(1);
        comments.setContext("好书");
        commentsService.addComment(comments);
    }

    @Test
    public void updateComment(){
        commentsService.updateContext(1,"不错的书！");
    }

    @Test
    public void deleteComment(){
        commentsService.deleteComment(2);
    }
}