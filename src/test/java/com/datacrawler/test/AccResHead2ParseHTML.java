package com.datacrawler.test;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.datacrawler.common.util.JsoupUtil;

import org.jsoup.Connection.Response;

public class AccResHead2ParseHTML {

    public static void main(String[] args) throws Exception {
        
        String _url = "http://blog.csdn.net/";
        String defaultCharset = "UTF-8";
        
        // according to response header to get parsed page char-set.
        Connection connection = Jsoup.connect(_url)
                .userAgent("Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36") // User-Agent of Chrome 55
                .referrer("http://blog.csdn.net/")
                .header("Content-Type", "application/json; charset=GBK")
                .header("Accept", "text/plain, */*; q=0.01")
                .header("Accept-Encoding", "gzip,deflate,sdch")
                .header("Accept-Language", "es-ES,es;q=0.8")
                .header("Connection", "keep-alive")
                .header("X-Requested-With", "XMLHttpRequest")
                .maxBodySize(100)
                .timeout(1000 * 10)
                .method(Connection.Method.POST);

        Response response = connection.execute();
        String charset = response.charset();
        System.out.println("charset:" + charset);
        
        if (null != charset && !charset.isEmpty()) {
            defaultCharset = charset;
        }
        
//        Document doc = Jsoup.connect("http://blog.csdn.net/")
//                .cookies(response.cookies())
//                .timeout(10 * 10000)
//                .get();
        
        Document doc = Jsoup.parse(new URL(_url).openStream(), defaultCharset, _url);
        System.out.println(doc);
        
        System.out.println("----------:" + JsoupUtil.getStaticCharset(doc));
    }

}
