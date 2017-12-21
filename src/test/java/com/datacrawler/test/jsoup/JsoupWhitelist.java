package com.datacrawler.test.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class JsoupWhitelist {

    public static void main(String[] args) {
        
        String strHTML = "<html>" +
                "<head>" +
                "<title> Clean HTML By Jsoup Whitelist</title>" +
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
        
        //clean HTML using none whitelist (remove all HTML tags)
        String cleanedHTML = Jsoup.clean(strHTML, Whitelist.none());
        System.out.println("None whitelist");
        System.out.println(cleanedHTML);

        System.out.println("===================================");

        //clean HTML using relaxed whitelist
        cleanedHTML = Jsoup.clean(strHTML, Whitelist.relaxed());
        System.out.println("Relaxed whitelist");
        System.out.println(cleanedHTML);

//        String strHTML = "<html>" +
//                "<head>" +
//                "<title>retain specific tags</title>" +
//                "</head>" +
//                "<body bgcolor=\"000000\">" +
//                "<a href=\"http://blog.csdn.net/dietime1943\">bluetata</a>" +
//                "<h1>heading tag H1</h1>" +
//                "<div>div tag content</div>" +
//                "</body>" +
//                "</html>";
//         
//        String cleanedHTML = Jsoup.clean(strHTML, Whitelist.none().addTags("div"));
//         
//        System.out.println(cleanedHTML);
    }
}
