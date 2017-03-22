/**
 * JsoupUtil.java
 *
 * Function：Jsoup utility class.
 *
 *   ver     date           author
 * ──────────────────────────────────
 *   1.0     2017/02/22     bluetata
 *
 * Copyright (c) 2017, [https://github.com/] All Rights Reserved.
 */
package com.datacrawler.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.datacrawler.consts.SystemConstants;
import com.datacrawler.consts.UtilsConstants;

/**
 * En:Utils class of by <code>Jsoup</code> to parse website html.</br>
 * Jp:ウェブサイトを解析する用ユーティリティクラス</br>
 * Zh:Jsoup模拟浏览器解析网页工具类</br>
 * 
 * @since crawler(datasnatch) version(1.0)</br>
 * @author bluetata / dietime1943@gmail.com</br>
 * @version 1.0</br>
 * 
 */
public class JsoupUtil {

    /**
     * 模拟浏览器、以String形式返回被访问的url的源码。
     * 
     * @param url 被访问的website. 所传的URL必须以 "http://www."开头
     * @return _html 以Stirng类型返回被访问网页的html.如果doc为null的情况方法返回null。
     * @throws Exception
     */
    public static String getHtml(String url) throws Exception {

        String _html = null;
        Document doc = getDocument(url);
        
        if (doc != null) {
            _html = doc.toString().replaceAll(UtilsConstants.AMP, UtilsConstants.AMPERSAND);
        }

        return _html;
    }

    /**
     * 方法用途和描述: 模拟浏览器以String类型返回被访问的网站html
     * 
     * @param url 被访问的website. 所传的URL必须以 "http://www."开头
     * @return doc 以Document类型返回被访问网页的html
     * @throws Exception
     */
    public static Document getDocument(String url) throws Exception {

        Document doc = null;
        StringWriter strWriter = new StringWriter();
        PrintWriter prtWriter = new PrintWriter(strWriter);

        // En:get retry max count by properties(com-constants.properties)
        // Zh:通过properties获取最大retry次数
        int maxRetry = Integer.parseInt(PropertyReader.getProperties(SystemConstants.COM_CONSTANTS)
                .getProperty(UtilsConstants.MAX_RETRY_COUNT));
        int sleepTime = Integer.parseInt(PropertyReader.getProperties(SystemConstants.COM_CONSTANTS)
                .getProperty(UtilsConstants.SLEEP_TIME_COUNT));

        // En: if exception is occurred then retry loop is continue to run;
        // Jp: 異常を起きる場合、ループを続き実行する。
        for (int j = 1; j <= maxRetry; j++) {

            try {
                if (j != 1) {
                    Thread.sleep(sleepTime);
                }
                doc = Jsoup.connect(url).timeout(10000)
                        .userAgent(
                                // add userAgent. TODO There is a plan to configure userAgent to load that userAgent from a property file.
                                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.122 Safari/534.30")
                        .get();

                // En: normal finish situation,loop is broken.
                // Jp: サービスが正常に終了した場合、ループを中止します。
                // Zh: 正常终了的情况、终止循环。
                break;

            } catch (Exception ex) {
                // throw new Exception(ex); dead code is occurred

                // StackTraceを文字列で取得
                ex.printStackTrace(prtWriter);
                String stackTrace = strWriter.toString();

                if (strWriter != null) {
                    try {
                        strWriter.close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
                if (prtWriter != null) {
                    prtWriter.close();
                }

                // En:info log is output. Jp: Infoログとして、エラー内容を出力。 Zh:输出到info log。
                Log4jUtil.info(stackTrace);
            }
        }
        return doc;
    }

    /**
     * get document by override method which is <code>getDocument</code>
     * 
     * @param url  visit link
     * @return Document  object
     * @throws Exception
     */
    public static Document _getDocument(String url) throws Exception {

        return getDocument(url, null, null);

    }

    
    /**
     * 
     * @param url
     * @param key
     * @param value
     * @return
     * @throws Exception
     */
    public static Document getDocument(String url, String key, String value) throws Exception {

        Document doc = null;

        if (StringUtil.isEmpty(key) && StringUtil.isEmpty(value)) {
            doc = getDocument(url);
        } else if (!StringUtil.isEmpty(key) && !StringUtil.isEmpty(value)){
            Map<String, String> data = new HashMap<String, String>();
            data.put(key, value);
            doc = getDocument(url, data);
        } else {
            // parameter is error. 参数が不正である、所传参数错误。
            throw new IllegalArgumentException("key or value is err"); // TODO hard coding is fixing bluetata 2017/03/20 add
        }
        return doc;
    }

    /**
     * 
     * @param url
     * @param data
     * @return
     * @throws Exception
     */
    public static Document getDocument(String url, Map<String, String> data) throws Exception {
        
        Document doc = null;
        Connection conn = null;
        StringWriter strWriter = new StringWriter();
        PrintWriter prtWriter = new PrintWriter(strWriter);

        // En:get retry max count by properties(com-constants.properties)
        // Zh:通过properties获取最大retry次数
        int maxRetry = Integer.parseInt(PropertyReader.getProperties(SystemConstants.COM_CONSTANTS)
                .getProperty(UtilsConstants.MAX_RETRY_COUNT));
        int sleepTime = Integer.parseInt(PropertyReader.getProperties(SystemConstants.COM_CONSTANTS)
                .getProperty(UtilsConstants.SLEEP_TIME_COUNT));

        // En: if exception is occurred then retry loop is continue to run;
        // Jp: 異常を起きる場合、ループを続き実行する。
        for (int j = 1; j <= maxRetry; j++) {

            try {
                if (j != 1) {
                    Thread.sleep(sleepTime);
                }
                conn = Jsoup.connect(url).timeout(10000)
                        .userAgent(
                                // add userAgent. TODO There is a plan to configure userAgent to load that userAgent from a property file.
                                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.122 Safari/534.30");

                if (data != null && data.size() != 0) {
                    // Adds all of the supplied data to the request data parameters. // 20170320 bluetata add 
                    conn.data(data);
                }
                doc = conn.get();

                // En: normal finish situation,loop is broken.
                // Jp: サービスが正常に終了した場合、ループを中止します。
                // Zh: 正常终了的情况、终止循环。
                break;

            } catch (Exception ex) {
                // throw new Exception(ex); dead code is occurred

                // StackTraceを文字列で取得
                ex.printStackTrace(prtWriter);
                String stackTrace = strWriter.toString();

                if (strWriter != null) {
                    try {
                        strWriter.close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
                if (prtWriter != null) {
                    prtWriter.close();
                }

                // En:info log is output. Jp: Infoログとして、エラー内容を出力。 Zh:输出到info log。
                Log4jUtil.info(stackTrace);
            }
        }
        return doc;
    }

    // post 提交--------------------------------------
    
}
