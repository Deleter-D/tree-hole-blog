package edu.whpu.wyp.THBlog.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
@Data
public class FileRespond {
    private String msg;
    private String code;
    private String URL;

    public FileRespond() {
    }

    public FileRespond(String msg, String code, String URL) {
        this.msg = msg;
        this.code = code;
        this.URL = URL;
    }
}
