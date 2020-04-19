package com.hao.springboottest.utils;

import org.springframework.web.util.HtmlUtils;

public class HtmlChangeUtils {
//    public static void main(String[] args){
//        String html = "<img style=\"width: 100%;height: auto;\" src=\"D:\\test\\test.jpg\" />";
//        System.out.println(htmlTo(html));
//        System.out.println(toHtml(htmlTo(html)));
//    }
    public static String htmlTo(String html){
        return HtmlUtils.htmlEscape(html);
    }
    public static String toHtml(String data){
        return HtmlUtils.htmlUnescape(data);
    }
}
