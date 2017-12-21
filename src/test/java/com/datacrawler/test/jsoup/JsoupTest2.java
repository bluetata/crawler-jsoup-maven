package com.datacrawler.test.jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.datacrawler.common.util.JsoupUtil;

public class JsoupTest2 {

    public static void main(String[] args) throws IOException {
        
        String url = "https://www.google.co.uk/maps/place/台湾台北市/";
        // String url = "http://www.shixiseng.com/";

        Document doc = Jsoup.connect(url).timeout(4000).proxy("proxy.emea.ibm.com", 8080).userAgent("Mozilla").get();
        
        System.out.println(doc.html());
    }

}
