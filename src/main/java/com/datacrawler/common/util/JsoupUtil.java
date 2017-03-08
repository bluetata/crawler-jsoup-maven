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

        String html = "";
        Document doc = null;

        doc = Jsoup.connect(url).timeout(10000).userAgent("Mozilla").get();
        html = doc.toString().replaceAll("&amp;", "&");

        return html;
    }

    // 以 Document 对象形式返回网页
    public static Document getDocument(String url) throws Exception {
        Document doc = null;
        doc = Jsoup.connect(url).timeout(10000).userAgent("Mozilla").get();

        return doc;
    }
}
