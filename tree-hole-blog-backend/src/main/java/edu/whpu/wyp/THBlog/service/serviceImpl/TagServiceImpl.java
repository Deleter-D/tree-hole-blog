package edu.whpu.wyp.THBlog.service.serviceImpl;

import edu.whpu.wyp.THBlog.bean.Tag;
import edu.whpu.wyp.THBlog.mapper.TagMapper;
import edu.whpu.wyp.THBlog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;


    @Override
    public List<Tag> getAllTag() {
        return tagMapper.getAllTag();
    }

    @Override
    public int addTag(Tag tag) {
        return tagMapper.addTag(tag);
    }

    @Override
    public int deleteTagLogically(String tagId) {
        return tagMapper.deleteTagLogically(Integer.parseInt(tagId));
    }

    @Override
    public int updateTagById(Tag tag) {
        return tagMapper.updateTagById(tag);
    }

    @Override
    public List<Tag> getTagLimited(int currentPage, int size) {
        int start = (currentPage - 1) * size;
        return tagMapper.getTagLimited(start, size);
    }

    @Override
    public int getTheNumOfTag() {
        return tagMapper.getTheNumOfTag();
    }

    @Override
    public Tag getTagById(int tagId) {
        return tagMapper.getTagById(tagId);
    }

    @Override
    public List<Tag> getAllDeletedTag() {
        return tagMapper.getAllDeletedTag();
    }

    @Override
    public List<Tag> getDeletedTagLimited(int currentPage, int size) {
        int start = (currentPage - 1) * size;
        return tagMapper.getDeletedTagLimited(start, size);
    }

    @Override
    public int getTheNumOfDeletedTag() {
        return tagMapper.getTheNumOfDeletedTag();
    }

    @Override
    public int deleteTagById(int tagId) {
        return tagMapper.deleteTagById(tagId);
    }

    @Override
    public int deleteAllDeletedTag() {
        return tagMapper.deleteAllDeletedTag();
    }
}
