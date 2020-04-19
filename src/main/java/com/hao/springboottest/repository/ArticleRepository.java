package com.hao.springboottest.repository;

import com.hao.springboottest.entity.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ArticleRepository extends MongoRepository<Article,String> {
    public Article findByTitle(String title);
    public List<Article> findByAuthor(String author);
}
