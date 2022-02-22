package edu.whpu.wyp.THBlog.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Tag {
    private int tagId;
    private String tagName;
    private boolean tagIsDelete;

    public Tag() {
    }

    public Tag(int tagId, String tagName, boolean tagIsDelete) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.tagIsDelete = tagIsDelete;
    }
}
