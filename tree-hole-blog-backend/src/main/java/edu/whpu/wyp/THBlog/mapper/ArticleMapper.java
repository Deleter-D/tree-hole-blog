package edu.whpu.wyp.THBlog.mapper;

import edu.whpu.wyp.THBlog.bean.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ArticleMapper {
    List<Article> getAllArticle();

    HashMap<String, Object> getArticleAndTagNameById(int articleId);

    Article getArticleById(int articleId);

    int addArticle(Article article);

    List<Article> getArticleLimited(int start, int limit);

    int getTheNumOfArticle();

    int deleteArticleById(int articleId);

    int updateArticleById(Article article);

    int updateHotStatement(int articleId);

    int deleteArticleLogically(int articleId);

    List<Article> getAllDeletedArticle();

    List<Article> getDeletedArticleLimited(int start, int limit);

    int getTheNumOfDeletedArticle();

    int deleteAllDeletedArticle();

    List<Article> getAllRecommendArticle();

    List<HashMap<String, Object>> getArticleAndTagName(int start, int limit);

    int getTheNumOfHotArticle();

    List<HashMap<String, Object>> getTheArticleAfterClassification(int tagId, int start, int limit);

    int getTheNumOfArticleAfterClassification(int tagId);

    int getMaxId();

    int updateCover(int articleId, String articleCover);

}
