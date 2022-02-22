package edu.whpu.wyp.THBlog.mapper;

import edu.whpu.wyp.THBlog.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getAllUser();

    User getUserByUserName(String userName);
}
