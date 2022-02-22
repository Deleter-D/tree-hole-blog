package edu.whpu.wyp.THBlog.mapper;

import edu.whpu.wyp.THBlog.bean.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TagTest {

    @Autowired
    private TagMapper tagMapper;

    @Test
    public void getAllTagTest() {
        for (Tag tag : tagMapper.getAllTag()) {
            System.out.println(tag);
        }
    }
}
