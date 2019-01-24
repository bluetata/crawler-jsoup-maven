/**
 * GITHUBLoginApater.java
 *
 * Function：Jsoup model apater class.
 *
 *   ver     date           author
 * ──────────────────────────────────
 *   1.0     2017/06/22     bluetata
 *
 * Copyright (c) 2017, [https://github.com/] All Rights Reserved.
 */
package com.datacrawler.service.model.github.com;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * @since      crawler(datasnatch) version(1.0)</br>
 * @author     bluetata / dietime1943@gmail.com</br>
 * @reference  http://blog.csdn.net/dietime1943/</br>
 * @version    1.0</br>
 */
public class GITHUBLoginApater {

    public static String LOGIN_URL = "https://github.com/login";
    public static String USER_AGENT = "User-Agent";
    public static String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0";
    
    public static void main(String[] args) throws Exception {

        simulateLogin("bluetata", "password123"); // 模拟登陆github的用户名和密码

    }

    /**
     * @param userName 用户名
     * @param pwd 密码
     * @throws Exception
     */
    public static void simulateLogin(String userName, String pwd) throws Exception {

        /* 
         * 第一次请求 
         * grab login form page first
         * 获取登陆提交的表单信息，及修改其提交data数据（login，password）
         */
        // get the response, which we will post to the action URL(rs.cookies())
        Connection con = Jsoup.connect(LOGIN_URL);  // 获取connection
        con.header(USER_AGENT, USER_AGENT_VALUE);   // 配置模拟浏览器
        Response rs = con.execute();                // 获取响应
        Document d1 = Jsoup.parse(rs.body());       // 转换为Dom树
        
        System.out.println(d1);
        
        List<Element> eleList = d1.select("form");  // 获取提交form表单，可以通过查看页面源码代码得知

        // 获取cooking和表单属性
        // lets make data map containing all the parameters and its values found in the form
        Map<String, String> datas = new HashMap<>();
        
        // 01/24/2019 17:45 bluetata 更新 -------------------------------------------------------------- Start ----------
        // GitHub多次改版更新，最新的提交request data为:
        
        // authenticity_token   ll0RJnG1f9XDAaN1DxnyTDzCs+YXweEZWel9kGkq8TvXH83HjCwPG048sJ/VVjDA94YmbF0qvUgcJx8/IKlP8Q==
        // commit  Sign+in
        // login   bluetata
        // password    password123
        // utf8    ✓
        
        for(int i = 0; i < eleList.size(); i++) {
        
            for (Element e : eleList.get(i).getAllElements()) {
                // 设置用户名
                if (e.attr("name").equals("login")) {
                    e.attr("value", userName);
                }
                // 设置用户密码
                if (e.attr("name").equals("password")) {
                    e.attr("value", pwd);
                }
                // 排除空值表单属性
                if (e.attr("name").length() > 0) {
                    datas.put(e.attr("name"), e.attr("value"));
                }
            }
        }

        
//      旧逻辑  delete  01/24/2019 17:49 bluetata --------------------------------------------start
//        for (Element e : eleList.get(0).getAllElements()) {
//            // 设置用户名
//            if (e.attr("name").equals("login")) {
//                e.attr("value", userName);
//            }
//            // 设置用户密码
//            if (e.attr("name").equals("password")) {
//                e.attr("value", pwd);
//            }
//            // 排除空值表单属性
//            if (e.attr("name").length() > 0) {
//                datas.put(e.attr("name"), e.attr("value"));
//            }
//        }
//      旧逻辑  delete  01/24/2019 17:49 bluetata --------------------------------------------end
        
        
        // 01/24/2019 17:45 bluetata 更新 --------------------------------------------------------------- End -----------
        
        /*
         * 第二次请求，以post方式提交表单数据以及cookie信息
         */
        Connection con2 = Jsoup.connect("https://github.com/session");
        con2.header(USER_AGENT, USER_AGENT_VALUE);
        // 设置cookie和post上面的map数据
        Response login = con2.ignoreContentType(true).followRedirects(true).method(Method.POST).data(datas).cookies(rs.cookies()).execute();
        // 打印，登陆成功后的信息
        System.out.println(login.body());

        // 登陆成功后的cookie信息，可以保存到本地，以后登陆时，只需一次登陆即可
        Map<String, String> map = login.cookies();
        for (String s : map.keySet()) {
            System.out.println(s + " : " + map.get(s));
        }
    }

}