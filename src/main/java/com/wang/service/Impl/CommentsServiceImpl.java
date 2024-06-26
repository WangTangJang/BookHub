package com.wang.service.Impl;

import com.wang.mapper.CommentsMapper;
import com.wang.model.Books;
import com.wang.model.Comment;
import com.wang.model.User;
import com.wang.model.result.CommentResult;
import com.wang.service.BooksService;
import com.wang.service.CommentsService;
import com.wang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsMapper mapper;

    @Autowired
    private CommentsService service;

    @Autowired
    private UserService userService;

    @Autowired
    private BooksService booksService;

    @Override
    public void addComment(int bookId,int userId,String commentContent) {
        validate(commentContent);
        Comment comment = new Comment();
        comment.setContext(commentContent);
        comment.setBookId(bookId);
        comment.setUserId(userId);
        // 在业务逻辑中设置初始值
        comment.setLikes(0);
        comment.setDislikes(0);

        mapper.insertSelective(comment);
        booksService.updateReviewsCount(bookId);

    }

    @Override
    public void replyComment(int parentId, int userId,int bookId,String commentContent) {
        validate(commentContent);
        Comment replyComment = new Comment();
        replyComment.setUserId(userId);
        replyComment.setContext(commentContent);
        replyComment.setParentCommentId(parentId);
        replyComment.setBookId(bookId);
        replyComment.setDislikes(0);
        replyComment.setLikes(0);
        mapper.insertSelective(replyComment);
        booksService.updateReviewsCount(bookId);

    }
    @Override
    public Comment getCommentById(long id) {
        return mapper.selectByPrimaryKey((int) id);
    }

    @Override
    public void updateContext(Integer commentId, String newContext) {

        validate(newContext);
        Comment comment = service.getCommentById(commentId);;
        comment.setContext(newContext);
        mapper.updateByPrimaryKeySelective(comment);
    }

    @Override
    public void deleteComment(Integer commentId) {
        mapper.deleteByPrimaryKey(commentId);
    }

    @Override
    public void updateVote(Comment comment) {
        mapper.updateByPrimaryKey(comment);
    }

    @Override
    public List<CommentResult> getCommentByBookId(int bookId) {
        Map<Integer,CommentResult> rootComments = organizeCommentsIntoTree(mapper.selectByBookId(bookId));
        // 将根评论按时间排序
        List<CommentResult> sortedRootComments = new ArrayList<>(rootComments.values());
        sortedRootComments.sort(Comparator.comparing(CommentResult::getCreationTime).reversed());

        return sortedRootComments;
    }

    @Override
    public List<CommentResult> getCommentByLike(int bookId) {
        Map<Integer,CommentResult> rootComments = organizeCommentsIntoTree(mapper.selectByBookId(bookId));
        // 将根评论按时间排序
        List<CommentResult> sortedRootComments = new ArrayList<>(rootComments.values());
        sortedRootComments.sort(Comparator.comparing(CommentResult::getLikes).reversed());

        return sortedRootComments;
    }

    @Override
    public int count() {
        List<Comment> comments =mapper.selectAll();
        return comments.size();
    }

    @Override
    public int countYesterday() {
        List<Comment> comments = mapper.selectAll();
        Date now = new Date(System.currentTimeMillis());
        // 昨天的时间
        Date yesterday = new Date(now.getTime() - 24 * 60 * 60 * 1000);
        List<Comment> result = new ArrayList<>();

        for (Comment comment : comments) {
            if (comment.getCreationTime().after(yesterday)) {
                result.add(comment);
            }
        }
        return result.size();
    }

    @Override
    public Page<Comment> findAllComment(Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        List<Comment> comments = mapper.selectPage(offset, pageable.getPageSize());
        // 获取数据总量
        int total = mapper.count();

        // 创建PageImpl对象
        return new PageImpl<>(comments, pageable, total);
    }

    @Override
    public void update(Comment comment) {
        mapper.updateByPrimaryKeySelective(comment);
    }

    /* 这才是该写的东西！！！ */
    // 评论的树形结构
    private Map<Integer,CommentResult> organizeCommentsIntoTree(List<Comment> comments) {
        // 评论id和评论的映射
        Map<Integer, CommentResult> commentResultMap = new HashMap<>();
        for (Comment comment : comments) {
            User user = userService.selectById(comment.getUserId());
            CommentResult commentResult = new CommentResult(comment);
            commentResult.setUserName(user.getUsername());
            commentResult.setUserAvatar(user.getProfilePicture());
            commentResult.setUserLevel(user.getLevel());
            commentResultMap.put(comment.getId(), commentResult);
        }

        // 遍历评论，判断哪些是根评论，哪些是子评论
        Map<Integer,CommentResult> rootCommentResults = new HashMap<>();
        List<CommentResult> childCommentResults = new ArrayList<>();
        for (CommentResult commentResult : commentResultMap.values()) {
            if (commentResult.getParentCommentId() == null) {
                rootCommentResults.put(commentResult.getId(),commentResult);
            } else {
                childCommentResults.add(commentResult);
            }
        }
        // 将子评论放到对应的根评论下
        for (CommentResult childCommentResult : childCommentResults) {
            String parentUsername = getParentUsername(childCommentResult);
            childCommentResult.setParentUsername(parentUsername);
            Integer rootId = getRootComment(childCommentResult);
            if (rootCommentResults.get(rootId).getReplies() == null){
                rootCommentResults.get(rootId).setReplies(new ArrayList<>());
            }
            rootCommentResults.get(rootId).getReplies().add(childCommentResult);
        }
        return rootCommentResults;
    }

    // 获取一个评论的父评论主人
    private String getParentUsername(CommentResult commentResult){
        Comment comment = service.getCommentById(commentResult.getParentCommentId());
        User user = userService.selectById(comment.getUserId());
        return user.getUsername();
    }

    // 获取一个评论的根评论
    private Integer getRootComment(CommentResult commentResult) {
        while(commentResult.getParentCommentId() != null) {
            Comment comment = mapper.selectByPrimaryKey(commentResult.getParentCommentId());
            commentResult = new CommentResult(comment);
        }
        return commentResult.getId();
    }


    private void validate(String context) {
        if (context == null || context.trim().isEmpty()) {
            throw new IllegalArgumentException("评论内容不能为空");
        }
    }
}
