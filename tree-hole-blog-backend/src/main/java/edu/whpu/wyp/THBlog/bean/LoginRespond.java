package edu.whpu.wyp.THBlog.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class LoginRespond {
    private String msg;
    private String code;
    private String token;

    public LoginRespond() {
    }

    public LoginRespond(String msg, String code, String token) {
        this.msg = msg;
        this.code = code;
        this.token = token;
    }
}
