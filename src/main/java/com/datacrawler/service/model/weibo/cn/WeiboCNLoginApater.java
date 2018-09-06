package com.datacrawler.service.model.weibo.cn;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WeiboCNLoginApater {

    public static void main(String[] args) throws IOException {

        try {
            String url = "https://www.oschina.net/home/login";
            String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.130 Safari/537.36";

            Connection.Response response = Jsoup.connect(url).userAgent(userAgent).method(Connection.Method.GET)
                    .execute();

            response = Jsoup.connect(url).cookies(response.cookies()).userAgent(userAgent)
                    .referrer("https://www.oschina.net/home/login?goto_page=https%3A%2F%2Fmy.oschina.net%2Fbluetata")
                    .data("username", "dietime1943@hotmail.com", "password", "lvmeng152300").data("save_login", "1")
                    .followRedirects(false)
                    .method(Connection.Method.POST).followRedirects(true).timeout(30 * 1000).execute();

            System.err.println(response.statusCode());
            
            Document doc = Jsoup.connect("https://my.oschina.net/bluetata").cookies(response.cookies())
                    .userAgent(userAgent).timeout(30 * 1000).get();

            System.out.println(doc);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static Map<String, String> connect() throws IOException {

        // Connection.Response loginForm =
        // Jsoup.connect("https://passport.weibo.cn/signin/login")
        // .method(Connection.Method.GET)
        // .execute();
        //
        // Connection.Response res =
        // Jsoup.connect("https://passport.weibo.cn/signin/login")
        // .data("username", "18241141433", "password", "152300")
        // .data("ec", "0", "entry", "mweibo")
        // .data("mainpageflag", "1", "savestate", "1")
        // .timeout(30 * 1000)
        // .userAgent("Mozilla/5.0")
        // .cookies(loginForm.cookies())
        // .method(Method.POST)
        // .execute();
        // Document doc = res.parse();
        // System.out.println(doc);

        Connection.Response loginForm = Jsoup.connect("https://www.oschina.net/home/login")
                .method(Connection.Method.GET).execute();

        Connection.Response res = Jsoup.connect("https://www.oschina.net/home/login").header("Host", "www.oschina.net")
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0")
                .referrer("https://www.oschina.net/home/login")
                .data("username", "dietime1943@hotmail.com", "password", "lvmeng152300").data("save_login", "1")
                .timeout(30 * 1000).cookies(loginForm.cookies()).method(Method.POST).execute();
        Document doc = res.parse();
        System.out.println(doc);

        Map<String, String> loginCookies = res.cookies();
        String sessionId = res.cookie("SESSIONID");

        return loginCookies;
    }
}
