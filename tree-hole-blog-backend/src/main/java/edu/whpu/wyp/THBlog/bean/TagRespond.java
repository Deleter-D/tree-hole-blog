package edu.whpu.wyp.THBlog.bean;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class TagRespond {
    private String msg;
    private String code;

    public TagRespond() {
    }

    public TagRespond(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }
}
