package edu.whpu.wyp.THBlog.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class Comment {
    private int commentId;
    private String commentName;
    private String commentContent;
    private Date commentDate;
    private boolean commentIsPass;
    private boolean commentIsDelete;

    public Comment() {
    }

    public Comment(int commentId, String commentName, String commentContent, Date commentDate, boolean commentIsPass, boolean commentIsDelete) {
        this.commentId = commentId;
        this.commentName = commentName;
        this.commentContent = commentContent;
        this.commentDate = commentDate;
        this.commentIsPass = commentIsPass;
        this.commentIsDelete = commentIsDelete;
    }
}
