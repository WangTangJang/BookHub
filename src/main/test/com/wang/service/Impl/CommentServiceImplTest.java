package com.wang.service.Impl;

import com.wang.model.Comment;
import com.wang.model.result.CommentResult;
import com.wang.service.CommentsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CommentServiceImplTest {

    @Resource
    CommentsService commentsService;

    @Test
    public void getCommentForBook() {
        List<CommentResult> comments = commentsService.getCommentByBookId(15);
        //printCommentsTree(comments, 0);
    }

    // 辅助方法：生成缩进字符串
    private String getIndentation(int depth) {
        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indentation.append("  "); // 每一层级两个空格缩进
        }
        return indentation.toString();
    }

    public void printCommentsTree(List<Comment> comments, int depth) {
        for (Comment comment : comments) {
            // 输出当前评论及其深度
            System.out.println(getIndentation(depth) + comment.getUserId() + "评论了" + comment.getBookId() + "书籍：" + comment.getContext());

            // 递归处理回复评论
            if (comment.getReplies() != null) {
                printCommentsTree(comment.getReplies(), depth + 1);
            }
        }
    }

    @Test
    public void addComments() {
        Comment comment = new Comment();
        comment.setUserId(1);
        comment.setBookId(1);
        comment.setContext("好书");
        commentsService.addComment(comment);
    }

    @Test
    public void Reply(){

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
