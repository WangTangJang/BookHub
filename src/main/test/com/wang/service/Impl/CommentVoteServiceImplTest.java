package com.wang.service.Impl;

import com.wang.service.CommentVoteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CommentVoteServiceImplTest {

    @Resource
    CommentVoteService commentVoteService;

    @Test
    public void likeComment() {
        commentVoteService.likeComment(1,1);
    }

    @Test
    public void dislikeComment() {
        commentVoteService.dislikeComment(1,1);
    }

}