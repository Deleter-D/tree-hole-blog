package edu.whpu.wyp.THBlog.service;

import edu.whpu.wyp.THBlog.bean.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAllPassedComment();

    List<Comment> getAllComment();

    int addComment(Comment comment);

    int updatePassStatement(int commentId);

    int deleteCommentLogically(int commentId);

    int getTheNumOfComment();

    List<Comment> getAllCommentLimited(int currentPage, int size);

    List<Comment> getAllPassedCommentLimited(int currentPage, int size);

    List<Comment> getAllDeletedCommentLimited(int currentPage, int size);

    int getTheNumOfDeletedComment();

    int deleteCommentById(int commentId);

    int deleteAllDeletedComment();

    List<Comment> getNewestComment();
}
