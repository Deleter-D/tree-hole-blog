package edu.whpu.wyp.THBlog.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ArticleRespond {
    private String msg;
    private String code;

    public ArticleRespond() {
    }

    public ArticleRespond(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }
}
