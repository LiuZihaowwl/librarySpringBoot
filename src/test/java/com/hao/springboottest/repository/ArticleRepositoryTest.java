package com.hao.springboottest.repository;

import com.hao.springboottest.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ArticleRepositoryTest {
    @Autowired
    private ArticleRepository articleRepository;
    @Test
    void saveArticle(){
        articleRepository.deleteAll();
        String html = "<img style=\"width: 100%;height: auto;\" src=\"D:\\test\\test.jpg\" />";
        Article article1 = new Article();
        article1.setAuthor("hao");
        article1.setTitle("Hello");
        article1.setBody("Hello world");
        article1.setPublishDate(new Date());
        article1.setSeeNumber(66);
        article1.setGreat(66);
        articleRepository.save(article1);
        Article article2 = new Article();
        article2.setAuthor("hao");
        article2.setTitle("shang");
        article2.setBody("shanglaihaipi");
        article2.setPublishDate(new Date());
        article2.setSeeNumber(99);
        article2.setGreat(99);
        articleRepository.save(article2);
        Article article3 = new Article();
        article3.setAuthor("hao");
        article3.setTitle("photo");
        article3.setBody(html);
        article3.setPublishDate(new Date());
        article3.setSeeNumber(99);
        article3.setGreat(99);
        articleRepository.save(article3);

        // fetch all customers
        System.out.println("Articles found with findAll():");
        System.out.println("-------------------------------");
        for (Article article : articleRepository.findAll()) {
            System.out.println(article);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Article found with findByTitle('Hello'):");
        System.out.println("--------------------------------");
        System.out.println(articleRepository.findByTitle("Hello"));

        System.out.println("Article found with findByAuthor('hao'):");
        System.out.println("--------------------------------");
        for (Article article : articleRepository.findByAuthor("hao")) {
            System.out.println(article);
        }
    }
    @Test
    void findAll(){
        List<Article> articles;
        articles = articleRepository.findByuId(1057);
        System.out.println("===================================");
        System.out.println(articles);
    }


}
