package com.datacrawler.service.model.net.csdn;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class CSDNLoginApater {

    public static void main(String[] args) {
        
        String lt = null;
        String execution = null;
        String _eventId = null;
        
        Document doc = null;
        
        
        
        try {
            doc = Jsoup.connect("https://passport.csdn.net/account/login?from=http://my.csdn.net/my/mycsdn")
                    .userAgent("Mozilla/5.0")
                    .timeout(30000)
                    .get();
            
            Connection.Response loginResponse = Jsoup.connect("https://passport.csdn.net/account/login?from=http://my.csdn.net/my/mycsdn")
                    .method(Connection.Method.GET)
                    .execute();
            
            // System.out.println(doc.toString());
            
            Element form = doc.select("#fm1").get(0);
            
            lt = form.select("input[name=lt]").get(0).val();
            execution = form.select("input[name=execution]").get(0).val();
            _eventId = form.select("input[name=_eventId]").get(0).val();
            
            System.out.println(lt);
            System.out.println(execution);
            System.out.println(_eventId);
            
            Document document1 = Jsoup.connect("https://passport.csdn.net/account/login?from=http://my.csdn.net/my/mycsdn")
            .header("Host", "passport.csdn.net")
            .header("Referer", "https://passport.csdn.net/account/login?from=http://my.csdn.net/my/mycsdn")
            .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .userAgent("Mozilla/5.0")
            .data("username", "dietime1943")
            .data("password", "lvmeng152300")
            .data("lt", lt)
            .data("execution", execution)
            .data("_eventId", _eventId)
            .cookies(loginResponse.cookies())
            .timeout(30000)
            .post();
            
            System.out.println(document1.body().html());
//
//            
//            Connection.Response res = Jsoup.connect("https://passport.csdn.net/account/login?from=http://my.csdn.net/my/mycsdn")
//                    .data("username", "dietime1943@gmail.com", "password", "lvmeng152300", "lt", "LT-4231353-tYmy24yTb5xHZqitHEGQVrO6Ye4f2A", "execution", "e9s1", "_eventId", "submit")
//                    .method(Method.POST)
//                    .execute();
//            
//            Map<String, String> cookies = res.cookies();
//            
//            Document doc = Jsoup.connect("http://my.csdn.net/my/mycsdn")
//                    .userAgent("Mozilla/5.0")
//                    .cookies(cookies)
//                    .timeout(30000)
//                    .get();
//
//            System.out.println(doc.toString());
//            //System.out.println(ress.statusCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}