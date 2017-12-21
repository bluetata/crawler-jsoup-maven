package com.datacrawler.test.jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;

public class JsoupLineFeed {

    public static void main(String[] args) throws Exception {
        
        String strHTML = "<html>" +
                "<head>" +
                "<title> Clean HTML By Jsoup Whitelist äü</title>" +
                "<script type=\"text/javascript\">document.getElementById(\"txtSearch\").value) == \"Search\" </script>" +
                "</head>" +
                "<body bgcolor=\"000000\">" +
                "<center><img src=\"image.jpg\" align=\"bottom\"> </center>" +
                "<hr>" +
                "<a href=\"http://blog.csdn.net/dietime1943\">bluetata</a>" +
                "<h1>heading tags H1</h1>" +
                "<h2>heading tags H2</h2>" +
                "My email link <a href=\"mailto:dietime1943@gmail.com\">" +
                "dietime1943@gmail.com</a>." +
                "<p>Para tag</p>" +
                "<p><b>bold paragraph</b>" +
                "<br><b><i>bold italics text.</i></b>" +
                "<hr>Horizontal line" +
                "</body>" +
                "</html>";
        
        //Document doc = Jsoup.connect("http://aviprobo.doorfree.com/control.html").timeout(10000).userAgent("Mozilla").get();
        
        //clean HTML using none whitelist (remove all HTML tags)
//        String cleanedHTML = Jsoup.clean(strHTML, Whitelist.none());
//        System.out.println("None whitelist");
        
        System.out.println(Jsoup.parse(strHTML));
        //System.out.println(cleanedHTML);

        System.out.println("===================================");


    }
}
