package com.datacrawler.common.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Jsoup形式解析网页工具类
 * 
 * @since 2017/03/07</br>
 * @author bluetata</br>
 * @version 1.0</br>
 * 
 */
public class JsoupUtil {

    // 以 String 形式返回网页
    // 所传のURL必须以 "http://www."开头
    public static String getHtml(String url) throws Exception {

        Document doc = Jsoup.connect(url).timeout(10000).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.122 Safari/534.30").get();
        String _html = doc.toString().replaceAll("&amp;", "&");

        return _html;
    }

    // 以 Document 对象形式返回网页
    public static Document getDocument(String url) throws Exception {
        Document doc = Jsoup.connect(url).timeout(10000)
                .userAgent(
                        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.122 Safari/534.30")
                .get();

        return doc;
    }
}
