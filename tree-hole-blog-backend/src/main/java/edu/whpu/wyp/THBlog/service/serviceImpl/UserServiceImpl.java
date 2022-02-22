package edu.whpu.wyp.THBlog.service.serviceImpl;

import edu.whpu.wyp.THBlog.bean.User;
import edu.whpu.wyp.THBlog.mapper.UserMapper;
import edu.whpu.wyp.THBlog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String Login(String userName, String password) {
        User user = userMapper.getUserByUserName(userName);
        if (user == null) {
            return "-1";
        } else {
            if (user.getPassword().equals(password)) {
                return user.getUserId();
            } else {
                return "-2";
            }
        }
    }
}
