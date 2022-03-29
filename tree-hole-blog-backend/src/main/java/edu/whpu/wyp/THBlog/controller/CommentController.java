package edu.whpu.wyp.THBlog.controller;

import edu.whpu.wyp.THBlog.bean.Comment;
import edu.whpu.wyp.THBlog.bean.CommentRespond;
import edu.whpu.wyp.THBlog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/allPassedComment")
    public List<Comment> getAllPassedComment() {
        return commentService.getAllPassedComment();
    }

    @GetMapping("/allComment")
    public List<Comment> getAllComment() {
        return commentService.getAllComment();
    }

    @PostMapping("/comment")
    public CommentRespond addComment(Comment comment) {
        int i = commentService.addComment(comment);
        CommentRespond commentRespond = new CommentRespond();
        if (i > 0) {
            commentRespond.setMsg("Added successfully");
            commentRespond.setCode("40000");
        } else {
            commentRespond.setMsg("Failed to add");
            commentRespond.setCode("40001");
        }

        return commentRespond;
    }

    @DeleteMapping("/comment")
    public CommentRespond deleteCommentLogically(String commentId) {
        int i = commentService.deleteCommentLogically(Integer.parseInt(commentId));
        CommentRespond commentRespond = new CommentRespond();
        if (i > 0) {
            commentRespond.setMsg("Deleted successfully");
            commentRespond.setCode("41000");
        } else {
            commentRespond.setMsg("Failed to delete");
            commentRespond.setCode("41001");
        }

        return commentRespond;
    }

    @PatchMapping("/comment")
    public CommentRespond updatePassStatement(String commentId) {
        int i = commentService.updatePassStatement(Integer.parseInt(commentId));
        CommentRespond commentRespond = new CommentRespond();
        if (i > 0) {
            commentRespond.setMsg("Passed successfully");
            commentRespond.setCode("43000");
        } else {
            commentRespond.setMsg("Failed to pass");
            commentRespond.setCode("43001");
        }

        return commentRespond;
    }

    @GetMapping("/theNumOfComment")
    public int getTheNumOfComment() {
        return commentService.getTheNumOfComment();
    }

    @GetMapping("/commentLimited")
    public List<Comment> getAllCommentLimited(String currentPage, String size) {
        return commentService.getAllCommentLimited(Integer.parseInt(currentPage), Integer.parseInt(size));
    }

    @GetMapping("/commentPassedLimited")
    public List<Comment> getAllPassedCommentLimited(String currentPage, String size) {
        return commentService.getAllPassedCommentLimited(Integer.parseInt(currentPage), Integer.parseInt(size));
    }

    @GetMapping("/commentDeletedLimited")
    public List<Comment> getAllDeletedCommentLimited(String currentPage, String size) {
        return commentService.getAllDeletedCommentLimited(Integer.parseInt(currentPage), Integer.parseInt(size));
    }

    @GetMapping("/theNumOfDeletedComment")
    public int getTheNumOfDeletedComment() {
        return commentService.getTheNumOfDeletedComment();
    }

    @DeleteMapping("/commentDeleted")
    public CommentRespond deleteCommentById(String commentId) {
        int i = commentService.deleteCommentById(Integer.parseInt(commentId));
        CommentRespond commentRespond = new CommentRespond();
        if (i > 0) {
            commentRespond.setMsg("Deleted successfully");
            commentRespond.setCode("44000");
        } else {
            commentRespond.setMsg("Failed to delete");
            commentRespond.setCode("44001");
        }

        return commentRespond;
    }

    @DeleteMapping("/allCommentDeleted")
    public CommentRespond deleteAllDeletedComment() {
        int i = commentService.deleteAllDeletedComment();
        CommentRespond commentRespond = new CommentRespond();
        if (i > 0) {
            commentRespond.setMsg("Emptied successfully");
            commentRespond.setCode("45000");
        } else {
            commentRespond.setMsg("Failed to empty");
            commentRespond.setCode("45001");
        }

        return commentRespond;
    }

    @GetMapping("/newestComment")
    public List<Comment> getNewestComment() {
        return commentService.getNewestComment();
    }
}
