package com.hao.springboottest.repository;

import com.hao.springboottest.entity.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends MongoRepository<Article,String> {
    public Article findByTitle(String title);
    public List<Article> findByAuthor(String author);
    public List<Article> findByuId(Integer uId);
    public Optional<Article> findById(String id);
}
