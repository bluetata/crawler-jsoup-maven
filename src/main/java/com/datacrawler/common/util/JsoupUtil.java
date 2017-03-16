/**
 * JsoupUtil.java
 *
 * Function：simulated browser.
 *
 *   ver     date           author
 * ──────────────────────────────────
 *   1.0     2017/02/22     bluetata
 *
 * Copyright (c) 2017, [https://github.com/] All Rights Reserved.
 */
package com.datacrawler.common.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.datacrawler.consts.SystemConstants;
import com.datacrawler.consts.UtilsConstants;

/**
 * En:Utils class of by <code>Jsoup</code> to parse website html.
 * Zh:Jsoup形式解析网页工具类
 * 
 * 
 * @since crawler(datasnatch) version(1.0)</br>
 * @author bluetata</br>
 * @version 1.0</br>
 * 
 */
public class JsoupUtil {

    /**
     * 方法用途和描述: 模拟浏览器以String类型返回被访问的网站html
     * 
     * @param url 被访问的website
     * @return _html 以Stirng类型返回被访问网页的html
     * @throws Exception
     */
    // 以 String 形式返回网页
    // 所传的URL必须以 "http://www."开头
    public static String getHtml(String url) throws Exception {

        Document doc = null;
        String _html = null;

        // En:get retry max count by properties(com-constants.properties) Zh:通过properties获取最大retry次数
        int maxRetry = Integer.parseInt(PropertyReader.getProperties(SystemConstants.COM_CONSTANTS)
                .getProperty(UtilsConstants.MAX_RETRY_COUNT));
        int sleepTime = Integer.parseInt(PropertyReader.getProperties(SystemConstants.COM_CONSTANTS)
                .getProperty(UtilsConstants.SLEEP_TIME_COUNT));

        // En: if exception is occurred then retry loop is run; Jp: 異常を起きる場合、ループを
        for (int j = 1; j <= maxRetry; j++) {
            
            try{
                if( j!=1 ){
                    Thread.sleep(sleepTime);
                }
                doc = Jsoup.connect(url).timeout(10000)
                        .userAgent(
                                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.122 Safari/534.30")
                        .get();
                // String _html = doc.toString().replaceAll("&amp;", "&"); // replace that logic by doc.text()  bluetata 2013/07/19 del.
                _html = doc.text();
                
                // En:normal finish situation,loop is broken. Jp:サービスが正常に終了した場合、ループを中止します。 Zh: 正常终了的情况、终止循环。
                break;
                
            } catch (Exception ex){
                // throw new Exception(ex); dead code is occurred
                
            }
        }
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
