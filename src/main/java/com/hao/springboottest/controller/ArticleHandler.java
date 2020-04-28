package com.hao.springboottest.controller;
import com.alibaba.fastjson.JSONObject;
import com.hao.springboottest.entity.Article;
import com.hao.springboottest.repository.ArticleRepository;
import com.hao.springboottest.utils.RetCode;
import com.hao.springboottest.utils.RetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/article")
public class ArticleHandler {
    @Autowired
    private ArticleRepository articleRepository;
    @PostMapping("/save")
    public String save(@RequestBody Map<String,String> map){
        Random random = new Random();
        Article article = new Article();
        article.setPublishDate(new Date());
        article.setSeeNumber(random.nextInt(10000));
        article.setGreat(random.nextInt(10000));
        String body = map.get("Body");
        String title = map.get("Title");
        String author = map.get("Author");
        JSONObject jsonObject = JSONObject.parseObject(author);
        String authorName = jsonObject.getString("uname");
        article.setBody(body);
        article.setTitle(title);
        article.setAuthor(authorName);
        article.setUId(Integer.valueOf(jsonObject.getString("uid")));
        Article result = articleRepository.save(article);
        if(result != null){
            return "SUCCESS";
        }else{
            return "error";
        }
    }
    @PostMapping("/getUserAllArticle")
    public RetResult getUserAllArticle(@RequestParam(value = "uid")String uid){
        RetResult retResult = new RetResult();
        Integer Uid = Integer.valueOf(uid);
        List<Article> articles;
        articles = articleRepository.findByuId(Uid);
        Collections.sort(articles);
        if(articles != null){
            retResult.setMsg("查找成功");
            retResult.setCode(RetCode.SUCCESS.getCode());

            retResult.setData(articles);
        }
        else{
            retResult.setMsg("查找失败");
            retResult.setCode(RetCode.FAIL.getCode());
        }
        return retResult;
    }
    @GetMapping("/getUserOneArticle")
    public RetResult getUserOneArticle(@RequestParam(value = "id")String id){
        RetResult retResult = new RetResult();
        Optional<Article> article = Optional.of(new Article());
        article = articleRepository.findById(id);
        if(article.isPresent()){
            retResult.setMsg("查找文章成功");
            retResult.setCode(RetCode.SUCCESS.getCode());
            Article article1 = article.orElse(new Article());  //可以还原Optional
            retResult.setData(article1);
            System.out.println("===================");
            System.out.println(article);
            System.out.println("===================");
            System.out.println(article1);
        }
        else
        {
            retResult.setMsg("查找文章失败");
            retResult.setCode(RetCode.FAIL.getCode());
        }
        return retResult;
    }

}
