package edu.whpu.wyp.THBlog.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CommentRespond {
    private String msg;
    private String code;

    public CommentRespond() {
    }

    public CommentRespond(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }
}
