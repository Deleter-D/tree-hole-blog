package edu.whpu.wyp.THBlog.mapper;

import edu.whpu.wyp.THBlog.bean.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleTest {

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void getAllArticleTest() {
        System.out.println(articleMapper.getAllArticle());
    }

    @Test
    public void getArticleById() {
        System.out.println(articleMapper.getArticleById(1));
    }

    @Test
    public void addArticleTest() {
        Article article = new Article();
        article.setArticleTitle("测试一");
        article.setArticleContent("测试一");
        article.setArticleDate(new Date());
        article.setArticleAuthor("亦初");
        articleMapper.addArticle(article);
    }

    @Test
    public void getArticleLimitedTest() {
        System.out.println(articleMapper.getArticleLimited(0, 10));
    }

    @Test
    public void getTheNumOfArticleTest() {
        System.out.println(articleMapper.getTheNumOfArticle());
    }

    @Test
    public void getAllRecommendArticleTest() {
        System.out.println(articleMapper.getAllRecommendArticle());
    }

    @Test
    public void getArticleAndTagNameTest() {
        for (Object obj : articleMapper.getArticleAndTagName(0, 10)) {
            System.out.println(obj);
        }
    }

    @Test
    public void getArticleAndTagNameByIdTest() {
        System.out.println(articleMapper.getArticleAndTagNameById(74));
    }

    @Test
    public void updateCoverTest() {
        String articleCover = "http://192.168.2.104:8081/85/cover.jpg";
        int articleId = 85;
        articleMapper.updateCover(articleId, articleCover);
    }
}
