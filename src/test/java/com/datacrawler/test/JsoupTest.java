package com.datacrawler.test;

import org.jsoup.nodes.Document;

import com.datacrawler.common.util.JsoupUtil;

public class JsoupTest {

    public static void main(String[] args) throws Exception {

        // String _url = "http://www.w3school.com.cn/tags/html_ref_standardattributes.asp";
        //String _url = "http://bbs.csdn.net/forums/Other";
        String _url = "http://www.open-open.com/code/";
        
        System.out.println(JsoupUtil.covertDoc2Str(getHtmlTest(_url)));
        //getDocument(_url);
    }

    private static Document getHtmlTest(String _url) {

        Document doc = null;
        try {
            // _html = JsoupUtil.getHtml(_url);
            // http://bbs.csdn.net/forums/Other?page=2
            // http://www.open-open.com/code/?pn=0
            // doc = JsoupUtil.getDocumentWithData(_url, "page", "2");
            doc = JsoupUtil.getDocumentWithData(_url, "pn", "1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }
}
