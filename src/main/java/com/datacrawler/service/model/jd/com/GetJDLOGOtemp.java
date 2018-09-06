/**
 * JDLoginApater.java
 *
 * Function：Jsoup Model Apater class.
 *
 *   ver     date           author
 * ──────────────────────────────────
 *   1.0     2017/06/16     bluetata
 *
 * Copyright (c) 2017, [https://github.com/] All Rights Reserved.
 */
package com.datacrawler.service.model.jd.com;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.datacrawler.common.util.JsoupUtil;

/**
 * @author bluetata
 *
 */
public class GetJDLOGOtemp {

    public static final String LOGIN_URI = "https://passport.jd.com/new/login.aspx";
    public static final String USER_AGENT = "Mozilla/5.0";
    //public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.12 (KHTML, like Gecko) Maxthon/3.0 Chrome/18.0.966.0 Safari/535.12";
    public static final int TIMEOUT_UNIT = 1000;
    public static final int TIMEOUT_TIMES = 50;
    
    public static void main(String[] args) throws Exception {

        String url = "https://shouji.jd.com/";
        Document doc = JsoupUtil.getDocument(url);
        Element ele = doc.select("div#logo-2014").first();
        // System.out.println(doc.toString());
        System.out.println(ele);
        
    }

}
