/**
 * AmazonLoginApater.java
 *
 * Function：Jsoup model apater class.
 *
 *   ver     date           author
 * ──────────────────────────────────
 *   1.0     2017/06/20     bluetata
 *
 * Copyright (c) 2017, [https://github.com/] All Rights Reserved.
 */
package com.datacrawler.service.model.amazon.cn;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

/**
 * @since crawler(datasnatch) version(1.0)</br>
 * @author bluetata / dietime1943@gmail.com</br>
 * @version 1.0</br>
 *
 */

public class AmazonLoginApater {
    
    public static final String LOGIN_URI = "https://www.amazon.cn/ap/signin?_encoding=UTF8&openid.assoc_handle=cnflex&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.ns.pape=http%3A%2F%2Fspecs.openid.net%2Fextensions%2Fpape%2F1.0&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.cn%2F%3Fie%3DUTF8%26ref_%3Dnav_ya_signin";
    public static final String USER_AGENT = "Mozilla/5.0";
    //public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.12 (KHTML, like Gecko) Maxthon/3.0 Chrome/18.0.966.0 Safari/535.12";
    public static final int TIMEOUT_UNIT = 1000;
    public static final int TIMEOUT_TIMES = 10;
    
    public static void main(String[] args) {
        // grab login form page first
        try {
            
            //lets make data map containing all the parameters and its values found in the form
            Map<String, String> mapParamsData = new HashMap<String, String>();
            mapParamsData.put("email", "dietime1943@hotmail.com");
            mapParamsData.put("password", "152300");
            
            Response loginResponse = Jsoup.connect("https://passport.jd.com/new/login.aspx")
                    .userAgent(USER_AGENT)
                    .timeout(TIMEOUT_UNIT * TIMEOUT_TIMES)
                    .data(mapParamsData)
                    .method(Method.POST)
                    .followRedirects(true)
                    .execute();
            
            System.out.println("Fetched login page");
            // System.out.println(loginResponse.toString());
            
          //get the cookies from the response, which we will post to the action URL
            Map<String, String> mapLoginPageCookies = loginResponse.cookies();
            
            System.out.println(mapLoginPageCookies);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection validateTLSCertificates(boolean b) {
        // TODO Auto-generated method stub
        return null;
    }

}
