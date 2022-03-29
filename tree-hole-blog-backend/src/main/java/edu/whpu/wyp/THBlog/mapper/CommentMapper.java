package edu.whpu.wyp.THBlog.mapper;

import edu.whpu.wyp.THBlog.bean.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<Comment> getAllPassedComment();

    List<Comment> getAllComment();

    int addComment(Comment comment);

    int updatePassStatement(int commentId);

    int deleteCommentLogically(int commentId);

    int getTheNumOfComment();

    List<Comment> getAllCommentLimited(int start, int limit);

    List<Comment> getAllPassedCommentLimited(int start, int limit);

    List<Comment> getAllDeletedCommentLimited(int start, int limit);

    int getTheNumOfDeletedComment();

    int deleteCommentById(int commentId);

    int deleteAllDeletedComment();

    List<Comment> getNewestComment();

}
