package com.datacrawler.test.jsoup;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PostSend2 {

    // post方式获取 [全国建筑市场监管公共服务平台] 
    public static void main(String[] args) throws Exception {
        
        String url = "http://jzsc.mohurd.gov.cn/dataservice/query/comp/list";
        Response res = Jsoup.connect(url).execute();
        Map<String, String> cookies = res.cookies();
        System.out.println(cookies);
        
        Map<String, String> datas = new HashMap<>();
        datas.put("$total", "297267");
        datas.put("$reload", "1");
        datas.put("$pg", "31&&1=1");
        datas.put("$pgsz", "15");
        
        Response res1 = Jsoup.connect(url).cookies(cookies).data(datas).method(Method.POST).execute();
        
        String html = res1.body();
        System.out.println(html);
        
//        String jsonBody = "{pg:30,ps:15,tt:297267,pn:5,pc:19818,id:'',st:true}"; 
//        
//        Connection connection = Jsoup.connect("http://jzsc.mohurd.gov.cn/dataservice/query/comp/list")  
//        .userAgent("Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36") // User-Agent of Chrome 55  
//        .referrer("http://jzsc.mohurd.gov.cn/dataservice/query/comp/list")  
//        .header("Content-Type", "application/x-www-form-urlencoded")  
//        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")  
//        .header("Accept-Encoding", "gzip, deflate")  
//        .header("Accept-Language", "zh-CN,en-US;q=0.8,zh;q=0.6,ja;q=0.4,en;q=0.2")  
//        .header("Cookie", "filter_comp=; JSESSIONID=F53A68FBA45CCF750F9F32ACF090DC9B")  
//        .header("Host", "jzsc.mohurd.gov.cn")  
//        .header("Connection", "keep-alive")  
//        .header("X-Requested-With", "XMLHttpRequest")  
//        .header("Upgrade-Insecure-Requests", "1")  
//        .requestBody(jsonBody)  
//        .maxBodySize(100)  
//        .timeout(1000 * 10)  
//        .method(Connection.Method.POST);  
//  
//        Response response = connection.execute(); 
//        
//        String body = response.body();
//        System.out.println(body);

    }

}
