package edu.whpu.wyp.THBlog.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import edu.whpu.wyp.THBlog.bean.LoginRespond;
import edu.whpu.wyp.THBlog.service.UserService;
import edu.whpu.wyp.THBlog.utils.TokenUtil;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtil tokenUtil;

    @PostMapping("/login")
    public LoginRespond Login(String userName, String password) {

        LoginRespond loginRespond = new LoginRespond();

        String usreId = userService.Login(userName, password);
        if (usreId.equals("-1")) {
            loginRespond.setMsg("User not exits");
            loginRespond.setCode("-1");
        } else if (usreId.equals("-2")) {
            loginRespond.setMsg("Password not correct");
            loginRespond.setCode("-2");
        } else {
            loginRespond.setMsg("Login success");
            loginRespond.setCode("10000");
            loginRespond.setToken(tokenUtil.createToken(usreId));
        }

        return loginRespond;
    }

}
