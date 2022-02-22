package edu.whpu.wyp.THBlog.service;

import edu.whpu.wyp.THBlog.bean.Article;

import java.util.HashMap;
import java.util.List;

public interface ArticleService {

    List<Article> getAllArticle();

    HashMap<String, Object> getArticleAndTagNameById(int articleId);

    Article getArticleById(int articleId);

    int addArticle(Article article);

    List<Article> getArticleLimited(int currentPage, int size);

    int getTheNumOfArticle();

    int deleteArticleById(String articleId);

    int updateArticleById(Article article);

    int updateHotStatement(String articleId);

    int deleteArticleLogically(int articleId);

    List<Article> getAllDeletedArticle();

    List<Article> getDeletedArticleLimited(int currentPage, int size);

    int getTheNumOfDeletedArticle();

    int deleteAllDeletedArticle();

    List<Article> getAllRecommendArticle();

    List<HashMap<String, Object>> getArticleAndTagName(int currentPage, int size);

    int getTheNumOfHotArticle();

    List<HashMap<String, Object>> getTheArticleAfterClassification(int tagId, int currentPage, int size);

    int getTheNumOfArticleAfterClassification(int tagId);

    int getMaxId();

    int updateCover(int articleId, String articleCover);
}
