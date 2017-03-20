package com.datacrawler.test;

import org.jsoup.nodes.Document;

import com.datacrawler.common.util.JsoupUtil;

public class JsoupTest {

    public static void main(String[] args) throws Exception {

        String _url = "http://www.w3school.com.cn/tags/html_ref_standardattributes.asp";

        getHtmlTest(_url);
        //getDocument(_url);
    }

    private static void getHtmlTest(String _url) {

        String _html = null;
        try {
            // _html = JsoupUtil.getHtml(_url);
            // http://bbs.csdn.net/forums/Other?page=2
            _html = JsoupUtil.getDocument("http://bbs.csdn.net/forums/Other", "2").toString();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        // System.out.println(_html);

        // Document doc = Jsoup.parse(_html);
        //
        // System.out.println(doc.select("tbody tr"));
        //
        // System.err.println(doc.select("tbody tr").size());
        System.out.println(_html);
    }
    
    
    private static void getDocument(String _url) {

        Document doc = null;
        try {
            doc = JsoupUtil.getDocument(_url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(doc);
    }

}
