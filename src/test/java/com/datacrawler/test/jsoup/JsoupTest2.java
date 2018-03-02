package com.datacrawler.test.jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.datacrawler.common.util.JsoupUtil;

public class JsoupTest2 {

    public static void main(String[] args) throws IOException {
        
        String url = "https://rate.taobao.com/feedRateList.htm?auctionNumId=552463737787&userNumId=1703495879&currentPageNum=1&pageSize=20&rateType=&orderType=sort_weight&attribute=&sku=&hasSku=false&folded=0&ua=098%23E1hvqvvRvPpvUpCkvvvvvjiPPLcyljlbRsqwsjnEPmPpsjt8RLMvtjiER2q9ljnvRsyCvvBvpvvv9phv2n1w3xqBzYswM20S79wCvvNwzHi4z0CNiQhvChCvCCptvpvhphvvvvyCvh1vVxOvITlz8eQEfaAK53n5WDKt5BwsWD6rfuVHR4hSoAZnD704deDHEcqhaXTAVAIanixreTt%2BCclWQRp4e0Q4b64B9CkaU6UsxI2hKphv8hCvvvvvvhCvphvZJ9vvpu1vpC9CvvC216CvHjIvvhPjphvZK9vvpYJivpvUphvh3cUYvR7EvpvVp6WUCEIXvphvCyCCvvvvvvGCvvpvvvvv3QhvChCCvvvtvpvhphvvv86CvvDvppWpJpCv7OQ%3D&_ksTS=1519956772500_2207&callback=jsonp_tbcrate_reviews_list";
        // String url = "http://www.shixiseng.com/";

        Document doc = Jsoup.connect(url).timeout(4000).userAgent("Mozilla").get();
        
        System.out.println(doc.html());
    }

}
