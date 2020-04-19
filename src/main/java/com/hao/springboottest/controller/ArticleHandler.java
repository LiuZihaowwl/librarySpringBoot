package com.hao.springboottest.controller;

import com.hao.springboottest.entity.Article;
import com.hao.springboottest.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleHandler {
    @Autowired
    private ArticleRepository articleRepository;
    @PostMapping("/save")
    public String save(@RequestBody Map<String,String> map){
        Article article = new Article();
        article.setAuthor("hao");
        article.setTitle("photo");
        System.out.println(map.get("Body"));
        article.setBody(map.get("Body"));
        article.setPublishDate(new Date());
        article.setSeeNumber(99);
        article.setGreat(99);

        Article result = articleRepository.save(article);
        System.out.println(result);
        if(result != null){
            return "SUCCESS";
        }else{
            return "error";
        }
    }
    @GetMapping("/showdemo")
    public String showdemo(){
        Article article = articleRepository.findByTitle("photo");
        return article.body;
    }

}
