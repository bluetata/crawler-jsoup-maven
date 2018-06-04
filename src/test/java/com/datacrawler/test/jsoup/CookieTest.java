package com.datacrawler.test.jsoup;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import com.datacrawler.common.util.JsoupUtil;

public class CookieTest {

    public static void main(String[] args) throws IOException {
        Response res = Jsoup.connect("https://blog.csdn.net/sdfujichao/article/details/51354931").execute();
        
        System.out.println(res.cookies());

    }

}
