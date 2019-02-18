package com.datacrawler.jsoup.session;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import com.alibaba.fastjson.JSONObject;

public class RequestPayload {

    public static void main(String[] args) throws IOException {
        
        HashMap<String, String> headers = new HashMap<>();
        String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/71.0.3578.80 Chrome/71.0.3578.80 Safari/537.36";
        
        JSONObject payload = new JSONObject();
        // {"pageIndex":2,"pageSize":20,"typeTab":1}
        payload.put("pageIndex", 2);
        payload.put("pageSize", 20);
        payload.put("typeTab", 1);
        
        // String jsonBody = "{\"name\":\"ACTIVATE\",\"value\":\"E0010\"}";
        
        Connection connection = Jsoup.connect("https://www.tablenow.vn/ho-chi-minh/bo-suu-tap")
                .userAgent(USER_AGENT) // User-Agent of Chrome 55
                .referrer("https://www.tablenow.vn/ho-chi-minh/bo-suu-tap")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "zh-CN,zh;q=0.9,ja;q=0.8,en;q=0.7")
                .header("Connection", "keep-alive")
                .requestBody(payload.toJSONString())
                .maxBodySize(100)
                .timeout(1000 * 10)
                .method(Connection.Method.POST);
 
        Response response = connection.execute();


        JSONObject payload1 = new JSONObject();
        payload.put("username", "username");
        payload.put("password", "password");
        payload.put("auth_token", "authToken");

        Connection.Response logon = Jsoup.connect("http://loginUrl.com/login/")
                //.cookies(cookies)
                .data("payload", payload.toString())
                .headers(headers)
                .method(Connection.Method.POST)
                .userAgent(USER_AGENT)
                .followRedirects(true)
                .execute();
        
        
        
        // upload file by jsoup
        File file1 = new File("C:/dir/file1.txt");
        FileInputStream fs1 = new FileInputStream(file1);

        Response response1 = Jsoup.connect("http://bluetata.com/")
            .data("text","Jsoup upload")            // 绑定数据
            .data("file1", "uploadTest.txt", fs1)   // 上传文件
            .method(Method.POST)
            .execute();
        
    }

}
