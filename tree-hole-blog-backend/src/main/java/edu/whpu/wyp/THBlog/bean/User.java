package edu.whpu.wyp.THBlog.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {
    private String  userId;
    private String userName;
    private String password;

    public User() {
    }

    public User(String  userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }
}
