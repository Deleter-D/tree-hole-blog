package edu.whpu.wyp.THBlog.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void getUserByUserName() {
        System.out.println(userMapper.getUserByUserName("admin"));
    }

    @Test
    public void getAllUser() {
        System.out.println(userMapper.getAllUser());
    }
}
