package edu.whpu.wyp.THBlog.controller;

import edu.whpu.wyp.THBlog.bean.Tag;
import edu.whpu.wyp.THBlog.bean.TagRespond;
import edu.whpu.wyp.THBlog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/allTag")
    public List<Tag> getAllTag() {
        return tagService.getAllTag();
    }

    @PostMapping("/tag")
    public TagRespond addTag(Tag tag) {
        int i = tagService.addTag(tag);
        TagRespond tagRespond = new TagRespond();
        if (i > 0) {
            tagRespond.setMsg("Added successfully");
            tagRespond.setCode("30000");
        } else {
            tagRespond.setMsg("Failed to add");
            tagRespond.setCode("30001");
        }

        return tagRespond;
    }

    @DeleteMapping("/tag")
    public TagRespond deleteTagLogically(String tagId) {
        int i = tagService.deleteTagLogically(tagId);
        TagRespond tagRespond = new TagRespond();
        if (i > 0) {
            tagRespond.setMsg("Deleted successfully");
            tagRespond.setCode("31000");
        } else {
            tagRespond.setMsg("Failed to delete");
            tagRespond.setCode("31001");
        }

        return tagRespond;
    }

    @PutMapping("/tag")
    public TagRespond updateTagById(Tag tag) {
        int i = tagService.updateTagById(tag);
        TagRespond tagRespond = new TagRespond();
        if (i > 0) {
            tagRespond.setMsg("Updated successfully");
            tagRespond.setCode("32000");
        } else {
            tagRespond.setMsg("Failed to update");
            tagRespond.setCode("32001");
        }

        return tagRespond;
    }

    @GetMapping("/tagLimited")
    public List<Tag> getTagLimited(String currentPage, String size) {
        return tagService.getTagLimited(Integer.parseInt(currentPage), Integer.parseInt(size));
    }

    @GetMapping("/theNumOfTag")
    public int getTheNumOfTag() {
        return tagService.getTheNumOfTag();
    }

    @GetMapping("/tag")
    public Tag getTagById(String tagId) {
        return tagService.getTagById(Integer.parseInt(tagId));
    }

    @GetMapping("/tagDeleted")
    public List<Tag> getAllDeletedTag() {
        return tagService.getAllDeletedTag();
    }

    @GetMapping("/tagDeletedLimited")
    public List<Tag> getDeletedTagLimited(String currentPage, String size) {
        return tagService.getDeletedTagLimited(Integer.parseInt(currentPage), Integer.parseInt(size));
    }

    @GetMapping("/theNumOfDeletedTag")
    public int getTheNumOfDeletedTag() {
        return tagService.getTheNumOfDeletedTag();
    }

    @DeleteMapping("/tagDeleted")
    public TagRespond deleteTagById(String tagId) {
        int i = tagService.deleteTagById(Integer.parseInt(tagId));
        TagRespond tagRespond = new TagRespond();
        if (i > 0) {
            tagRespond.setMsg("Deleted successfully");
            tagRespond.setCode("33000");
        } else {
            tagRespond.setMsg("Failed to delete");
            tagRespond.setCode("33001");
        }

        return tagRespond;
    }

    @DeleteMapping("/allTagDeleted")
    public TagRespond deleteAllDeletedTag() {
        int i = tagService.deleteAllDeletedTag();
        TagRespond tagRespond = new TagRespond();
        if (i > 0) {
            tagRespond.setMsg("emptied successfully");
            tagRespond.setCode("34000");
        } else {
            tagRespond.setMsg("Failed to empty");
            tagRespond.setCode("34001");
        }

        return tagRespond;
    }
}
