package com.datacrawler.service.model.centralnic.com;

import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.datacrawler.common.util.JsoupUtil;

public class CentralnicLoinApater {

    // post get data from : https://registrar-console.centralnic.com/dashboard/login
    public static void main(String[] args) throws Exception {
        
        String url = "https://registrar-console.centralnic.com/dashboard/login";
        String userAgent = "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";
        
        Response res = Jsoup.connect(url).userAgent(userAgent).timeout(1000 * 3).execute();

        Document d1 = Jsoup.parse(res.body());
        
        List<Element> et = d1.select("#login_form");// 获取form表单
        String XSRF = null;
        // 获取，cooking和表单属性，下面map存放post时的数据
        Map<String, String> datas = new java.util.HashMap<>();
        for (Element e : et.get(0).getAllElements()) {
          if (e.attr("name").equals("regid")) {
            e.attr("value", "*****");// 设置id
          }
          if (e.attr("name").equals("user")) {
            e.attr("value", "*****");// 设置用户名
          }
          if (e.attr("name").equals("password")) {
            e.attr("value", "*****"); // 设置用户密码
          }
          if (e.attr("name").equals("XSRF")) {
            XSRF = e.attr("value");
          }
          if (e.attr("name").length() > 0) {// 排除空值表单属性
            datas.put(e.attr("name"), e.attr("value"));
          }
        }
        
        System.out.println(datas);
        System.out.println("======================");
        System.out.println(res.url());
        System.out.println(res.statusCode());
        
        Map<String, String> cookies = res.cookies();
        cookies.put("remember_me", "H2454482%3Atoddhan");
        cookies.put("lang", "en");
        
        System.out.println(res.cookies());
        
        /* 
         * 第二次请求，以post方式提交表单数据以及cookie信息 
         */  
        Connection con2 = Jsoup.connect("https://registrar-console.centralnic.com/dashboard/login_target");  
        con2.header("User-Agent", userAgent);  
        // 设置cookie和post上面的map数据  
        Response login = con2.ignoreContentType(true).followRedirects(true).method(Method.POST)  
                                .data(datas).cookies(cookies).referrer("https://registrar-console.centralnic.com/dashboard/login")
                                .header("host", "registrar-console.centralnic.com")
                                .execute();  
        
        Map<String, String> cookies2 = login.cookies();
        cookies2.put("remember_me", "H2454482%3Atoddhan");
        cookies2.put("lang", "en");
        System.out.println(cookies2);
        
        System.out.println(login.statusCode());
        
        System.err.println(JsoupUtil.getDocumentWithCookies("https://registrar-console.centralnic.com/", cookies2));
        
        
        System.out.println(JsoupUtil.getDocumentWithCookies("https://registrar-console.centralnic.com/graphs/file_browser/droplist", cookies2));
        
        
    }

}
