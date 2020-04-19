package com.hao.springboottest.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
@Data
public class Article {
    @Id
    public String id;
    public String title;
    public String author;
    public Date publishDate;
    public String body;
    public Integer seeNumber;
    public Integer great;
    @Override
    public String toString(){
        return String.format("Article[id = %s,title = %s,author = %s,publishDate = %s,body = %s,seeNumber = %d,great = %d]"
                ,id,title,author,publishDate.toString(),body,seeNumber,great);
    }

}
