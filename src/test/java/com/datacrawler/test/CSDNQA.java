package com.datacrawler.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class CSDNQA {
    

    public static void jsoupIOTest03() throws IOException{

//        String h = "<dl class='test'>" +
//                   "  <dt>"+
//                   "    Category"+
//                   "  </dt>"+
//                   "  <dd> "+
//                   "    <a href='/free'>Free</a>" + 
//                   "  </dd> ";
        
        String h =   " <html>" +
                  "   <head>" +
                    "     <title>JsoupInputAndOutput</title>" + 
                    "   </head>" +
                    "     <body> hhhh<ACTxxx<body>" +
                    " </html>";
        
        Document d = Jsoup.parse(h);
        
        System.out.println(d.body().html());
        
//        String s2 = d.select("a").toString();
//        System.out.println(s2);
//        System.out.println(d.select("a").remove("a"));
//        System.out.println(d.select("a").removeAttr("href"));
//        System.out.println(d.select("a").removeAttr("a"));
            
    }
    
    public static void main(String[] args) {
        try {
            jsoupIOTest03();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
