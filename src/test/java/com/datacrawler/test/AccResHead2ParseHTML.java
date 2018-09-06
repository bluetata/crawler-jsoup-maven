package com.datacrawler.test;

import java.net.URL;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.datacrawler.common.util.JsoupUtil;

import org.jsoup.Connection.Response;

public class AccResHead2ParseHTML {

    public static void main(String[] args) throws Exception {
        
        // String _url = "http://61.161.221.4:8088/mainservlet?actionType=INDEX";
        String _url = "https://rate.taobao.com/feedRateList.htm?auctionNumId=552463737787&userNumId=1703495879&currentPageNum=1&pageSize=20&rateType=&orderType=sort_weight&attribute=&sku=&hasSku=false&folded=0&ua=098%23E1hvqvvRvPpvUpCkvvvvvjiPPLcyljlbRsqwsjnEPmPpsjt8RLMvtjiER2q9ljnvRsyCvvBvpvvv9phv2n1w3xqBzYswM20S79wCvvNwzHi4z0CNiQhvChCvCCptvpvhphvvvvyCvh1vVxOvITlz8eQEfaAK53n5WDKt5BwsWD6rfuVHR4hSoAZnD704deDHEcqhaXTAVAIanixreTt%2BCclWQRp4e0Q4b64B9CkaU6UsxI2hKphv8hCvvvvvvhCvphvZJ9vvpu1vpC9CvvC216CvHjIvvhPjphvZK9vvpYJivpvUphvh3cUYvR7EvpvVp6WUCEIXvphvCyCCvvvvvvGCvvpvvvvv3QhvChCCvvvtvpvhphvvv86CvvDvppWpJpCv7OQ%3D&_ksTS=1519956772500_2207&callback=jsonp_tbcrate_reviews_list";
        String defaultCharset = "UTF-8";
        
        // according to response header to get parsed page char-set.
        Connection connection = Jsoup.connect(_url)
                .userAgent("Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36") // User-Agent of Chrome 55
                .referrer("http://blog.csdn.net/")
                .header("Content-Type", "application/json; charset=GBK")
                .header("Accept", "text/plain, */*; q=0.01")
                .header("Accept-Encoding", "gzip,deflate,sdch")
                .header("Accept-Language", "es-ES,es;q=0.8")
                .header("Connection", "keep-alive")
                .header("X-Requested-With", "XMLHttpRequest")
                .maxBodySize(100)
                .timeout(1000 * 10)
                .method(Connection.Method.POST);

        Response response = connection.execute();
        String charset = response.charset();
        System.out.println("charset:" + charset);
        
        if (null != charset && !charset.isEmpty()) {
            defaultCharset = charset;
        }
        
//        Document doc = Jsoup.connect("http://blog.csdn.net/")
//                .cookies(response.cookies())
//                .timeout(10 * 10000)
//                .get();
        
        Document doc = Jsoup.parse(new URL(_url).openStream(), defaultCharset, _url);
        System.out.println(doc);
        
        System.out.println("----------:" + JsoupUtil.getStaticCharset(doc));
    }

}
