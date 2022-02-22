package edu.whpu.wyp.THBlog.service;

import edu.whpu.wyp.THBlog.bean.Tag;
import edu.whpu.wyp.THBlog.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TagService {
    List<Tag> getAllTag();

    int addTag(Tag tag);

    int deleteTagLogically(String tagId);

    int updateTagById(Tag tag);

    List<Tag> getTagLimited(int currentPage, int size);

    int getTheNumOfTag();

    Tag getTagById(int tagId);

    List<Tag> getAllDeletedTag();

    List<Tag> getDeletedTagLimited(int currentPage, int size);

    int getTheNumOfDeletedTag();

    int deleteTagById(int tagId);

    int deleteAllDeletedTag();
}
