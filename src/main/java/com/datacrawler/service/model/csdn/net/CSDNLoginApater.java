package com.datacrawler.service.model.csdn.net;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.datacrawler.common.util.HttpUtil;

public class CSDNLoginApater {

    public static String LOGIN_URL = "https://passport.csdn.net/account/login";
    private static String redirectURL = "http://www.csdn.net/";
    private static HttpClient httpclient = null;
    
    static CookieStore cookieStore = null;
    static HttpClientContext context = null;
    
    // The HttpClient is used in one session
    private static HttpResponse response;
    
    public static void main(String[] args) {
        
        String lt = null;
        String execution = null;
        String _eventId = null;
        
        
        Document doc = null;
        
        try {
//            doc = Jsoup.connect("https://passport.csdn.net/account/login")
//                    .userAgent("Mozilla/5.0")
//                    .timeout(30000)
//                    .get();
            
            String html = HttpUtil.sendGet(LOGIN_URL);
            doc = Jsoup.parse(html);
            
//            Connection.Response loginResponse = Jsoup.connect("https://passport.csdn.net/account/login")
//                    .method(Connection.Method.GET)
//                    .execute();
            
            // System.out.println(doc.toString());
            
            Element form = doc.select("#fm1").get(0);
            
            lt = form.select("input[name=lt]").get(0).val();
            execution = form.select("input[name=execution]").get(0).val();
            _eventId = form.select("input[name=_eventId]").get(0).val();
            
            System.out.println(lt);
            System.out.println(execution);
            System.out.println(_eventId);
            
            
//            // Document responseDoc = Jsoup.connect("https://passport.csdn.net/account/login")
//            Response response = Jsoup.connect("https://passport.csdn.net/account/login")
//            .header("Host", "passport.csdn.net")
//            //.header("Referer", "https://passport.csdn.net/account/login?from=http://my.csdn.net/my/mycsdn")
//            .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
//            .userAgent("Mozilla/5.0")
//            .data("username", "dietime1943")
//            .data("password", "lvmeng152300")
//            .data("lt", lt)
//            .data("execution", execution)
//            .data("_eventId", _eventId)
//            
//            .cookies(loginResponse.cookies())
//            .followRedirects(false)
//            .timeout(30000)
//            .execute();
//
//            System.err.println(response.statusCode());
//            System.err.println(response.header("location"));
//            
//            //System.out.println(responseDoc);
//            
//            // http://my.csdn.net/my/mycsdn
//            
//            doc = Jsoup.connect("http://my.csdn.net/my/mycsdn")
//            .userAgent("Mozilla/5.0")
//            .timeout(30000)
//            .get();
//            
//            System.out.println(doc.toString());
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
            
            
            // http://write.blog.csdn.net/postlist
            
            //httpclient = new DefaultHttpClient();
            
            httpclient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();

            HttpPost httpost = new HttpPost(LOGIN_URL);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("username", "dietime1943"));  
            nvps.add(new BasicNameValuePair("password", "password"));  
            nvps.add(new BasicNameValuePair("lt", lt));  
            nvps.add(new BasicNameValuePair("execution", execution));  
            nvps.add(new BasicNameValuePair("_eventId", _eventId));  
            String ret = HttpUtil.sendPost(LOGIN_URL, nvps);
            
            if (ret.indexOf("redirect_back") > -1) {  
                System.out.println("登陆成功。。。。。");  
            } else if (ret.indexOf("登录太频繁") > -1) {  
                System.out.println("登录太频繁，请稍后再试。。。。。");  
            } else {  
                System.out.println("登陆失败。。。。。");  
            }  
 
            
            try {

                httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
                response = httpclient.execute(httpost);
                
                setCookieStore(response);
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                httpost.abort();
            }
            
            System.out.println(response.getStatusLine().getStatusCode());
            
            
            String redirectLocation = getRedirectLocation();
            
            System.out.println("RURL =============== :" + redirectLocation);
            
            if (redirectLocation != null) {
                // 跳到首页，现在登录完成
                String resoult = getText(redirectLocation);
                
                System.err.println(resoult);
            }

            
                
            doc = Jsoup.connect("http://my.csdn.net/my/mycsdn")
            .userAgent("Mozilla/5.0")
            .timeout(30000)
            .get();
            
            System.out.println(doc.toString());
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public static String getRedirectLocation() {
        Header locationHeader = response.getFirstHeader("Location");
        if (locationHeader == null) {
            return null;
        }
        return locationHeader.getValue();
    }
    
    public static String getText(String redirectLocation) {
        HttpGet httpget = new HttpGet(redirectLocation);
        // Create a response handler
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseBody = "";
        try {
            responseBody = httpclient.execute(httpget, responseHandler);
        } catch (Exception e) {
            e.printStackTrace();
            responseBody = null;
        } finally {
            httpget.abort();
            // httpclient.getConnectionManager().shutdown();
        }
        return responseBody;
    }
    
    
    public static void setCookieStore(HttpResponse httpResponse) {
        System.out.println("----setCookieStore");
        cookieStore = new BasicCookieStore();
        // JSESSIONID
        String setCookie = httpResponse.getFirstHeader("Set-Cookie")
            .getValue();
        String JSESSIONID = setCookie.substring("JSESSIONID=".length(),
            setCookie.indexOf(";"));
        System.out.println("JSESSIONID:" + JSESSIONID);
        // 新建一个Cookie
        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID",
            JSESSIONID);
        cookie.setVersion(0);
        //cookie.setDomain("127.0.0.1");
        //cookie.setPath("/CwlProClient");
        // cookie.setAttribute(ClientCookie.VERSION_ATTR, "0");
        // cookie.setAttribute(ClientCookie.DOMAIN_ATTR, "127.0.0.1");
        // cookie.setAttribute(ClientCookie.PORT_ATTR, "8080");
        // cookie.setAttribute(ClientCookie.PATH_ATTR, "/CwlProWeb");
        cookieStore.addCookie(cookie);
      }

}