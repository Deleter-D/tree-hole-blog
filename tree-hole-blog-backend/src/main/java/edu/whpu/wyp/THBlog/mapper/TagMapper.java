package edu.whpu.wyp.THBlog.mapper;

import edu.whpu.wyp.THBlog.bean.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagMapper {
    List<Tag> getAllTag();

    int addTag(Tag tag);

    int deleteTagLogically(int tagId);

    int updateTagById(Tag tag);

    List<Tag> getTagLimited(int start, int limit);

    int getTheNumOfTag();

    Tag getTagById(int tagId);

    List<Tag> getAllDeletedTag();

    List<Tag> getDeletedTagLimited(int start, int limit);

    int getTheNumOfDeletedTag();

    int deleteTagById(int tagId);

    int deleteAllDeletedTag();
}
