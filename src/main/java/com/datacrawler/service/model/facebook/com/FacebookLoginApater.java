package com.datacrawler.service.model.facebook.com;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class FacebookLoginApater {

    public static void main(String[] args) throws IOException {

        connect();
    }

    
    static Map<String,String> connect() throws IOException{
        Connection.Response res = Jsoup.connect("https://www.facebook.com/login.php")
                .data("username", "dietime1943@hotmail.com", "password", "password")
                .timeout(30 * 1000)
                .userAgent("Mozilla/5.0")
                .method(Method.POST)
                .execute();
        Document doc = res.parse();
        System.out.println(doc);
        
        Map<String, String> loginCookies = res.cookies();
        String sessionId = res.cookie("SESSIONID");
        return loginCookies;
    }
}
