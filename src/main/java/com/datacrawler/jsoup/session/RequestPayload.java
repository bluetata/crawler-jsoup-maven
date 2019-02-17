package com.datacrawler.jsoup.session;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import com.alibaba.fastjson.JSONObject;

public class RequestPayload {

    public static void main(String[] args) throws IOException {
        
        JSONObject payload = new JSONObject();
        // {"pageIndex":2,"pageSize":20,"typeTab":1}
        payload.put("pageIndex", 2);
        payload.put("pageSize", 20);
        payload.put("typeTab", 1);
        
        // String jsonBody = "{\"name\":\"ACTIVATE\",\"value\":\"E0010\"}";
        
        Connection connection = Jsoup.connect("https://www.tablenow.vn/ho-chi-minh/bo-suu-tap")
                .userAgent("Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36") // User-Agent of Chrome 55
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


    }

}
