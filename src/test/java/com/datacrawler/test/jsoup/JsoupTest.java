package com.datacrawler.test.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class JsoupTest {

    public static void main(String[] args) {
        
        String d = "<span><div>test</div></span>";
        Document doc = Jsoup.parse(d);
        Element div = doc.select("div").first(); // <div></div>
        div.html("<p>lorem ipsum</p>"); // <div><p>lorem ipsum</p></div>
        div.prepend("<p>First</p>");
        div.append("<p>Last</p>");
        // now: <div><p>First</p><p>lorem ipsum</p><p>Last</p></div>
        div.appendElement(d);
        Element span = doc.select("span").first(); // <span>One</span>
        span.wrap("<li><a href='http://example.com/'></a></li>");
        // now: <li><a href="http://example.com"><span>One</span></a></li>
        System.out.println(doc.html());
    }

}
