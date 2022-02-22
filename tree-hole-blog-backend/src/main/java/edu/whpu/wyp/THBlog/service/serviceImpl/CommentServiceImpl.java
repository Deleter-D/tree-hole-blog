package edu.whpu.wyp.THBlog.service.serviceImpl;

import edu.whpu.wyp.THBlog.bean.Comment;
import edu.whpu.wyp.THBlog.mapper.CommentMapper;
import edu.whpu.wyp.THBlog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> getAllPassedComment() {
        return commentMapper.getAllPassedComment();
    }

    @Override
    public List<Comment> getAllComment() {
        return commentMapper.getAllComment();
    }

    @Override
    public int addComment(Comment comment) {
        return commentMapper.addComment(comment);
    }

    @Override
    public int updatePassStatement(int commentId) {
        return commentMapper.updatePassStatement(commentId);
    }

    @Override
    public int deleteCommentLogically(int commentId) {
        return commentMapper.deleteCommentLogically(commentId);
    }

    @Override
    public int getTheNumOfComment() {
        return commentMapper.getTheNumOfComment();
    }

    @Override
    public List<Comment> getAllCommentLimited(int currentPage, int size) {
        int start = (currentPage - 1) * size;
        return commentMapper.getAllCommentLimited(start, size);
    }

    @Override
    public List<Comment> getAllDeletedCommentLimited(int currentPage, int size) {
        int start = (currentPage - 1) * size;
        return commentMapper.getAllDeletedCommentLimited(start, size);
    }

    @Override
    public int getTheNumOfDeletedComment() {
        return commentMapper.getTheNumOfDeletedComment();
    }

    @Override
    public int deleteCommentById(int commentId) {
        return commentMapper.deleteCommentById(commentId);
    }

    @Override
    public int deleteAllDeletedComment() {
        return commentMapper.deleteAllDeletedComment();
    }

    @Override
    public List<Comment> getNewestComment() {
        return commentMapper.getNewestComment();
    }
}
