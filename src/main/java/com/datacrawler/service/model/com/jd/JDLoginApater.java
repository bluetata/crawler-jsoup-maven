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
package com.datacrawler.service.model.com.jd;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author bluetata
 *
 */
public class JDLoginApater {

    public static final String LOGIN_URI = "https://passport.jd.com/new/login.aspx";
    public static final String USER_AGENT = "Mozilla/5.0";
    //public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.12 (KHTML, like Gecko) Maxthon/3.0 Chrome/18.0.966.0 Safari/535.12";
    public static final int TIMEOUT_UNIT = 1000;
    public static final int TIMEOUT_TIMES = 50;
    
    public static void main(String[] args) {
        // grab login form page first
        try {
            
            //lets make data map containing all the parameters and its values found in the form
            Map<String, String> mapParamsData = new HashMap<String, String>();
            mapParamsData.put("loginType", "f");
            mapParamsData.put("loginname", "18241141433");
            mapParamsData.put("nloginpwd", "152300");
            mapParamsData.put("eid", "2QE5VJVZUBCRYD7LQZBJBTEFRNKPMQQA5OXKXNY7AAN4A3DKDTR7IN3GXHE5C6B4GTMW3Z53B4RGORB6YG5LUWF2UA");
            mapParamsData.put("fp", "ae5baf289624644fced3f921c6a3792c");
            mapParamsData.put("pubKey", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDC7kw8r6tq43pwApYvkJ5laljaN9BZb21TAIfT/vexbobzH7Q8SUdP5uDPXEBKzOjx2L28y7Xs1d9v3tdPfKI2LR7PAzWBmDMn8riHrDDNpUpJnlAGUqJG9ooPn8j7YNpcxCa1iybOlc2kEhmJn5uwoanQq+CA6agNkqly2H4j6wIDAQAB");
            mapParamsData.put("sa_token", "B68C442BE645754F33277E701208059080DD726A94A73F76DEC3053A838549C06EB7D3797CE1C5BBE7C2B2EF9CA7D4676F3D489984B517943EA13575FA80C7E73160F85EDB2705D145C52621D18B86C98B49AAF0DA97A2A7A964A78DDDA048AE592CF17C2AD8BF442AD743460B9316993DCDF5924AD8536FD4392C95A998E1C4C4EEDF76BF8FF03AAC145E449EAB889368EE1E7DA72B18881D527D9F51BAD9E2678DDEAFB33A647DD6D48B2A3BE1BC51DDC55AB1EAAEE2DE9A3CEA3702F93AAD1EC8EF740B632F5A4EC102498CDB31AF91CEA15DB3B6DF6FAC6CA31473ACC5E2CD727F80D2746F504A85379E7F3971086C13BA743F21731CEBFEC558E54E8D5D486CC3A19266E238F539A59C2F8630964981217DCC3B31B324F7DBF41FAEA47CA363904F06816BA9730B31BDD9FFA5498C1D0C36D67F315BA4F9236AC77BAFD5");
            mapParamsData.put("seqSid", "5844668515208615000");
            mapParamsData.put("uuid", "5653262a-5ef1-47c6-8ac2-427f519fdcfa");
            
            Response loginResponse = Jsoup.connect(LOGIN_URI)
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
            
            Document doc = Jsoup.connect("http://order.jd.com/center/list.action")
                    .cookies(mapLoginPageCookies)
                    .timeout(30000)
                    .get();
            System.out.println(doc.toString());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
