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
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.datacrawler.consts.SystemConstants;
import com.datacrawler.consts.UtilsConstants;

/**
 * En:Utils class to parse website html by <code>Jsoup</code></br>
 * Jp:ウェブサイトを解析する用ユーティリティクラス</br>
 * Zh:Jsoup模拟浏览器解析网页工具类</br>
 * 
 * @since crawler(datasnatch) version(1.0)</br>
 * @author bluetata / dietime1943@gmail.com</br>
 * @version 1.0</br>
 * 
 * 重构代码：提取getDocument方法,并增加三类方法：getDocumentWithHeaders,
 * getDocumentWithData和getDocumentWithCookies以满足在get提交中对不同数据绑定的提交       bluetata 2017/03/22
 * 
 */
public final class JsoupUtil {
    
    private JsoupUtil() {
    }

    /**
     * 方法说明：模拟浏览器,以String形式返回被访问的url的源码。
     * 
     * @param url 被访问的website. 所传的URL必须以 "http://www."开头
     * @return _html 以Stirng类型返回被访问网页的html.如果doc为null的情况方法返回空串""。
     * @throws Exception
     */
    public static String getHtml(String url) throws Exception {

        String _html = "";
        Document doc = getDocument(url);
        
        if (doc != null) {
            _html = doc.toString().replaceAll(UtilsConstants.AMP, UtilsConstants.AMPERSAND);
        }

        return _html;
    }

    /**
     * 方法说明：将document对象转换成String类型
     * 
     * @param doc 所要转换的document对象
     * @return _html 以Stirng类型返回被访问网页的html.如果doc为null的情况方法返回空串""。
     * @throws Exception
     */
    public static String covertDoc2Str(Document doc) throws Exception {
        return doc == null ? "" : doc.toString().replaceAll(UtilsConstants.AMP, UtilsConstants.AMPERSAND);
    }

    /**
     * 方法说明：get document by override method which is <code>getDocument</code>
     * 
     * @param url  visit link
     * @return Document  doc object
     * @throws Exception
     */
    public static Document getDocument(String url) throws Exception {
        return getDocument(url, null, null);
    }

    /**
     * 方法说明：绑定单header模拟浏览器,返回document对象
     * 
     * @param url           被访问url
     * @param headerKey     header的key
     * @param headerValue   header的value
     * @return Document     返回document对象
     * @throws Exception
     */
    public static Document getDocumentWithHeader(String url, String headerKey, String headerValue) throws Exception {

        Document doc = null;

        if (StringUtil.isEmpty(headerKey) && StringUtil.isEmpty(headerValue)) {
            doc = getDocument(url);
        } else if (!StringUtil.isEmpty(headerKey) && !StringUtil.isEmpty(headerValue)){
            Map<String, String> requestHeader = new HashMap<String, String>();
            requestHeader.put(headerKey, headerValue);
            doc = getDocumentWithHeaders(url, requestHeader);
        } else {
            // parameter is error. 参数が不正である、所传参数错误。
            throw new IllegalArgumentException("key or value is err"); // TODO hard coding is fixing bluetata 2017/03/20 add
        }
        return doc;
    }

    /**
     * 方法说明：绑定header集合模拟浏览器,返回document对象
     * 
     * @param url           被访问url
     * @param headersMap    绑定header的map集合
     * @return Document     返回document对象
     * @throws Exception
     */
    public static Document getDocumentWithHeaders(String url, Map<String, String> headersMap) throws Exception {

        Document doc = null;

        if (headersMap != null && headersMap.size() != 0) {
            doc = getDocument(url, headersMap, UtilsConstants.REQUEST_HEADERS);
        }
        return doc;
    }

    /**
     * 方法说明：绑定单data（parameter）模拟浏览器,并返回document对象
     * 
     * @param url           被访问的url
     * @param dataKey       parameter的key
     * @param dataValue     parameter的value
     * @return Document     返回document对象
     * @throws Exception
     */
    public static Document getDocumentWithData(String url, String dataKey, String dataValue) throws Exception {

        Document doc = null;

        if (StringUtil.isEmpty(dataKey) && StringUtil.isEmpty(dataValue)) {
            doc = getDocument(url);
        } else if (!StringUtil.isEmpty(dataKey) && !StringUtil.isEmpty(dataValue)){
            Map<String, String> dataMap = new HashMap<String, String>();
            dataMap.put(dataKey, dataValue);
            doc = getDocumentWithData(url, dataMap);
        } else {
            // parameter is error. 参数が不正である、所传参数错误。
            throw new IllegalArgumentException("key or value is err"); // TODO hard coding is fixing bluetata 2017/03/20 add
        }
        return doc;
    }

    /**
     * 方法说明：绑定data（parameters）集合模拟浏览器,并返回document对象
     * 
     * @param url           被访问的url
     * @param dataMap       parameters的map集合
     * @return Document     返回document对象
     * @throws Exception
     */
    public static Document getDocumentWithData(String url, Map<String, String> dataMap) throws Exception {

        Document doc = null;

        if (dataMap != null && dataMap.size() != 0) {
            doc = getDocument(url, dataMap, UtilsConstants.REQUEST_DATA);
        }
        return doc;
    }

    /**
     * 方法说明：绑定单cookie模拟浏览器,返回document对象
     * 
     * @param url           被访问url
     * @param cookieKey     绑定cookie的key
     * @param cookieValue   绑定cookie的value
     * @return Document     返回document对象
     * @throws Exception
     */
    public static Document getDocumentWithCookie(String url, String cookieKey, String cookieValue) throws Exception {

        Document doc = null;

        if (StringUtil.isEmpty(cookieKey) && StringUtil.isEmpty(cookieValue)) {
            doc = getDocument(url);
        } else if (!StringUtil.isEmpty(cookieKey) && !StringUtil.isEmpty(cookieValue)){
            Map<String, String> cookiesMap = new HashMap<String, String>();
            cookiesMap.put(cookieKey, cookieValue);
            doc = getDocumentWithCookies(url, cookiesMap);
        } else {
            // parameter is error. 参数が不正である、所传参数错误。
            throw new IllegalArgumentException("key or value is err"); // TODO hard coding is fixing bluetata 2017/03/20 add
        }
        return doc;
    }

    /**
     * 方法说明：绑定cookies集合模拟浏览器,并返回document对象
     * 
     * @param url           被访问的url
     * @param cookiesMap    绑定cookies的map集合
     * @return Document     返回document对象
     * @throws Exception
     */
    public static Document getDocumentWithCookies(String url, Map<String, String> cookiesMap) throws Exception {

        Document doc = null;

        if (cookiesMap != null && cookiesMap.size() != 0) {
            doc = getDocument(url, cookiesMap, UtilsConstants.REQUEST_COOKIES);
        }
        return doc;
    }

    /**
     * 方法说明：根据绑定的数据type选择绑定数据种类模拟浏览器
     * 
     * @param url           request url
     * @param bindData      bind data
     * @param requestType   request type: "headers" "data" "cookies" etc.
     * @return              Document object.
     * @throws Exception    Exception
     */
    public static Document getDocument(String url, Map<String, String> bindData, String requestType) throws Exception {

        Document doc = null;
        Connection conn = null;
        StringWriter strWriter = new StringWriter();
        PrintWriter prtWriter = new PrintWriter(strWriter);

        // En:get max retry count from properties file(com-constants.properties)
        // Jp:プロパティファイルでロックタイムアウトのリトライ回数を取得する Zh:通过properties获取最大retry次数
        int maxRetry = Integer.parseInt(PropertyReader.getProperties(SystemConstants.COM_CONSTANTS)
                .getProperty(UtilsConstants.MAX_RETRY_COUNT));
        // En: get sleep time from properties file Jp:プロパティファイルでロックタイムアウトのスリープ時間を取得する
        int sleepTime = Integer.parseInt(PropertyReader.getProperties(SystemConstants.COM_CONSTANTS)
                .getProperty(UtilsConstants.SLEEP_TIME_COUNT));
        
        int temp = 0;

        // En: if exception is occurred then retry loop is continue to run;
        // Jp: 異常を起きる場合、ループを続き実行する。
        for (int j = 1; j <= maxRetry; j++) {

            try {
                if (j != 1) {
                    Thread.sleep(sleepTime);
                }
                temp = Integer.parseInt(String.valueOf(Math.round(Math.random() * (UserAgent.length - 1))));
                conn = Jsoup.connect(url).timeout(10000)
                       // .userAgent(
                                // add userAgent. TODO There is a plan to configure userAgent to load that userAgent from a property file.
                         //       "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.122 Safari/534.30");
                        .userAgent(UserAgent[temp]);
                if (bindData != null && bindData.size() != 0 && !StringUtil.isEmpty(requestType)) {
                    switch (requestType) {
                    case UtilsConstants.REQUEST_HEADERS:
                        // adds each of the supplied headers to the request. // bluetata 2017/03/22 add
                        conn.headers(bindData);
                        break;
                    case UtilsConstants.REQUEST_DATA:
                        // adds all of the supplied data to the request data parameters. // 20170320 bluetata add
                        conn.data(bindData);
                        break;
                    case UtilsConstants.REQUEST_COOKIES:
                        // adds each of the supplied cookies to the request. // bluetata 2017/03/22 add
                        conn.cookies(bindData);
                        break;
                    default:
                        // TODO stream etc. logic is adding. bluetata 2017/03/22 add
                        break;
                    }
                }
                doc = conn.get();

                // En: normal finish situation,loop is broken.
                // Jp: サービスが正常に終了した場合、ループを中止します。
                // Zh: 正常终了的情况、终止循环。
                break;

            } catch (Exception ex) {
                // if throw new Exception(ex); dead code is occurred, retry is invalid.

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
                Log4jUtil.error("第" + j + "次请求异常。");
                Log4jUtil.error(stackTrace);
            }
        }
        return doc;
    }
    
    
    /**
     * 解析HTML获取字符编码
     * @param doc
     *          Dcoument 对象
     * @return
     * @throws Exception
     */
    public static String getStaticCharset(Document doc) throws Exception {
        String charset = "";
        String coarseCharset = "";
        Elements elements = doc.select("meta");
        for (Element element : elements) {
            coarseCharset = element.attr("content");
            if (coarseCharset.contains("charset")) {
                charset = coarseCharset.substring(
                        coarseCharset.indexOf("=") + 1, coarseCharset.length());

                return charset;
            }
        }
        return "";
    }

    /**
     * @return 谷歌浏览器
     * @return 火狐浏览器
     * @return 猎豹浏览器
     * @return 360浏览器
     * @return 傲游浏览器
     * @return 腾讯TT浏览器
     * @return Opera浏览器
     * @return IE6、8、9
     * @return 世界之窗浏览器
     * @return 腾讯QQ浏览器
     * @return 苹果浏览器
     */
    public static String[] UserAgent = {
            
        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.13 (KHTML, like Gecko) Chrome/24.0.1290.1 Safari/537.13",
        "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:17.0) Gecko/20100101 Firefox/17.0",
        "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; LBBROWSER)",
        "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)",
        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.12 (KHTML, like Gecko) Maxthon/3.0 Chrome/18.0.966.0 Safari/535.12",
        "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)",
        "Opera/9.80 (Windows NT 6.1; WOW64) Presto/2.12.388 Version/12.12",
        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 6.1; WOW64; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)",
        "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)",
        "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)",
        "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; qihu theworld)",
        "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; QQBrowser/7.0.4350.400)",
        "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.6 Safari/534.57.2"
    };
    
    public static String[] BrowserType = {"谷歌浏览器", "火狐浏览器", "猎豹浏览器", "360浏览器", "傲游浏览器", "腾讯TT浏览器", "Opera浏览器", "IE6浏览器", "IE8浏览器", "IE9浏览器", "世界之窗浏览器", "腾讯QQ浏览器", "苹果浏览器"};
    
    
    
    // post 提交--------------------------------------

}
