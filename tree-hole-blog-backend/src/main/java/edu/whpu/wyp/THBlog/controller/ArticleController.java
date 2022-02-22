package edu.whpu.wyp.THBlog.controller;

import edu.whpu.wyp.THBlog.bean.ArticleRespond;
import edu.whpu.wyp.THBlog.bean.Article;
import edu.whpu.wyp.THBlog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/allArticle")
    public List<Article> getAllArticle() {
        return articleService.getAllArticle();
    }

    @GetMapping("/article")
    public HashMap<String, Object> getArticleById(String articleId) {
        return articleService.getArticleAndTagNameById(Integer.parseInt(articleId));
    }

    @GetMapping("/articleById")
    public Article getArticleByIdWithoutMap(String articleId) {
        return articleService.getArticleById(Integer.parseInt(articleId));
    }

    @PostMapping("/article")
    public ArticleRespond addArticle(Article article) {
        int i = articleService.addArticle(article);
        ArticleRespond articleRespond = new ArticleRespond();
        if (i > 0) {
            articleRespond.setMsg("Added successfully");
            articleRespond.setCode("20000");
        } else {
            articleRespond.setCode("Failed to add");
            articleRespond.setCode("20001");
        }

        return articleRespond;
    }

    @GetMapping("/articleLimited")
    public List<Article> getArticleLimited(String currentPage, String size) {
        return articleService.getArticleLimited(Integer.parseInt(currentPage), Integer.parseInt(size));
    }

    @GetMapping("/theNumOfArticle")
    public int getTheNumOfArticle() {
        return articleService.getTheNumOfArticle();
    }


    @PutMapping("/article")
    public ArticleRespond updateArticleById(Article article) {
        System.out.println(article);
        int i = articleService.updateArticleById(article);
        ArticleRespond articleRespond = new ArticleRespond();
        if (i > 0) {
            articleRespond.setMsg("Updated successfully");
            articleRespond.setCode("22000");
        } else {
            articleRespond.setCode("Failed to update");
            articleRespond.setCode("22001");
        }

        return articleRespond;
    }

    @PatchMapping("/article")
    public ArticleRespond updateHotStatement(String articleId) {
        int i = articleService.updateHotStatement(articleId);
        ArticleRespond articleRespond = new ArticleRespond();
        if (i > 0) {
            articleRespond.setMsg("Changed successfully");
            articleRespond.setCode("23000");
        } else {
            articleRespond.setCode("Failed to change");
            articleRespond.setCode("23001");
        }

        return articleRespond;
    }

    @DeleteMapping("/article")
    public ArticleRespond deleteArticleLogically(String articleId) {
        int i = articleService.deleteArticleLogically(Integer.parseInt(articleId));
        ArticleRespond articleRespond = new ArticleRespond();
        if (i > 0) {
            articleRespond.setMsg("Deleted successfully");
            articleRespond.setCode("21000");
        } else {
            articleRespond.setCode("Failed to delete");
            articleRespond.setCode("21001");
        }

        return articleRespond;
    }

    @DeleteMapping("/articleDeleted")
    public ArticleRespond deleteArticleById(String articleId) {
        int i = articleService.deleteArticleById(articleId);
        ArticleRespond articleRespond = new ArticleRespond();
        if (i > 0) {
            articleRespond.setMsg("Deleted successfully");
            articleRespond.setCode("24000");
        } else {
            articleRespond.setCode("Failed to delete");
            articleRespond.setCode("24001");
        }

        return articleRespond;
    }

    @GetMapping("/allArticleDeleted")
    public List<Article> getAllDeletedArticle() {
        return articleService.getAllDeletedArticle();
    }

    @GetMapping("/articleDeletedLimited")
    public List<Article> getDeletedArticleLimited(String currentPage, String size) {
        return articleService.getDeletedArticleLimited(Integer.parseInt(currentPage), Integer.parseInt(size));
    }

    @GetMapping("/theNumOfDeletedArticle")
    public int getTheNumOfDeletedArticle() {
        return articleService.getTheNumOfDeletedArticle();
    }

    @DeleteMapping("/allArticleDeleted")
    public ArticleRespond deleteAllDeletedArticle() {
        int i = articleService.deleteAllDeletedArticle();
        ArticleRespond articleRespond = new ArticleRespond();
        if (i > 0) {
            articleRespond.setMsg("Emptied successfully");
            articleRespond.setCode("25000");
        } else {
            articleRespond.setCode("Failed to empty");
            articleRespond.setCode("25001");
        }

        return articleRespond;
    }

    @GetMapping("/allRecommendArticle")
    public List<Article> getAllRecommendArticle() {
        return articleService.getAllRecommendArticle();
    }

    @GetMapping("/articleAndTagName")
    public List<HashMap<String, Object>> getArticleAndTagName(String currentPage, String size) {
        return articleService.getArticleAndTagName(Integer.parseInt(currentPage), Integer.parseInt(size));
    }

    @GetMapping("/theNumOfHotArticle")
    public int getTheNumOfHotArticle() {
        return articleService.getTheNumOfHotArticle();
    }

    @GetMapping("/theArticleAfterClassification")
    public List<HashMap<String, Object>> getTheArticleAfterClassification(String tagId, String currentPage, String size) {
        return articleService.getTheArticleAfterClassification(Integer.parseInt(tagId), Integer.parseInt(currentPage), Integer.parseInt(size));
    }

    @GetMapping("/theNumOfArticleAfterClassification")
    public int getTheNumOfArticleAfterClassification(String tagId) {
        return articleService.getTheNumOfArticleAfterClassification(Integer.parseInt(tagId));
    }

    @PostMapping("/articleCover")
    public ArticleRespond updateCover(String articleId, String articleCover) {
        System.out.println(articleId);
        System.out.println(articleCover);
        int i = articleService.updateCover(Integer.parseInt(articleId), articleCover);
        ArticleRespond articleRespond = new ArticleRespond();
        if (i > 0) {
            articleRespond.setMsg("Emptied successfully");
            articleRespond.setCode("25000");
        } else {
            articleRespond.setCode("Failed to empty");
            articleRespond.setCode("25001");
        }

        return articleRespond;
    }
}
