package com.datacrawler.test;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

public class TestApiOfConnect {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
//        Connection connection = Jsoup.connect("http://bluetata.com");

//        // connection.data("aaa","ccc");  // 这是重点
//
//        connection.header("Content-Type", "application/json; charset=UTF-8");  // 这是重点
//
//        connection.header("Accept", "text/plain, */*; q=0.01");  
//
//        connection.timeout(15000);  
//
//        //String body = "{\"CategoryType\":\"SiteHome\",\"ParentCategoryId\":0,\"CategoryId\":808,\"PageIndex\":2,\"TotalPostCount\":4000,\"ItemListActionName\":\"PostList\"}"; 
//
//        //connection.requestBody(body);  
//
//        Document document = connection.post();
        
        
        String jsonBody = "{\"name\":\"ACTIVATE\",\"value\":\"E0010\"}";
        
        Connection connection = Jsoup.connect("http://bluetata.com/")
                .userAgent("Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36") // User-Agent of Chrome 55
                .referrer("http://bluetata.com/")
                .header("Content-Type", "application/json; charset=UTF-8")
                .header("Accept", "text/plain, */*; q=0.01")
                .header("Accept-Encoding", "gzip,deflate,sdch")
                .header("Accept-Language", "es-ES,es;q=0.8")
                .header("Connection", "keep-alive")
                .header("X-Requested-With", "XMLHttpRequest")
                .requestBody(jsonBody)
                .maxBodySize(100)
                .timeout(1000 * 10)
                .method(Connection.Method.POST);

        Response response = connection.execute();
        
        
    }

}
