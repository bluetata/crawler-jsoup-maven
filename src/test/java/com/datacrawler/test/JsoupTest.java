package com.datacrawler.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.datacrawler.common.util.JsoupUtil;

public class JsoupTest {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        
        String _url = "http://www.w3school.com.cn/tags/html_ref_standardattributes.asp";
        
        String _html = JsoupUtil.getHtml(_url);
        
        // System.out.println(_html);
        
        Document doc = Jsoup.parse(_html);
        
        System.out.println(doc.select("tbody tr"));
        
        System.err.println(doc.select("tbody tr").size());

    }

}
