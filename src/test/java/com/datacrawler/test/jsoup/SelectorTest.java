package com.datacrawler.test.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class SelectorTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String htmlText = //  " <html>" +
                //  "   <head>" +
                    "     <title>JsoupInputAndOutput</title>" + 
                    "   </head>" +
                //  "   <body>" +
                    "     <h1>Hello World!</h1>" +
                    "     <img scr='testscr' alt='altsource'>" +
                //  "   </body>" +
                    " </html>";
        Document doc = Jsoup.parse(htmlText);
        System.out.println(doc.select("img").attr("alt"));

    }
    
    
    

}
