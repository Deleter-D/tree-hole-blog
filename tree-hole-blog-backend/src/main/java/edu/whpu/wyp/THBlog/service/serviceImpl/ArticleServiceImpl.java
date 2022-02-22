package edu.whpu.wyp.THBlog.service.serviceImpl;

import edu.whpu.wyp.THBlog.bean.Article;
import edu.whpu.wyp.THBlog.mapper.ArticleMapper;
import edu.whpu.wyp.THBlog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Article> getAllArticle() {
        return articleMapper.getAllArticle();
    }

    @Override
    public HashMap<String, Object> getArticleAndTagNameById(int articleId) {
        return articleMapper.getArticleAndTagNameById(articleId);
    }

    @Override
    public Article getArticleById(int articleId) {
        return articleMapper.getArticleById(articleId);
    }

    @Override
    public int addArticle(Article article) {
        return articleMapper.addArticle(article);
    }

    @Override
    public List<Article> getArticleLimited(int currentPage, int size) {
        int start = (currentPage - 1) * size;
        return articleMapper.getArticleLimited(start, size);
    }

    @Override
    public int getTheNumOfArticle() {
        return articleMapper.getTheNumOfArticle();
    }

    @Override
    public int deleteArticleById(String articleId) {
        return articleMapper.deleteArticleById(Integer.parseInt(articleId));
    }

    @Override
    public int updateArticleById(Article article) {
        return articleMapper.updateArticleById(article);
    }

    @Override
    public int updateHotStatement(String articleId) {
        return articleMapper.updateHotStatement(Integer.parseInt(articleId));
    }

    @Override
    public int deleteArticleLogically(int articleId) {
        return articleMapper.deleteArticleLogically(articleId);
    }

    @Override
    public List<Article> getAllDeletedArticle() {
        return articleMapper.getAllDeletedArticle();
    }

    @Override
    public List<Article> getDeletedArticleLimited(int currentPage, int size) {
        int start = (currentPage - 1) * size;
        return articleMapper.getDeletedArticleLimited(start, size);
    }

    @Override
    public int getTheNumOfDeletedArticle() {
        return articleMapper.getTheNumOfDeletedArticle();
    }

    @Override
    public int deleteAllDeletedArticle() {
        return articleMapper.deleteAllDeletedArticle();
    }

    @Override
    public List<Article> getAllRecommendArticle() {
        return articleMapper.getAllRecommendArticle();
    }

    @Override
    public List<HashMap<String, Object>> getArticleAndTagName(int currentPage, int size) {
        int start = (currentPage - 1) * size;
        return articleMapper.getArticleAndTagName(start, size);
    }

    @Override
    public int getTheNumOfHotArticle() {
        return articleMapper.getTheNumOfHotArticle();
    }

    @Override
    public List<HashMap<String, Object>> getTheArticleAfterClassification(int tagId, int currentPage, int size) {
        int start = (currentPage - 1) * size;
        return articleMapper.getTheArticleAfterClassification(tagId, start, size);
    }

    @Override
    public int getTheNumOfArticleAfterClassification(int tagId) {
        return articleMapper.getTheNumOfArticleAfterClassification(tagId);
    }

    @Override
    public int getMaxId() {
        return articleMapper.getMaxId();
    }

    @Override
    public int updateCover(int articleId, String articleCover) {
        return articleMapper.updateCover(articleId, articleCover);
    }
}
