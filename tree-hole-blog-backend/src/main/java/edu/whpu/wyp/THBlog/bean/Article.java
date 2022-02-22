package edu.whpu.wyp.THBlog.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class Article {
    private int articleId;
    private String articleTitle;
    private String articleContent;
    private Date articleDate;
    private String articleAuthor;
    private boolean articleIsHot;
    private boolean articleIsDelete;
    private Integer articleTag1;
    private Integer articleTag2;
    private Integer articleTag3;
    private String articleCover;

    public Article() {
    }

    public Article(int articleId, String articleTitle, String articleContent, Date articleDate, String articleAuthor, boolean articleIsHot, boolean articleIsDelete, Integer articleTag1, Integer articleTag2, Integer articleTag3, String articleCover) {
        this.articleId = articleId;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleDate = articleDate;
        this.articleAuthor = articleAuthor;
        this.articleIsHot = articleIsHot;
        this.articleIsDelete = articleIsDelete;
        this.articleTag1 = articleTag1;
        this.articleTag2 = articleTag2;
        this.articleTag3 = articleTag3;
        this.articleCover = articleCover;
    }
}
