package com.hao.springboottest.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
@Data
public class Article implements Comparable{
    @Id
    private String id;
    private Integer uId;
    private String title;
    private String author;
    private Date publishDate;
    private String body;
    private Integer seeNumber;
    private Integer great;
    @Override
    public String toString(){
        return String.format("Article[id = %s,uId = %d,title = %s,author = %s,publishDate = %s,body = %s,seeNumber = %d,great = %d]"
                ,id,uId,title,author,publishDate.toString(),body,seeNumber,great);
    }




    @Override
    public int compareTo(Object o) {
        Article article = (Article)o;
        if((this.publishDate.before(article.publishDate)))
            return 1;
        else
            return -1;
    }

}
